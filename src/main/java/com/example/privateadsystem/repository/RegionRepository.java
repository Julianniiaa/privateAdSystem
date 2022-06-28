package com.example.privateadsystem.repository;

import com.example.privateadsystem.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findByIdRegion(long id);
}
