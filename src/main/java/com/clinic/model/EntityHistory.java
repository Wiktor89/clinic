package com.clinic.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityHistory implements Serializable {

  @ApiModelProperty(notes = "Кем создано")
  @Column(name = "created_by", updatable = false)
  @CreatedBy
  private String createdBy;

  @ApiModelProperty(notes = "Когда создано")
  @Column(name = "created_date", updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;

  @ApiModelProperty(notes = "Кем изменено")
  @Column(name = "modified_by")
  @LastModifiedBy
  private String modifiedBy;

  @ApiModelProperty(notes = "Когда изменено")
  @Column(name = "modified_date")
  @LastModifiedDate
  private LocalDateTime modifiedDate;
}
