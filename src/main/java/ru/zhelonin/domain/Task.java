package ru.zhelonin.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "task")
@Table(schema = "todo", name = "task")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "description")
  private String description;

  @Column(name = "status")
  @Enumerated(EnumType.ORDINAL)
  private Status status;


  public Task(String description, Status status) {
    this.description = description;
    this.status = status;
  }

  public Task() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Task{");
    sb.append("description='").append(description).append('\'');
    sb.append(", status=").append(status);
    sb.append('}');
    return sb.toString();
  }


}
