package carrentspring.web;

import carrentspring.service.OrderServiceImpl;
import com.example.carrentservlets.model.Order;
import com.example.carrentservlets.model.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController implements UserInformationSecurityContextHolder {
  private final OrderServiceImpl service;

  @GetMapping
  @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
  public List<Order> orders(Principal principal) {
    String clientId = null;
    if (getRoles().contains("CLIENT")) {
      clientId = principal.getName();
    }
    return service.orders(clientId).stream().collect(Collectors.toList());
  }

  @GetMapping("/car")
  @PreAuthorize("hasRole('CLIENT')")
  public void makeOrder(Order order, Principal principal) {
//    final var order = converter.toModel(orderDto);
//    order.setClientId(principal.getName());
    service.orderCar(order);
  }

  @GetMapping("/car/pay")
  @PreAuthorize("hasRole('CLIENT')")
  public void pay(Order order, BigInteger fine, Principal principal) {
    service.completeOrder(order);
    //user.addFine(fine);
  }

  @GetMapping("/car/reject")
  @PreAuthorize("hasRole('ADMIN')")
  public void reject(Order order, Principal principal) {
    service.addFine(order);
  }

}
