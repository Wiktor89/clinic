package com.clinic.model;

import com.clinic.model.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@ApiModel(value = "Пользователь")
@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends EntityHistory {

  @ApiModelProperty(notes = "Уникальный идентификатор id", required = true)
  @Id
  @Column(name = "ID", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ApiModelProperty(notes = "Логин", required = true)
  @Column(name = "LOGIN", nullable = false, unique = true)
  @NotNull
  private String login;

  @ApiModelProperty(notes = "Пароль", required = true)
  @Column(name = "PASSWORD", nullable = false, unique = true)
  @NotNull
  private String password;

  @ApiModelProperty(notes = "Признак ативности УЗ", required = true)
  @Column(name = "IS_ACTIVE", nullable = false)
  @NotNull
  private Boolean isActive;

  @ApiModelProperty(notes = "Роли пользователя", required = true)
  @ManyToMany
  @JoinTable(name = "USER_ROLE",
      joinColumns = @JoinColumn(name = "USER_ID"),
      inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
  private Set<Role> roles;

  @OneToOne(mappedBy = "user")
  private Employees employees;
}
