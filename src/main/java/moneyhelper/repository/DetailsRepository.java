package moneyhelper.repository;

import moneyhelper.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DetailsRepository extends JpaRepository<UserDetails, Long> {

}
