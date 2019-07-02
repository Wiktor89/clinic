package com.clinic.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ApiModel(value = "Сотрудники история")
@Entity
@Table(name = "employees")
@Data
@EqualsAndHashCode(callSuper = false)
public class Employees extends EntityHistory {

  @ApiModelProperty(notes = "Уникальный идентификатор id", required = true)
  @Id
  @Column(name = "id", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ApiModelProperty(notes = "Категория работника (из справочника)")
  @Column(name = "CATEGORY")
  private String category;

  @ApiModelProperty(notes = "Фамилия", required = true)
  @Column(name = "LAST_NAME", nullable = false)
  @NotNull
  private String lastName;

  @ApiModelProperty(notes = "Имя", required = true)
  @Column(name = "FIRST_NAME", nullable = false)
  @NotNull
  private String firstName;

  @ApiModelProperty(notes = "Отчество")
  @Column(name = "MIDDLE_NAME")
  private String middleName;

  @ApiModelProperty(notes = "Дата рождения")
  @Column(name = "BIRTH_DAY")
  private LocalDate birthDay;

  @ApiModelProperty(notes = "Пользователь")
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
  @JsonIgnore
  private User user;

  @ApiModelProperty(notes = "Контакты пользователя")
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "CONTACT_ID", referencedColumnName = "ID")
  private Contact contact;
}
