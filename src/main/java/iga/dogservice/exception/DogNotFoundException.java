package iga.dogservice.exception;

public class DogNotFoundException extends RuntimeException {
    public DogNotFoundException(Long id) {
        super("Dog with id " + id + " was not found");
    }
}
