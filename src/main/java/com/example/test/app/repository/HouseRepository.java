package com.example.test.app.repository;

import com.example.test.app.model.house.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
