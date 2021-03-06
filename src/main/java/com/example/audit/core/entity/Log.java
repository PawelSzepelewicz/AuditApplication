package com.example.audit.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "logs")
@EqualsAndHashCode(callSuper = true)
public class Log extends AbstractEntity {
    private String action;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_user", referencedColumnName = "id")
    private User subjectUser;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "object_user", referencedColumnName = "id")
    private User objectUser;
    private LocalDateTime actionTime;
}
