package iga.dogservice.service;

import iga.dogservice.exception.DogNotFoundException;
import iga.dogservice.model.Dog;
import iga.dogservice.repository.DogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DogServiceTest {
    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private DogService dogService;

    @Test
    void shouldReturnDogById() {
        Dog dog = new Dog(
                1L,
                "Baki",
                "Shiba",
                9,
                true
        );
        when(dogRepository.findById(1L)).thenReturn(Optional.of(dog));
        Dog result = dogService.getDogById(1L);
        assertEquals("Baki", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenDogDoesNotExist() {
        when(dogRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                DogNotFoundException.class,
                () -> dogService.getDogById(99L)
        );
    }
}
