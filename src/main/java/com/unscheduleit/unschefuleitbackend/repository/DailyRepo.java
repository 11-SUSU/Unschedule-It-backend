package com.unscheduleit.unschefuleitbackend.repository;

import com.unscheduleit.unschefuleitbackend.entities.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRepo extends JpaRepository<Daily, String> {
}
