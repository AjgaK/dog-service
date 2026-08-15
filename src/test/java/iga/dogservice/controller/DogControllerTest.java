package iga.dogservice.controller;

import iga.dogservice.model.Dog;
import iga.dogservice.service.DogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DogController.class)
public class DogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DogService dogService;

    @Test
    void shouldReturnAllDogs() throws Exception {

        Dog dog = new Dog(
                1L,
                "Baki",
                "Shiba",
                9,
                true
        );

        when(dogService.getAllDogs())
                .thenReturn(List.of(dog));

        mockMvc.perform(get("/dogs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Baki"))
                .andExpect(jsonPath("$[0].breed").value("Shiba"))
                .andExpect(jsonPath("$[0].age").value(9))
                .andExpect(jsonPath("$[0].available").value(true));
    }

    @Test
    void shouldReturnDogById() throws Exception {

        Dog dog = new Dog(
                1L,
                "Baki",
                "Shiba",
                9,
                true
        );

        when(dogService.getDogById(1L))
                .thenReturn(dog);

        mockMvc.perform(get("/dogs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Baki"))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    void shouldCreateDog() throws Exception {

        Dog request = new Dog(
                null,
                "Baki",
                "Shiba",
                9,
                true
        );

        Dog savedDog = new Dog(
                1L,
                "Baki",
                "Shiba",
                9,
                true
        );

        when(dogService.createDog(any(Dog.class)))
                .thenReturn(savedDog);

        mockMvc.perform(post("/dogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Baki"));
    }

    @Test
    void shouldUpdateDog() throws Exception {

        Dog request = new Dog(
                null,
                "Maks",
                "Beagle",
                4,
                false
        );

        Dog updatedDog = new Dog(
                1L,
                "Maks",
                "Beagle",
                4,
                false
        );

        when(dogService.updateDog(any(Long.class), any(Dog.class)))
                .thenReturn(updatedDog);

        mockMvc.perform(put("/dogs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Maks"))
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void shouldDeleteDog() throws Exception {

        mockMvc.perform(delete("/dogs/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenNameIsEmpty() throws Exception {

        Dog invalidDog = new Dog(
                null,
                "",
                "Labrador",
                3,
                true
        );

        mockMvc.perform(post("/dogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDog)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenBreedIsEmpty() throws Exception {

        Dog invalidDog = new Dog(
                null,
                "Baki",
                "",
                3,
                true
        );

        mockMvc.perform(post("/dogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDog)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenAgeIsNegative() throws Exception {

        Dog invalidDog = new Dog(
                null,
                "Baki",
                "Shiba",
                -1,
                true
        );

        mockMvc.perform(post("/dogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDog)))
                .andExpect(status().isBadRequest());
    }
}
