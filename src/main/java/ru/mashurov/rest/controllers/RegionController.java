package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.RegionListDto;
import ru.mashurov.rest.model.Region;
import ru.mashurov.rest.services.RegionService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/regions")
    public ResponseEntity<RegionListDto> regions() {

        final List<Region> regions = regionService.findAll();
        return ResponseEntity.ok(new RegionListDto(regions));
    }
}