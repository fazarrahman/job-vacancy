package com.jobvacancy.jobdetails.controller;

import com.jobvacancy.jobdetails.auth.AuthenticationResponse;
import com.jobvacancy.jobdetails.auth.AuthenticationService;
import com.jobvacancy.jobdetails.auth.RegisterRequest;
import com.jobvacancy.jobdetails.entity.Jobs;
import com.jobvacancy.jobdetails.service.JobService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<Jobs>> getJob() throws Exception {
        return ResponseEntity.ok(jobService.GetJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jobs> getJobById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(jobService.GetJobById(id));
    }
}
