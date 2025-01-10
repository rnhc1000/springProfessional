package br.dev.ferreiras.dscommerce.dto;

import java.time.LocalDate;
import java.util.Set;

public record UserDTO( String name, String email, String phone, LocalDate birthDate, Set<String> roles) {

}
