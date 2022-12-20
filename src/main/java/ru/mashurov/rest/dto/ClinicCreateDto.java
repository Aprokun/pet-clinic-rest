package ru.mashurov.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mashurov.rest.model.Region;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicCreateDto {

	private String name;

	private String address;

	private Region region;
}
