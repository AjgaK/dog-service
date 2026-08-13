package iga.dogservice.controller;

import iga.dogservice.model.Dog;
import iga.dogservice.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
@RequiredArgsConstructor
public class DogController {
    private final DogService dogService;

    @GetMapping
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @GetMapping("/id")
    public Dog getDogById(@PathVariable Long id) {
        return dogService.getDogById(id);
    }

    @PostMapping
    public Dog createDog(@RequestBody Dog dog) {
        return dogService.createDog(dog);
    }

    @PutMapping("/id")
    public Dog updateDog(@PathVariable Long id, @RequestBody Dog dog) {
        return dogService.updateDog(id, dog);
    }

    @DeleteMapping("/id")
    public void deleteDog(@PathVariable Long id) {
        dogService.deleteDog(id);
    }
}
