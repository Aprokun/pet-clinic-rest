package ru.mashurov.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

	@Id
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String name;
}
