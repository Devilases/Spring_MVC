package ru.zhelonin.controller;

import ru.zhelonin.domain.Status;

public class TaskInfo {
  private String description;
  private Status status;

  public TaskInfo(String description, Status status) {
    this.description = description;
    this.status = status;
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
}
