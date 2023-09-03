package com.jobvacancy.jobdetails.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobvacancy.jobdetails.entity.Jobs;
import com.jobvacancy.jobdetails.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
@Service
@RequiredArgsConstructor
public class JobService {
    private static String ALL_JOBS_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
    private static String JOB_BY_ID_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions/%s";

    public List<Jobs> GetJobs() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ALL_JOBS_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        List<Jobs> jobs = objectMapper
                .readValue(response.body(), new TypeReference<List<Jobs>>(){});

        return jobs;
    }

    public Jobs GetJobById(String id) throws Exception {
        if (Strings.isBlank(id)) {
            throw new BadRequestException("id is required");
        }

        ObjectMapper objectMapper = new ObjectMapper();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format(JOB_BY_ID_URL, id)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Jobs job = objectMapper
                .readValue(response.body(), Jobs.class);

        return job;
    }
}
