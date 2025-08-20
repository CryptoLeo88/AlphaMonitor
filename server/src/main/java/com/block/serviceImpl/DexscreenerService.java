package com.block.serviceImpl;

import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class DexscreenerService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getTokenInfo(String chain, String address) {

        String url = "https://api.dexscreener.com/tokens/v1/"+chain+"/"+address;
        HttpGet httpGet = new HttpGet(url);


        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        return response;
    }


}
