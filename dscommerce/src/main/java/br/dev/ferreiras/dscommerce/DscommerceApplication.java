package br.dev.ferreiras.dscommerce;

import br.dev.ferreiras.dscommerce.projections.UserDetailsProjection;
import br.dev.ferreiras.dscommerce.repositories.UserRepository;
import br.dev.ferreiras.dscommerce.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class DscommerceApplication implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(DscommerceApplication.class);
  private final PasswordEncoder passwordEncoder;

  private final UserService userService;
  private final UserRepository userRepository;

  public DscommerceApplication(PasswordEncoder passwordEncoder, UserService userService, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
    this.userRepository = userRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(DscommerceApplication.class, args);
  }


  @Override
  public void run(String... args) throws Exception {

    if (logger.isInfoEnabled()) {
      logger.info("Password 123456 encoded: {}", passwordEncoder.encode("123456"));
    }

    if (passwordEncoder.matches("123456", "$2a$10$7caEfVz3DK36OKHQBfnKr.aFoFVX1u41lLR9rNed8YNzDx20FIt2m")) {
      logger.info("Encode/Decode Process OK!");
    } else {
      logger.info("Error encoding/decoding password");
    }

    String email = "alex@gmail.com";
    List<UserDetailsProjection> user = userRepository.searchUserAndRolesByEmail(email);

    for (UserDetailsProjection details : user) {

      logger.info("Username: {} Password: {}, Id: {}, Authority: {}",
          details.getUsername(), details.getPassword(), details.getRoleId(), details.getAuthority());
    }

//    logger.info("Username: {} Password: {}, BirthDate: {}, Name: {}",
//        user.getEmail(), user.getPassword(), user.getBirthDate(), user.getName());
  }

}

