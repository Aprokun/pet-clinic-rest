package ru.mashurov.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mashurov.rest.model.Timetable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarianTimetableCreateDto {

	private Long id;

	private Long veterinarianId;

	private Timetable timetable;
}
