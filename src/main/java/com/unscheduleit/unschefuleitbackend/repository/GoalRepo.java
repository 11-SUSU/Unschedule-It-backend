package com.unscheduleit.unschefuleitbackend.repository;

import com.unscheduleit.unschefuleitbackend.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepo extends JpaRepository<Goal, String> {



}
