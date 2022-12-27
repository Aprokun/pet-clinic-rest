package ru.mashurov.rest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "appointment_request")
public class AppointmentRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String appointmentPlace;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime date;

	@OneToOne
	private Clinic clinic;

	@OneToOne
	private Service service;

	@ManyToOne
	@JsonBackReference("veterinarian-value")
	@JoinColumn(name = "veterinarian_id", referencedColumnName = "id")
	private Veterinarian veterinarian;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference("appointments-value")
	@ToString.Exclude
	private Pet pet;

	@OneToOne
	private User user;

	@OneToOne
	@JoinColumn(name = "status", referencedColumnName = "id")
	private AppointmentRequestStatus status;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppointmentRequest that = (AppointmentRequest) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
