package br.dev.ferreiras.dscommerce.projections;

import java.time.LocalDate;

public interface UserDetailsProjection {

  public String getUsername();
  public String getPassword();
  public String getName();
  public Long getRoleId();
  public String getAuthority();
  public String getPhone();
  public LocalDate getBirthDate();
}
