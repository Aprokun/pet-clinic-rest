package ru.mashurov.rest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.mashurov.rest.converter.TimetableConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "veterinarian_timetables")
public class VeterinarianTimetable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(columnDefinition = "json default ' '")
	@Convert(converter = TimetableConverter.class)
	private Timetable timetable;

	@OneToOne(fetch = FetchType.LAZY)
	@JsonBackReference("vet_timetable")
	@JoinColumn(name = "veterinarian_id", nullable = false)
	@ToString.Exclude
	private Veterinarian veterinarian;
}
