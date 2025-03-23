package ru.job4j.jpafilter.model.wiredmodel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonW {
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

    @JoinColumn(name = "person_id") // столбец внешнего ключа в таблице books
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookW> books;
}
