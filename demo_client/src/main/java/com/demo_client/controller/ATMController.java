package com.demo_client.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class ATMController
{
    private RestTemplate restTemplate = null;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @GetMapping(value = "/login")
    public Object login(@RequestParam String userName, @RequestParam String password)
    {
        String url = "http://localhost:8080/login";

        this.restTemplate = restTemplateBuilder
                .basicAuthentication(userName, password)
                .build();

         Object user = restTemplate.getForObject(url, Object.class);
        return user;
    }

    @GetMapping(value = "/logout")
    public Object logout()
    {
        String url = "http://localhost:8080/logout";

        if(restTemplate == null)
            return ("Please log in.");

        Object message = restTemplate.getForObject(url, Object.class);
        restTemplate = null;
        return message;
    }

    @GetMapping(value = "/checkBalance")
    public Object checkBalance()
    {
        String url = "http://localhost:8080/api/operations/checkBalance";

        if(restTemplate == null)
            return ("Please log in.");

        Object balance = restTemplate.getForObject(url, Object.class);
        return balance;
    }

    @PostMapping(value = "/deposit")
    public String deposit(@RequestParam("50") Long count50Notes,
                          @RequestParam("100") Long count100Notes,
                          @RequestParam("200") Long count200Notes,
                          @RequestParam("500") Long count500Notes,
                          @RequestParam("2000") Long count2000Notes)

    {
        String url = "http://localhost:8080/api/operations/addMoney";

        if(restTemplate == null)
            return ("Please log in.");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("50", count50Notes.toString());
        map.add("100", count100Notes.toString());
        map.add("200", count200Notes.toString());
        map.add("500", count500Notes.toString());
        map.add("2000", count2000Notes.toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        String message = restTemplate.postForObject(url, request, String.class);
        return message;
    }

    @PostMapping(value = "/withdraw")
    public String withdraw(@RequestParam("50") Long count50Notes,
                          @RequestParam("100") Long count100Notes,
                          @RequestParam("200") Long count200Notes,
                          @RequestParam("500") Long count500Notes,
                          @RequestParam("2000") Long count2000Notes)

    {
        String url = "http://localhost:8080/api/operations/withdrawMoney";

        if(restTemplate == null)
            return ("Please log in.");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("50", count50Notes.toString());
        map.add("100", count100Notes.toString());
        map.add("200", count200Notes.toString());
        map.add("500", count500Notes.toString());
        map.add("2000", count2000Notes.toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        String message = restTemplate.postForObject(url, request, String.class);
        return message;
    }
}