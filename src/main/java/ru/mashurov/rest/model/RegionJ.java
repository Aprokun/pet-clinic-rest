package ru.mashurov.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "region")
public class RegionJ {

    @Id
    @Column(nullable = false)
    private Long code;

    @Column(nullable = false)
    private String name;
}
