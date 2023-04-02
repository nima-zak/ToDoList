package com.zak.tododb.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private String taskName;
    private LocalDateTime startTime;
    private String owner;
}
