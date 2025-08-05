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
    
    private final String NCP_CLIENT_ID = "881a9cbawu";
    private final String NCP_CLIENT_SECRET = "VlccERoPwuogrKO7yI6JgblOd8BcslsP1MJeeT3b";

    // 네이버 지역 검색 API
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
    
    // 네이버 클라우드 플랫폼 directions 5 길찾기 기능
    public String getDrivingRoute(String start, String goal) throws IOException {
        String apiURL = "https://maps.apigw.ntruss.com/map-direction/v1/driving"
                      + "?start=" + URLEncoder.encode(start, StandardCharsets.UTF_8)
                      + "&goal=" + URLEncoder.encode(goal, StandardCharsets.UTF_8);

        HttpURLConnection conn = (HttpURLConnection) new URL(apiURL).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", NCP_CLIENT_ID);
        conn.setRequestProperty("X-NCP-APIGW-API-KEY", NCP_CLIENT_SECRET);

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

