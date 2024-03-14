package ru.pogornev.course.taskspring2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.pogornev.course.taskspring2.model.TppProductRegister;

@Repository
public interface TppProductRegisterRepo extends CrudRepository<TppProductRegister, Long> {
    @Query(value = """
            SELECT exists(SELECT *
               FROM tpp_product_register
               WHERE product_id = ?1 AND type = ?2)
            """
            , nativeQuery = true)
    boolean existTpr(long instanceId, String registryTypeCode);

    @Query(value = """
            SELECT exists(SELECT *
               FROM tpp_ref_product_register_type
               WHERE value = ?1)
            """
            , nativeQuery = true)
    boolean existTprType(String registryTypeCode);
}
