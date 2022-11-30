package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.Region;
import ru.mashurov.rest.repositories.RegionRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RegionService {

    private RegionRepo regionRepo;

    public List<Region> findAll() {

        final List<Region> result = new ArrayList<>();
        regionRepo.findAll().forEach(result::add);

        return result;
    }

}
