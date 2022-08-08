package com.uu.microservice.coreproxy.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Column(columnDefinition = "Timestamp default CURRENT_TIMESTAMP")
    private ZonedDateTime createdAt = ZonedDateTime.now();
    @Column(columnDefinition = "Timestamp default CURRENT_TIMESTAMP")
    private ZonedDateTime modifiedAt = ZonedDateTime.now();
    private String creator;
    private String updater;
    private Integer creatorId = 0;
    private Integer updaterId = 0;
    @Column(columnDefinition = "Boolean default TRUE")
    private Boolean active = true;
}
