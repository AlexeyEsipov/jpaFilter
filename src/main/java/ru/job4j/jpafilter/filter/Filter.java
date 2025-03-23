package ru.job4j.jpafilter.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Filter {
    private Long personId;
    private String pName;
    private Integer age;
    private String countryOfBorn;
    private Set<String> favoriteList;
    private String gender;
    private Long bookId;
    private Long pages;
    private String title;
    private String isbn;
    private Long picNumbers;
    private Long picMax;
    private Long picMin;
    private String publisher;
    private Long publisherCode;
    private LocalDateTime dateOfPublishingMax;
    private LocalDateTime dateOfPublishingMin;
    private Set<Long> stopPublisherCodeList;
}
