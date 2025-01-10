package br.dev.ferreiras.dscommerce.services;

import br.dev.ferreiras.dscommerce.entities.User;
import br.dev.ferreiras.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserService userService;

  public AuthService(UserService userService) {
    this.userService = userService;
  }

  public void validateSelfOrAdmin(long userId) {

    User myself = userService.getAuthenticatedUser();

    if(!myself.hasRole("ROLE_ADMIN") && !myself.getId().equals((userId))) {
      throw new ForbiddenException("Access Denied!");
    }
  }
}
