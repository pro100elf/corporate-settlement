package ru.pogornev.course.taskspring2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.pogornev.course.taskspring2.model.TppProduct;
import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<TppProduct, Long> {
    @Query(value = """
            SELECT *
               FROM tpp_product
               WHERE number = ?1 limit 1
            """
            , nativeQuery = true)
    Integer existsTp(String contractNumber);

    @Query(value = """
            SELECT value
               FROM tpp_ref_product_register_type
               WHERE product_class_code = ?1
                AND account_type = 'Клиентский'
            """
            , nativeQuery = true)
    List<Object[]> getRegistersByProductCode(String ProductCode);

}
