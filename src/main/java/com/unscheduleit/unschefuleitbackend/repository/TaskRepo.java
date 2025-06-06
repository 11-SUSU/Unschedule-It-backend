package com.unscheduleit.unschefuleitbackend.repository;

import com.unscheduleit.unschefuleitbackend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, String>, JpaSpecificationExecutor<Task> {
}
