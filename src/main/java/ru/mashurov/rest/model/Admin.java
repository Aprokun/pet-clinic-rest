package ru.mashurov.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "admins")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false, unique = true)
	private String login;

	@Column(nullable = false)
	private String password;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clinic", referencedColumnName = "id")
	private Clinic clinic;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(
			name = "admins_roles",
			joinColumns = @JoinColumn(name = "admin_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Role role;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Admin admin = (Admin) o;
		return id != null && Objects.equals(id, admin.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
