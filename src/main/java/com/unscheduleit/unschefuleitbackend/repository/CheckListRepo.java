package com.unscheduleit.unschefuleitbackend.repository;

import com.unscheduleit.unschefuleitbackend.entities.ChecklistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckListRepo extends JpaRepository<ChecklistItem, String> {
}
