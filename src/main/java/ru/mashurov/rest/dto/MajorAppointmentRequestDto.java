package ru.mashurov.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MajorAppointmentRequestDto {

    private Long id;

    private String veterinarianName;

    private String appointmentPlace;

    private String petName;

    private String serviceName;

    private String status;

    private String date;
}
