package com.citizen.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.citizen.portal.model.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Integer> {

    // Worker side: get only available issues
    List<Issue> findByStatus(String status);

    // Citizen side: get all issues posted by a user
    List<Issue> findByUserId(int userId);
}
