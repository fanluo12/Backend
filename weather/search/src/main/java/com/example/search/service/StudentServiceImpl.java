package com.example.search.service;

import com.example.search.domain.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Service
public class StudentServiceImpl implements StudentService {
    private final ExecutorService pool;
    private final RestTemplate ribbonRestTemplate;

    @Autowired
    public StudentServiceImpl(ExecutorService pool, RestTemplate ribbonRestTemplate) {
        this.pool = pool;
        this.ribbonRestTemplate = ribbonRestTemplate;
    }

    @Override
    public Map fetchAllStudents() {
        Map map = ribbonRestTemplate.getForObject("http://university-service/university/students", HashMap.class);
        return map;
    }

    @Override
    public Map fetchAllTeachers() {
        Map map = ribbonRestTemplate.getForObject("http://university-service/university/teachers", HashMap.class);
        return map;
    }

    @Override
    public Map<Integer, Map> getStudentByIds(List<String> ids) {
        List<CompletableFuture> completableFutureList = new ArrayList<>();
        for(String id: ids) {
            completableFutureList.add(
                    CompletableFuture.supplyAsync(
                            () -> ribbonRestTemplate.getForObject(
                                    "http://university-service/university/students/" + id + "/",
                                    HashMap.class)
                            , pool
                    )
            );
        }
        return CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]))
                .thenApply(VOID -> {
                    Map<Integer, Map> map = new HashMap<>();
                    for(int i = 0; i < completableFutureList.size(); i++) {
                        try {
                            map.put(Integer.valueOf(ids.get(i)), (Map)completableFutureList.get(i).get());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return map;
                }).join();
    }

}
