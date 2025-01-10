package br.dev.ferreiras.dscommerce.services;

import br.dev.ferreiras.dscommerce.dto.UserDTO;
import br.dev.ferreiras.dscommerce.entities.Role;
import br.dev.ferreiras.dscommerce.entities.User;
import br.dev.ferreiras.dscommerce.projections.UserDetailsProjection;
import br.dev.ferreiras.dscommerce.repositories.UserRepository;
import br.dev.ferreiras.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDetails findUserAndRoleByEmail(String username) {

    List<UserDetailsProjection> details = userRepository.searchUserAndRolesByEmail(username);

    User user = new User();
    user.setEmail(username);
    user.setPassword(details.getFirst().getPassword());
    user.setBirthDate(details.getFirst().getBirthDate());
    user.setName(details.getFirst().getName());
    user.setPhone(details.getFirst().getPhone());
    for (UserDetailsProjection projection : details) {
      user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
    }

    return user;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);

    User user = new User();

    user.setEmail(username);
    user.setPassword(result.getFirst().getPassword());
    for (UserDetailsProjection projection : result) {
      user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
    }

    return user;
  }

  protected User getAuthenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
    String username = jwtPrincipal.getClaim("username");

    return userRepository.findByEmail(username).orElseThrow(
        () -> new ResourceNotFoundException("User not found!!!")
    );
  }

  @Transactional(readOnly = true)
  public UserDTO getUserAuthenticated() {
    User user = getAuthenticatedUser();
    Set<String> roles = new HashSet<>();

    for (GrantedAuthority role : user.getRoles()) {
      roles.add(role.getAuthority());
    }

    return new UserDTO(user.getName(), user.getEmail(), user.getPhone(), user.getBirthDate(), roles);
  }

}

