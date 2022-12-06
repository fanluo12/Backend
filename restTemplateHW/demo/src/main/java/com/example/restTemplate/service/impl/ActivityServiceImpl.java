package com.example.restTemplate.service.impl;

import com.example.restTemplate.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
public class ActivityServiceImpl {
    private final RestTemplate restTemplate;
    private final String url = "https://www.boredapi.com/api/activity";

    @Autowired
    public ActivityServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public Activity getAll(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity request = new HttpEntity(httpHeaders);
        Activity response = restTemplate.exchange(url, HttpMethod.GET, request, Activity.class).getBody();
        return response;
    }
}
