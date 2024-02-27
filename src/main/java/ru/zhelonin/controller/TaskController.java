package ru.zhelonin.controller;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zhelonin.domain.Task;
import ru.zhelonin.services.TaskService;

@Controller
@RequestMapping("/")
public class TaskController {
  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }
  @GetMapping("/")
  public String tasks(Model model,
                      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                      @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){

    List<Task> allTasks = taskService.getAll((page - 1) * limit, limit);
    model.addAttribute("tasks", allTasks);
    model.addAttribute("current_page", page);
    int totalPages = (int) Math.ceil(1.0 * taskService.countTask() / limit);
    if(totalPages > 1){
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
      model.addAttribute("page_numbers", pageNumbers);
    }
    return "tasks";
  }

  @PostMapping("/{id}")
  public String edit(Model model,
                     @PathVariable Integer id,
                     @RequestBody TaskInfo info){
    if(isNull(id) || id < 0){
      throw new RuntimeException("Invalid id");
    }

    taskService.edit(id, info.getDescription(), info.getStatus());
    return tasks(model, 1, 10);
  }

  @PostMapping("/")
  public String add(Model model,
                    @RequestBody TaskInfo info){

    taskService.create(info.getDescription(), info.getStatus());
    return tasks(model, 1, 10);
  }

  @DeleteMapping("/{id}")
  public String delete(Model model,
                       @PathVariable("id") Integer id){
    if(isNull(id) || id < 0){
      throw new RuntimeException("Invalid id");
    }
    taskService.delete(id);
    return tasks(model, 1, 10);
  }

}
