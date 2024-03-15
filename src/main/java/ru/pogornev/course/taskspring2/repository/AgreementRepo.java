package ru.pogornev.course.taskspring2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.pogornev.course.taskspring2.model.Agreement;

@Repository
public interface AgreementRepo extends CrudRepository<Agreement, Long> {
}
