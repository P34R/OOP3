package carrentspring.repos;

import com.example.carrentservlets.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

  List<Car> findAllByStatusIsAVAILABLE();

  List<Car> findAllByStatusIsBROKEN();

  List<Car> findAllByStatusIsRENTED();

  List<Car> findAllByStatusIsNotAVAILABLE();

  Optional<Car> findAllById(Integer id);
}
