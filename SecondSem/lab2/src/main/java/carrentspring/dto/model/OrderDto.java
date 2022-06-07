package carrentspring.dto.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder(toBuilder = true)
@Value(staticConstructor = "of")
@JsonInclude(NON_NULL)
public class OrderDto {
  @NotNull
  String userId;
  @NotNull
  Integer carId;
  @NonNull Integer cost;
  @NonNull Integer fine;
  @NonNull Long dateStart;
  @NonNull Long dateEnd;

  @JsonCreator
  public OrderDto(@NotNull String clientId, @NotNull Integer carId,
                  @NotNull Integer cost, @NotNull Integer fine,
                  @NotNull Long dateStart, @NotNull Long dateEnd) {
    this.userId = clientId;
    this.carId = carId;
    this.cost = cost;
    this.fine = fine;
    this.dateStart = dateStart;
    this.dateEnd = dateEnd;
  }

}