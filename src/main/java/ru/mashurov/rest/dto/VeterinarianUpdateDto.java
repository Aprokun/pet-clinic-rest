package ru.mashurov.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarianUpdateDto {

	private Long id;

	private String surname;

	private String name;

	private String patronymic;

	private Integer experience;

	private Long clinicId;
}
