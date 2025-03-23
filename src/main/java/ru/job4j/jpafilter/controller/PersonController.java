package ru.job4j.jpafilter.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.jpafilter.filter.SearchValue;
import ru.job4j.jpafilter.model.Person;
import ru.job4j.jpafilter.model.wiredmodel.PersonW;
import ru.job4j.jpafilter.service.PersonService;

@RestController
@RequestMapping("/filter")
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping("/person")
    public ResponseEntity<Page<Person>> findOnlyPerson(@RequestBody SearchValue searchValue) {
        return new ResponseEntity<>(service.findByOnlyPersonSpecification(searchValue), HttpStatus.OK);
    }

    @PostMapping("/personbook")
    public ResponseEntity<Page<Person>> findPersonBook(@RequestBody SearchValue searchValue) {
        return new ResponseEntity<>(service.findByPersonAndBookSpecification(searchValue), HttpStatus.OK);
    }

    @PostMapping("/personbookwired")
    public ResponseEntity<Page<PersonW>> findPersonWBookW(@RequestBody SearchValue searchValue) {
        return new ResponseEntity<>(service.findByPersonAndBookMappedSpecification(searchValue), HttpStatus.OK);
    }
}
