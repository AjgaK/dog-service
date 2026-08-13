package iga.dogservice.service;

import iga.dogservice.model.Dog;
import iga.dogservice.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogService {
    private final DogRepository dogRepository;

    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    public Dog getDogById(Long id) {
        return dogRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Dog with id " + id + " not found"));
    }

    public Dog createDog(Dog dog) {
        return dogRepository.save(dog);
    }

    public Dog updateDog(Long id, Dog updatedDog) {
        Dog dog = getDogById(id);

        dog.setName(updatedDog.getName());
        dog.setBreed(updatedDog.getBreed());
        dog.setAge(updatedDog.getAge());
        dog.setAvailable(updatedDog.isAvailable());

        return dogRepository.save(dog);
    }

    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }
}
