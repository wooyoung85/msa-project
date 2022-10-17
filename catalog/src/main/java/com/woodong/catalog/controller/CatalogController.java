package com.woodong.catalog.controller;

import com.woodong.catalog.data.response.ResponseCatalog;
import com.woodong.catalog.entity.CatalogEntity;
import com.woodong.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {
        return String.format("It's working in catalog service on port %s", request.getServerPort());
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        List<CatalogEntity> orderList = catalogService.getAllCatalogs();
        List<ResponseCatalog> result = new ArrayList<>();
        orderList.forEach(order -> result.add(new ModelMapper().map(order, ResponseCatalog.class)));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
