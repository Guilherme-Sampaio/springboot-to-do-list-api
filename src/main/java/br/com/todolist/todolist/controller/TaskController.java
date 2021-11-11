package br.com.todolist.todolist.controller;

import java.util.List;
import java.util.Optional;

import br.com.todolist.todolist.model.Task;
import br.com.todolist.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/task", consumes = "application/json", produces = "application/json")
public class TaskController {

  private final TaskRepository repository;

  @Autowired
  public TaskController(TaskRepository repository) {
    this.repository = repository;
  }

  @GetMapping(path = "/all")
  public List<Task> findAll() {
    return repository.findAll();
  }

  @GetMapping(path = "/project/{id}")
  public List<Task> findByProject(@PathVariable("id") Long id) {
    return repository.findByProjectId(id);
  }

  @GetMapping(path = "/{id}")
  public Optional<Task> findById(@PathVariable("id") Long id) {
    return repository.findById(id);
  }

  @PostMapping
  public Task save(@RequestBody Task task) {
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
  @PatchMapping(path = "/{id}/set-as-done", produces = MediaType.APPLICATION_JSON_VALUE)
  public Task setAsDone(@PathVariable("id") Long id) {
    var task = repository.findById(id).orElseThrow(IllegalArgumentException::new);
    task.setDone(true);
    return repository.save(task);
  }
  @PatchMapping(path = "/{id}/set-as-pending", produces = MediaType.APPLICATION_JSON_VALUE)
  public Task setAsPending(@PathVariable("id") Long id) {
    var task = repository.findById(id).orElseThrow(IllegalArgumentException::new);
    task.setDone(false);
    return repository.save(task);
  }



}
