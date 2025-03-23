package ru.job4j.jpafilter.service;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.job4j.jpafilter.filter.Filter;
import ru.job4j.jpafilter.filter.PageParam;
import ru.job4j.jpafilter.filter.SearchValue;
import ru.job4j.jpafilter.model.Book;
import ru.job4j.jpafilter.model.Person;
import ru.job4j.jpafilter.model.wiredmodel.BookW;
import ru.job4j.jpafilter.model.wiredmodel.PersonW;
import ru.job4j.jpafilter.repository.PersonRepository;
import ru.job4j.jpafilter.repository.PersonWiredSpecificationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PersonService {
    private final PersonRepository repository;

    private final PersonWiredSpecificationRepository personWiredSpecificationRepository;


    public PersonService(PersonRepository repository,
                         PersonWiredSpecificationRepository personWiredSpecificationRepository) {
        this.repository = repository;
        this.personWiredSpecificationRepository = personWiredSpecificationRepository;
    }

    public Page<Person> findByOnlyPersonSpecification(SearchValue searchValue) {
        Filter filter = searchValue.getFilter();
        PageParam pageParam = searchValue.getPageParam();
        PageRequest pageRequest = createPageRequest(pageParam);
        Long personId = filter.getPersonId();
        String pName = filter.getPName();
        Integer age = filter.getAge();
        String countryOfBorn = filter.getCountryOfBorn();
        Set<String> favoriteList = filter.getFavoriteList();
        String gender = filter.getGender();

        Specification<Person> specification = (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();
            if (personId != null) {
                predicates.add(cb.equal(root.get("id"), personId));
            }
            if (pName != null && !pName.isBlank()) {
                predicates.add(cb.like(cb.upper(root.get("pName")), "%" + pName.toUpperCase().trim() + "%"));
            }
            if (age != null) {
                predicates.add(cb.equal(root.get("age"), age));
            }
            if (countryOfBorn != null && !countryOfBorn.isBlank()) {
                predicates.add(cb.like(cb.upper(root.get("countryOfBorn")), "%" + countryOfBorn.toUpperCase().trim() + "%"));
            }
            if (gender != null && !gender.isBlank()) {
                predicates.add(cb.like(cb.upper(root.get("gender")),  gender.toUpperCase().trim()));
            }
            if (favoriteList != null && !favoriteList.isEmpty()) {
                predicates.add(root.get("pName").in(favoriteList));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return repository.findAll(specification, pageRequest);
    }

    public Page<Person> findByPersonAndBookSpecification(SearchValue searchValue) {
        Filter filter = searchValue.getFilter();
        PageParam pageParam = searchValue.getPageParam();
        PageRequest pageRequest = createPageRequest(pageParam);
        Long personId = filter.getPersonId();
        String pName = filter.getPName();
        Integer age = filter.getAge();
        String countryOfBorn = filter.getCountryOfBorn();
        Set<String> favoriteList = filter.getFavoriteList();
        String gender = filter.getGender();

        Long bookId = filter.getBookId();
        Long pages = filter.getPages();
        String title = filter.getTitle();
        String isbn = filter.getIsbn();
        Long picNumbers = filter.getPicNumbers();
        Long picMin = filter.getPicMin();
        Long picMax = filter.getPicMax();
        String publisher = filter.getPublisher();
        Long publisherCode = filter.getPublisherCode();
        LocalDateTime dateOfPublishingMax = filter.getDateOfPublishingMax();
        LocalDateTime dateOfPublishingMin = filter.getDateOfPublishingMin();
        Set<Long> stopPublisherCodeList = filter.getStopPublisherCodeList();

        Specification<Person> specification = (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();

            Root<Book> bookRoot = query.from(Book.class);
            Predicate joinCondition = cb.equal(root.get("id"), bookRoot.get("personId"));
            predicates.add(joinCondition);

            if (personId != null) {
                predicates.add(cb.equal(root.get("id"), personId));
            }
            if (pName != null && !pName.isBlank()) {
                predicates.add(cb.like(cb.upper(root.get("pName")), "%" + pName.toUpperCase().trim() + "%"));
            }
            if (age != null) {
                predicates.add(cb.equal(root.get("age"), age));
            }
            if (countryOfBorn != null && !countryOfBorn.isBlank()) {
                predicates.add(cb.like(cb.upper(root.get("countryOfBorn")), "%" + countryOfBorn.toUpperCase().trim() + "%"));
            }
            if (gender != null && !gender.isBlank()) {
                predicates.add(cb.like(cb.upper(root.get("gender")),  gender.toUpperCase().trim()));
            }
            if (favoriteList != null && !favoriteList.isEmpty()) {
                predicates.add(root.get("pName").in(favoriteList));
            }
            if (bookId != null) {
                predicates.add(cb.equal(bookRoot.get("id"), bookId));
            }
            if (pages != null) {
                predicates.add(cb.equal(bookRoot.get("pages"), pages));
            }
            if (title != null && !title.isBlank()) {
                predicates.add(cb.like(cb.upper(bookRoot.get("title")), "%" + title.toUpperCase().trim() + "%"));
            }
            if (isbn != null && !isbn.isBlank()) {
                predicates.add(cb.like(cb.upper(bookRoot.get("isbn")), "%" + isbn.toUpperCase().trim() + "%"));
            }
            if (picNumbers != null) {
                predicates.add(cb.equal(bookRoot.get("picNumbers"), picNumbers));
            } else if (picMax != null && picMin != null && picMax >= 0L && picMin >= 0L) {
                predicates.add(cb.between(bookRoot.get("picNumbers"), picMin, picMax));
            } else if (picMin != null && picMin >= 0L) {
                predicates.add(cb.greaterThanOrEqualTo(bookRoot.get("picNumbers"), picMin));
            } else if (picMax != null && picMax >= 0L) {
                predicates.add(cb.lessThanOrEqualTo(bookRoot.get("picNumbers"), picMax));
            }
            if (publisher != null && !publisher.isBlank()) {
                predicates.add(cb.like(cb.upper(bookRoot.get("publisher")), "%" + publisher.toUpperCase().trim() + "%"));
            }
            if (publisherCode != null) {
                predicates.add(cb.equal(bookRoot.get("publisherCode"), publisherCode));
            }
            if (dateOfPublishingMin != null && dateOfPublishingMax != null) {
                predicates.add(cb.between(bookRoot.get("dateOfPublishing"), dateOfPublishingMin, dateOfPublishingMax));
            } else if (dateOfPublishingMin != null && dateOfPublishingMax == null) {
                predicates.add(cb.greaterThanOrEqualTo(bookRoot.get("dateOfPublishing"), dateOfPublishingMin));
            } else if (dateOfPublishingMin == null && dateOfPublishingMax != null) {
                predicates.add(cb.lessThanOrEqualTo(bookRoot.get("dateOfPublishing"), dateOfPublishingMax));
            }
            if (stopPublisherCodeList != null && !stopPublisherCodeList.isEmpty()) {
                predicates.add(cb.not(bookRoot.get("publisherCode").in(stopPublisherCodeList)));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return repository.findAll(specification, pageRequest);
    }

    public Page<PersonW> findByPersonAndBookMappedSpecification(SearchValue searchValue) {
        Filter filter = searchValue.getFilter();
        PageParam pageParam = searchValue.getPageParam();
        PageRequest pageRequest = createPageRequest(pageParam);
        Long personId = filter.getPersonId();
        String pName = filter.getPName();
        Integer age = filter.getAge();
        String countryOfBorn = filter.getCountryOfBorn();
        Set<String> favoriteList = filter.getFavoriteList();
        String gender = filter.getGender();

        Long bookId = filter.getBookId();
        Long pages = filter.getPages();
        String title = filter.getTitle();
        String isbn = filter.getIsbn();
        Long picNumbers = filter.getPicNumbers();
        Long picMin = filter.getPicMin();
        Long picMax = filter.getPicMax();
        String publisher = filter.getPublisher();
        Long publisherCode = filter.getPublisherCode();
        LocalDateTime dateOfPublishingMax = filter.getDateOfPublishingMax();
        LocalDateTime dateOfPublishingMin = filter.getDateOfPublishingMin();
        Set<Long> stopPublisherCodeList = filter.getStopPublisherCodeList();

        Specification<PersonW> specification = (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();
            Join<PersonW, BookW> joinBookW = root.join("books", JoinType.INNER);
            if (personId != null) {
                predicates.add(cb.equal(root.get("id"), personId));
            }
            if (pName != null && !pName.isBlank()) {
                predicates.add(cb.like(cb.upper(root.get("pName")), "%" + pName.toUpperCase().trim() + "%"));
            }
            if (age != null) {
                predicates.add(cb.equal(root.get("age"), age));
            }
            if (countryOfBorn != null && !countryOfBorn.isBlank()) {
                predicates.add(cb.like(cb.upper(root.get("countryOfBorn")), "%" + countryOfBorn.toUpperCase().trim() + "%"));
            }
            if (gender != null && !gender.isBlank()) {
                predicates.add(cb.like(cb.upper(root.get("gender")),  gender.toUpperCase().trim()));
            }
            if (favoriteList != null && !favoriteList.isEmpty()) {
                predicates.add(root.get("pName").in(favoriteList));
            }
            if (bookId != null) {
                predicates.add(cb.equal(joinBookW.get("id"), bookId));
            }
            if (pages != null) {
                predicates.add(cb.equal(joinBookW.get("pages"), pages));
            }
            if (title != null && !title.isBlank()) {
                predicates.add(cb.like(cb.upper(joinBookW.get("title")), "%" + title.toUpperCase().trim() + "%"));
            }
            if (isbn != null && !isbn.isBlank()) {
                predicates.add(cb.like(cb.upper(joinBookW.get("isbn")), "%" + isbn.toUpperCase().trim() + "%"));
            }
            if (picNumbers != null) {
                predicates.add(cb.equal(joinBookW.get("picNumbers"), picNumbers));
            } else if (picMax != null && picMin != null && picMax >= 0L && picMin >= 0L) {
                predicates.add(cb.between(joinBookW.get("picNumbers"), picMin, picMax));
            } else if (picMin != null && picMin >= 0L) {
                predicates.add(cb.greaterThanOrEqualTo(joinBookW.get("picNumbers"), picMin));
            } else if (picMax != null && picMax >= 0L) {
                predicates.add(cb.lessThanOrEqualTo(joinBookW.get("picNumbers"), picMax));
            }
            if (publisher != null && !publisher.isBlank()) {
                predicates.add(cb.like(cb.upper(joinBookW.get("publisher")), "%" + publisher.toUpperCase().trim() + "%"));
            }
            if (publisherCode != null) {
                predicates.add(cb.equal(joinBookW.get("publisherCode"), publisherCode));
            }
            if (dateOfPublishingMin != null && dateOfPublishingMax != null) {
                predicates.add(cb.between(joinBookW.get("dateOfPublishing"), dateOfPublishingMin, dateOfPublishingMax));
            } else if (dateOfPublishingMin != null && dateOfPublishingMax == null) {
                predicates.add(cb.greaterThanOrEqualTo(joinBookW.get("dateOfPublishing"), dateOfPublishingMin));
            } else if (dateOfPublishingMin == null && dateOfPublishingMax != null) {
                predicates.add(cb.lessThanOrEqualTo(joinBookW.get("dateOfPublishing"), dateOfPublishingMax));
            }
            if (stopPublisherCodeList != null && !stopPublisherCodeList.isEmpty()) {
                predicates.add(cb.not(joinBookW.get("publisherCode").in(stopPublisherCodeList)));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return personWiredSpecificationRepository.findAll(specification, pageRequest);
    }

    private PageRequest createPageRequest(PageParam pageParam) {
        String sortDirection = pageParam.getSortDirection();
        Integer pageNumber = pageParam.getPageNumber();
        Integer pageSize = pageParam.getPageSize();
        Sort.Direction direction = (sortDirection == null
                || sortDirection.trim().length() == 0
                || sortDirection.trim().equals("asc"))
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, pageParam.getSortColumn(), "id");
        pageNumber = pageNumber == null ? 0 : pageNumber;
        pageSize = pageSize == null ? 5 : pageSize;
        return PageRequest.of(pageNumber, pageSize, sort);
    }
}
