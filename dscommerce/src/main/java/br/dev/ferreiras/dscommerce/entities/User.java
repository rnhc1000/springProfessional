package br.dev.ferreiras.dscommerce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tb_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(unique = true)
  private String email;

  private String phone;

  @Column(name = "birth_date")
  private LocalDate birthOfDate;

  private String password;

  @OneToMany(mappedBy = "client")
  private List<Order> orders = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "tb_user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(Long id, String name, String email, String phone, LocalDate birthOfDate, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.birthOfDate = birthOfDate;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public LocalDate getBirthOfDate() {
    return birthOfDate;
  }

  public void setBirthOfDate(LocalDate birthOfDate) {
    this.birthOfDate = birthOfDate;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Order> getOrders() {
    return orders;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;
    return id.equals(user.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "User{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", email='" + email + '\'' +
           ", phone='" + phone + '\'' +
           ", birthOfDate=" + birthOfDate +
           ", password='" + password + '\'' +
           '}';
  }

  public void addRole(Role role) {
    roles.add(role);
  }

  public boolean hasRole(String roleName) {

    for (Role role: roles) {
      if(role.getAuthority().equals(roleName)) {
        return true;
      }
    }
    return false;
  }
}
