package br.com.todolist.todolist.controller;

import java.util.List;
import java.util.Optional;

import br.com.todolist.todolist.model.Task;
import br.com.todolist.todolist.repository.TaskRepository;
import br.com.todolist.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/task", consumes = "application/json", produces = "application/json")
public class TaskController {

  private final TaskRepository repository;
  private final TaskService service;

  @Autowired
  public TaskController(TaskRepository repository, TaskService service) {
    this.repository = repository;
    this.service = service;
  }

  @GetMapping(path = "/all")
  public List<Task> findAll() {
    return repository.findAll();
  }

  @GetMapping(path = "/project/{id}")
  public List<Task> findByProject(@PathVariable("id") Long id) {
    return repository.findByProjectIdOrderById(id);
  }

  @GetMapping(path = "/project/{id}/today")
  public List<Task> findTodayTasksByProject(@PathVariable("id") Long id) {
    return service.findTodayTasks(id);
  }

  @GetMapping(path = "/project/{id}/week")
  public List<Task> findNextWeekTasksByProject(@PathVariable("id") Long id) {
    return service.findWeekTasks(id);
  }

  @GetMapping(path = "/project/{id}/distant")
  public List<Task> findMoreThanOneWeekTasksByProject(@PathVariable("id") Long id) {
    return service.findMoreThanOneWeekTasks(id);
  }

  @GetMapping(path = "/project/today")
  public List<Task> findTodayTasksByProject() {
    return service.findTodayTasks(null);
  }

  @GetMapping(path = "/project/week")
  public List<Task> findNextWeekTasksByProject() {
    return service.findWeekTasks(null);
  }

  @GetMapping(path = "/project/distant")
  public List<Task> findMoreThanOneWeekTasksByProject() {
    return service.findMoreThanOneWeekTasks(null);
  }

  @GetMapping(path = "/project/")
  public List<Task> findTasksWithoutProject() {
    return repository.findByProjectIsNullOrderById();
  }

  @GetMapping(path = "/{id}")
  public Optional<Task> findById(@PathVariable("id") Long id) {
    return repository.findById(id);
  }

  @PostMapping
  public Task save(@RequestBody Task task) {
    return repository.save(task);
  }

  @PatchMapping(path = "/{id}/done")
  public Task setAsDone(@PathVariable("id") Long id) {
    Task task = repository.getById(id);
    task.setDone(true);

    return repository.save(task);
  }

  @PatchMapping(path = "/{id}/pending")
  public Task setAsPending(@PathVariable("id") Long id) {
    Task task = repository.getById(id);
    task.setDone(false);

    return repository.save(task);
  }

  @PutMapping
  public Task update(@RequestBody Task task) {
    return repository.save(task);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }


}
