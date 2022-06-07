package carrentspring.web;

import carrentspring.dto.CarConverter;
import carrentspring.dto.model.CarDto;
import carrentspring.repos.CarRepository;
import carrentspring.service.CarsServiceImpl;
import com.example.carrentservlets.constants.URL;
import com.example.carrentservlets.dao.CarDao;
import com.example.carrentservlets.dao.impl.CarDaoImpl;
import com.example.carrentservlets.model.Car;
import com.example.carrentservlets.model.CarStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CarController implements UserInformationSecurityContextHolder {
  private final CarsServiceImpl service;
  private final CarConverter converter;

  @GetMapping
  @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
  @RequestMapping("/getCars")
  protected List<Car> doGet(@RequestParam Integer id, CarStatus status, @RequestParam Boolean admin) throws IOException {
    if(id!=null) {
      service.deleteCar(Long.valueOf(id));
      System.out.println("Done removing car");
    }
    if(status != null)
      return service.getCars(admin);
    else
      return service.getCarsByStatus(status);
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
  @RequestMapping("/save")
  protected void doPost(String model, String color, BigDecimal price, Integer id, HttpServletResponse response) throws IOException {
    if(id!=null) {
      service.addCar(new Car(id, model, color, price, CarStatus.AVAILABLE));
      System.out.println("Done updating car");
    } else {
      service.addCar(new Car(model, color, price, CarStatus.AVAILABLE));
      System.out.println("Done adding car");
    }
  }

}
