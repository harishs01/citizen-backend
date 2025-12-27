package com.citizen.portal.controller;

import org.springframework.web.bind.annotation.*;

import com.citizen.portal.model.Issue;
import com.citizen.portal.service.IssueService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "*")
public class IssueController {

    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    // ---------------- CITIZEN: POST ISSUE ----------------
    @PostMapping("/add")
    public Issue addIssue(@RequestBody Issue issue) {
        return service.addIssue(issue);
    }

    // ---------------- WORKER: VIEW AVAILABLE ISSUES (BASIC) ----------------
    @GetMapping("/available")
    public List<Issue> getAvailableIssues() {
        return service.getAvailableIssues();
    }

    // 🔥 ---------------- WORKER: VIEW AVAILABLE ISSUES + CITIZEN DETAILS ----------------
    @GetMapping("/available/details")
    public List<Map<String, Object>> getAvailableIssuesWithCitizenDetails() {
        return service.getAvailableIssuesWithCitizenDetails();
    }

    // ---------------- CITIZEN: VIEW MY ISSUES + STATUS ----------------
    @GetMapping("/user/{userId}")
    public List<Issue> getIssuesByUser(@PathVariable("userId") int userId) {
        return service.getIssuesByUser(userId);
    }
}
