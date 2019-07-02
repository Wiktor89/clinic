package com.clinic.model.security;

import com.clinic.model.EntityHistory;
import com.clinic.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ROLES")
@ApiModel(value = "Роли")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Role extends EntityHistory {

  @ApiModelProperty(notes = "Уникальный идентификатор записи (id)", required = true)
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;

  @ApiModelProperty(notes = "Название роли", required = true)
  @Column(name = "ROLE_NAME")
  @NotNull
  private String roleName;

  @ApiModelProperty(notes = "Описание")
  @Column(name = "DESCRIPTION")
  private String description;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users;
}
