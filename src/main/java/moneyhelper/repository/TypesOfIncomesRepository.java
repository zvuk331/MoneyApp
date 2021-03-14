package moneyhelper.repository;

import moneyhelper.entity.TypesOfIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesOfIncomesRepository extends JpaRepository<TypesOfIncome, Long> {

}
