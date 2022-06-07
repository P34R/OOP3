package carrentspring.service;

import carrentspring.repos.CarRepository;
import com.example.carrentservlets.model.Car;
import com.example.carrentservlets.model.CarStatus;

import java.util.List;

public class CarsServiceImpl {
    private final CarRepository carRepository;

    public CarsServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars(Boolean isAdmin) {
        return Boolean.TRUE.equals(isAdmin) ? carRepository.findAll() : carRepository.findAllByStatusIsAVAILABLE();
    }


    public List<Car> getCarsByStatus(CarStatus status) {
        if(status == CarStatus.AVAILABLE)
            return carRepository.findAllByStatusIsAVAILABLE();
        else if (status == CarStatus.BROKEN)
            return carRepository.findAllByStatusIsBROKEN();
        else
            return carRepository.findAllByStatusIsRENTED();
    }

    public void deleteCar(long id) {
        carRepository.deleteById((int)id);
    }

    public void addCar(Car car) {
        carRepository.save(car);
    }

}
