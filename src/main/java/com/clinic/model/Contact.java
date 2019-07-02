package com.clinic.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@ApiModel(value = "Контакты пользователя")
@Entity
@Table(name = "contact")
@Data
@EqualsAndHashCode(callSuper = false)
public class Contact extends EntityHistory {

  @ApiModelProperty(notes = "Уникальный идентификатор id", required = true)
  @Id
  @Column(name = "id", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ApiModelProperty(notes = "E-mail")
  @Column(name = "EMAIL")
  private String email;

  @ApiModelProperty(notes = "Раб. телефон")
  @Column(name = "WORK_PHONE")
  private String workPhone;

  @ApiModelProperty(notes = "Вн. телефон")
  @Column(name = "INNER_PHONE")
  private String innerPhone;

  @ApiModelProperty(notes = "Сотовый телефон")
  @Column(name = "MOBILE_PHONE")
  private String mobilePhone;

  @OneToOne(mappedBy = "contact")
  @NotNull
  private Employees employees;
}
