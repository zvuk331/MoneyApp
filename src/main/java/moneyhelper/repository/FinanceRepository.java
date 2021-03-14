package moneyhelper.repository;

import moneyhelper.entity.UserFinance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceRepository extends JpaRepository<UserFinance, Long> {

}
