package br.dev.ferreiras.dscommerce.services;

import br.dev.ferreiras.dscommerce.entities.Role;
import br.dev.ferreiras.dscommerce.entities.User;
import br.dev.ferreiras.dscommerce.projections.UserDetailsProjection;
import br.dev.ferreiras.dscommerce.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

}

