package ru.mashurov.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDto {

	private Long id;


	private String veterinarianName;

	private String appointmentPlace;

	private String petName;

	private String serviceName;

	private LocalDateTime date;
}
