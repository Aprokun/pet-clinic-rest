package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.Region;
import ru.mashurov.rest.repositories.RegionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegionService {

    private RegionRepo regionRepo;

    public List<Region> findAll() {

        final List<Region> result = new ArrayList<>();
        regionRepo.findAll().forEach(result::add);

        return result;
    }

    public Region findById(final Long code) {

        final Optional<Region> optionalRegion = regionRepo.findById(code);

        if (optionalRegion.isPresent()) {
            return optionalRegion.get();
        } else {
            throw new RuntimeException("Нет региона с таким кодом - " + code);
        }
    }
}
