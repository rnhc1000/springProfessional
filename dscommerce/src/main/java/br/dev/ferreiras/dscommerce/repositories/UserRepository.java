package br.dev.ferreiras.dscommerce.repositories;

import br.dev.ferreiras.dscommerce.entities.User;
import br.dev.ferreiras.dscommerce.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


  @Query(nativeQuery = true, value =
      """
          SELECT tb_user.email AS username, tb_user.password, tb_user.birth_date, tb_user.name,
          tb_role.id AS roleId, tb_role.authority
          FROM tb_user
          INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
          INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
          WHERE tb_user.email = ?1
          """
  )
  List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
