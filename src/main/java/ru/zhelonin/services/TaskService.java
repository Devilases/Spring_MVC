package ru.zhelonin.services;

import static java.util.Objects.isNull;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ru.zhelonin.dao.TaskDAO;
import org.springframework.stereotype.Service;
import ru.zhelonin.domain.Status;
import ru.zhelonin.domain.Task;

@Service
public class TaskService {
  private final TaskDAO taskDAO;

  public TaskService(TaskDAO taskDAO) {
    this.taskDAO = taskDAO;
  }

  public List<Task> getAll(int offset, int limit){
    return taskDAO.getAll(offset,limit);
  }

  public Task getById(int id){
    return taskDAO.getById(id);
  }

  public int countTask(){
    return taskDAO.getAllCount();
  }

  @Transactional
  public Task edit(int id, String description, Status status){
    Task byId = taskDAO.getById(id);
    if(isNull(byId)){
      throw new RuntimeException("Not found");
    }
    byId.setDescription(description);
    byId.setStatus(status);
    taskDAO.saveOrUpdate(byId);
    return byId;
  }

  public Task create(String description, Status status){
    Task task = new Task();
    task.setDescription(description);
    task.setStatus(status);
    taskDAO.saveOrUpdate(task);
    return task;
  }

  @Transactional
  public void delete(int id){
    Task byId = taskDAO.getById(id);
    if(isNull(byId)){
      throw new RuntimeException("Not found");
    }
    taskDAO.delete(byId);
  }

}
