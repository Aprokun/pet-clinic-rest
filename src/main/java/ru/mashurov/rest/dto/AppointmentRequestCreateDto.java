package ru.mashurov.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestCreateDto {

    private Long veterinarianId;

    private Long clinicId;

    private String appointmentPlace;

    private Long petId;

    private Long serviceId;
}
