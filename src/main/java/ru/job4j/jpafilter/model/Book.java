package ru.job4j.jpafilter.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pages")
    private Long pages;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "pic_numbers")
    private Long picNumbers;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publisher_code")
    private Long publisherCode;

    @Column(name = "date_of_publishing")
    private LocalDateTime dateOfPublishing;

    @Column(name = "person_id")
    private Long personId;
}
