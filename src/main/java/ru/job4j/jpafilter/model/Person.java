package ru.job4j.jpafilter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "persons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "p_name")
    private String pName;

    @Column(name = "age")
    private Long age;

    @Column(name = "country_of_born")
    private String countryOfBorn;

    @Column(name = "gender")
    private String gender;
}
