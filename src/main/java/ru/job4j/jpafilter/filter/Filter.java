package ru.job4j.jpafilter.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Filter {

    @Schema(description = "id автора")
    private Long personId;

    @Schema(description = "Имя автора")
    private String pName;

    @Schema(description = "Возраст автора")
    private Integer age;

    @Schema(description = "Наименование страны происхождения автора")
    private String countryOfBorn;

    @Schema(description = "Список избранных авторов")
    private Set<String> favoriteList;

    @Schema(description = "Пол автора")
    private String gender;

    @Schema(description = "id книги")
    private Long bookId;

    @Schema(description = "Количество страниц")
    private Long pages;

    @Schema(description = "Наименование книги")
    private String title;

    @Schema(description = "Код ISBN")
    private String isbn;

    @Schema(description = "Общее количество рисунков в книге")
    private Long picNumbers;

    @Schema(description = "Максимальное количество рисунков в книге")
    private Long picMax;

    @Schema(description = "Минимальное количество рисунков в книге")
    private Long picMin;

    @Schema(description = "Наименование издателя")
    private String publisher;

    @Schema(description = "Код книги, присвоенный издателем")
    private Long publisherCode;

    @Schema(description = "Дата публикации максимальная")
    private LocalDateTime dateOfPublishingMax;

    @Schema(description = "Дата публикации минимальная")
    private LocalDateTime dateOfPublishingMin;

    @Schema(description = "Список кодов книг, которых не должно быть в ответе")
    private Set<Long> stopPublisherCodeList;
}
