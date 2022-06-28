package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.model.Region;
import com.example.privateadsystem.repository.RegionRepository;
import com.example.privateadsystem.service.RegionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }
}
