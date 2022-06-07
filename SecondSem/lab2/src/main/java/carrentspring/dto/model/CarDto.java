package carrentspring.dto.model;

import com.example.carrentservlets.model.CarStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder(toBuilder = true)
@Value(staticConstructor = "of")
@JsonInclude(NON_NULL)
public class CarDto {
  @Null Integer id;
  @NotBlank
  @NotNull
  String model;

  String color;
  BigDecimal price;
  CarStatus status;

  @JsonCreator
  public CarDto(@Null Integer id,
                @NotBlank @NotNull String model,
                @NotBlank @NonNull String color,
                @NonNull BigDecimal price, @NonNull CarStatus status) {
    this.id = id;
    this.model = model;
    this.color = color;
    this.price = price;
    this.status = status;
  }
}
