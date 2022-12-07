//package com.example.restTemplate.service.impl;
//
//import com.example.restTemplate.entity.Employee;
//import com.example.restTemplate.pojo.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import javax.transaction.Transactional;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static java.util.stream.Collectors.groupingBy;
//
//@Service
//@Transactional
//public class EmployeeServiceImpl {
//    private final RestTemplate restTemplate;
//    private static final String url = "https://dummy.restapiexample.com/api/v1/employees";
//
//    @Autowired
//    public EmployeeServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public List<Employee> getAll(){
//        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setAccept(singletonList(MediaType.APPLICATION_JSON));
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
//        return restTemplate.exchange(url, HttpMethod.GET, entity, Response.class).getBody().getData();
////        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,Response.class).getBody();
////                new ParameterizedTypeReference<List<Employee>>() {});
////        return responseEntity.getBody();
//
//    }
//
//
//    public List<Employee> getByAge(int age) {
//        return getAll().stream().filter(emp -> emp.getEmployee_age() == age).collect(Collectors.toList());
//    }
//    public List<List<Employee>> getAllByAge() {
//        Map<Integer, List<Employee>> empMap = getAll()
//                .stream().collect(Collectors.groupingBy(Employee::getEmployee_age));
//        List<List<Employee>> empList = new ArrayList<>();
//        for(int i : empMap.keySet()){
//            empList.add(empMap.get(i));
//        }
//        return empList;
//    }
//}
