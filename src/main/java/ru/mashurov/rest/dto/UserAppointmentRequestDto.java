package ru.mashurov.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAppointmentRequestDto {

    private Long id;

    private String clinicName;

    private String clinicAddress;

    private String veterinarianName;

    private String appointmentPlace;

    private String serviceName;

    private String status;

    private String petName;

    private String date;
}
