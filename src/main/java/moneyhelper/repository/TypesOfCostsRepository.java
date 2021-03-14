package moneyhelper.repository;

import moneyhelper.entity.TypesOfCosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypesOfCostsRepository extends JpaRepository<TypesOfCosts, Long> {

}
