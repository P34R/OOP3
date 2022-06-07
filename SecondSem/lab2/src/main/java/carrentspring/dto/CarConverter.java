package carrentspring.dto;


import carrentspring.dto.model.CarDto;
import com.example.carrentservlets.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter implements Converter<Car, CarDto> {

  @Override
  public CarDto toDto(Car model) {
    return new CarDto((int) model.getId(), model.getModel(), model.getColor(), model.getPrice(), model.getStatus());
  }

  @Override
  public Car toModel(CarDto dto) {
    return new Car(dto.getId(), dto.getModel(), dto.getColor(), dto.getPrice(), dto.getStatus());
  }
}
