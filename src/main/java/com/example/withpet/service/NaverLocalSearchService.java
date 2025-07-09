package com.example.withpet.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

@Service
public class NaverLocalSearchService {

    private final String CLIENT_ID = "XHZ9ADZ2rrQWqLCtLrc6";
    private final String CLIENT_SECRET = "BEuGuzPjRB";

    public String searchLocal(String query) throws IOException {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + encodedQuery + "&display=5&start=1&sort=random";

        HttpURLConnection conn = (HttpURLConnection) new URL(apiURL).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
        conn.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);

        int responseCode = conn.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(
            (responseCode == 200) ? conn.getInputStream() : conn.getErrorStream()
        ));

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();
        return response.toString();
    }
}

