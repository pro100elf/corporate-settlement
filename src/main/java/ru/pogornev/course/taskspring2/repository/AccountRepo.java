package ru.pogornev.course.taskspring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pogornev.course.taskspring2.model.Account;
import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {//vs CrudRepository
    @Query(value = """
            SELECT a.id, a.account_pool_id, a.account_number, p.branch_code, p.currency_code, p.mdm_code, p.priority_code
            , p.registry_type_code
               FROM account a JOIN account_pool p on p.id = a.account_pool_id
               ORDER by a.id
            """
            , nativeQuery = true)
    Iterable<Account> getAccounts();

    @Query(value = """
            SELECT a.id, a.account_number
               FROM account a JOIN account_pool p on p.id = a.account_pool_id
               WHERE p.branch_code = ?1
                 AND p.currency_code = ?2
                 AND p.mdm_code = ?3
                 AND p.priority_code = ?4
                 AND p.registry_type_code = ?5
                ORDER BY ID
                LIMIT 1
            """
            , nativeQuery = true)
    List<Object[]> getAccountByRequest(String branchCode, String currencyCode, String mdmCode, String priorityCode, String registryTypeCode);
}
