package carrentspring.repos;

import com.example.carrentservlets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
  List<User> findAllByClientId(String clientId);
}
