package com.citizen.portal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citizen.portal.model.Issue;
import com.citizen.portal.model.User;
import com.citizen.portal.repository.IssueRepository;
import com.citizen.portal.repository.UserRepository;

import java.util.*;

@Service
public class IssueService {

    private final IssueRepository issueRepo;
    private final UserRepository userRepo;

    public IssueService(IssueRepository issueRepo, UserRepository userRepo) {
        this.issueRepo = issueRepo;
        this.userRepo = userRepo;
    }

    // ---------------- CITIZEN: ADD ISSUE ----------------
    @Transactional
    public Issue addIssue(Issue issue) {
        issue.setStatus("PENDING");
        issue.setWorkerId(null);
        return issueRepo.save(issue);
    }

    // ---------------- WORKER: VIEW AVAILABLE ISSUES ----------------
    public List<Issue> getAvailableIssues() {
        return issueRepo.findByStatus("PENDING");
    }

    // 🔥 THIS METHOD IS REQUIRED (THIS WAS MISSING)
    public List<Map<String, Object>> getAvailableIssuesWithCitizenDetails() {

        List<Issue> issues = issueRepo.findByStatus("PENDING");
        List<Map<String, Object>> result = new ArrayList<>();

        for (Issue issue : issues) {
            Map<String, Object> map = new HashMap<>();

            User citizen = userRepo.findById(issue.getUserId()).orElse(null);

            map.put("issue", issue);
            map.put("citizenName", citizen != null ? citizen.getName() : "Unknown");
            map.put("citizenEmail", citizen != null ? citizen.getEmail() : "N/A");

            result.add(map);
        }
        return result;
    }

    // ---------------- CITIZEN: VIEW MY ISSUES ----------------
    public List<Issue> getIssuesByUser(int userId) {
        return issueRepo.findByUserId(userId);
    }
}
