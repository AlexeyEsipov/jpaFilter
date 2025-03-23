package ru.job4j.jpafilter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.job4j.jpafilter.model.wiredmodel.PersonW;

public interface PersonWiredSpecificationRepository extends JpaRepository<PersonW, Long>, JpaSpecificationExecutor<PersonW> {
}
