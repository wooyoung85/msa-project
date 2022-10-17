package com.woodong.catalog.service;

import com.woodong.catalog.entity.CatalogEntity;
import com.woodong.catalog.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;

    @Override
    public List<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }
}
