package carrentspring.service;

import carrentspring.repos.CarRepository;
import carrentspring.repos.OrderRepository;
import com.example.carrentservlets.model.Car;
import com.example.carrentservlets.model.CarStatus;
import com.example.carrentservlets.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {

  private final CarRepository carRepository;
  private final OrderRepository orderRepository;

  public List<Order> orders(String UserId) {
    return isNull(UserId) ? orderRepository.findAll() : orderRepository.findAllByUserId(UserId);
  }

  public void orderCar(Order order) {
    final var car = carRepository.findById((int) order.getCarId()).orElseThrow(() -> new EntityNotFoundException("car not found"));
    order = new Order(order.getId(), order.getUserId(), order.getCarId(), Instant.now().toEpochMilli(), null);

    orderRepository.saveAndFlush(order);
  }

  public void completeOrder(Order order) {
    var car = carRepository.findById((int) order.getCarId()).orElseThrow(() -> new EntityNotFoundException("car not found or unavailable"));
    var orderDb = orderRepository.findAllByUserIdAndCarIdAndEndDateIsNull((int) order.getUserId(), (int) order.getCarId()).orElseThrow(() -> new EntityNotFoundException("order not found or payed"));

    car = new Car(car.getId(), car.getModel(), car.getColor(), car.getPrice(), CarStatus.AVAILABLE);
    carRepository.saveAndFlush(car);

    orderDb = new Order(order.getId(), order.getUserId(), order.getCarId(), order.getStartDate(), Instant.now().toEpochMilli());

    orderRepository.saveAndFlush(orderDb);
  }

  public void addFine(Order order) {
    var orderDb = orderRepository.findAllByUserIdAndCarIdAndEndDateIsNull((int) order.getUserId(), (int) order.getCarId()).orElseThrow(() -> new EntityNotFoundException("order not found or rejected re payed"));

    orderDb = new Order(order.getId(), order.getUserId(), order.getCarId(), order.getStartDate(), Instant.now().toEpochMilli());

    //add fine to user

    orderRepository.saveAndFlush(orderDb);
  }
}
