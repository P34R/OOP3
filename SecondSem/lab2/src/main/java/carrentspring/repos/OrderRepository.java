package carrentspring.repos;

import com.example.carrentservlets.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

  List<Order> findAllByUserId(String userId);

  Optional<Order> findAllByUserIdAndCarIdAndEndDateIsNull(int userId, Integer carId);

}
