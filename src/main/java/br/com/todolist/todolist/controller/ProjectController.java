package br.com.todolist.todolist.controller;

import java.util.List;
import java.util.Optional;

import br.com.todolist.todolist.model.Project;
import br.com.todolist.todolist.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/project", consumes = "application/json", produces = "application/json")
public class ProjectController {

  private final ProjectRepository repository;

  @Autowired
  public ProjectController(ProjectRepository repository) {
    this.repository = repository;
  }

  @GetMapping(path = "/all")
  public List<Project> findAll() {
    return repository.findAll();
  }

  @GetMapping(path = "/{id}")
  public Optional<Project> findById(@PathVariable("id") Long id) {
    return repository.findById(id);
  }

  @PostMapping
  public Project save(@RequestBody Project project) {
    return repository.save(project);
  }

  @PutMapping
  public Project update(@RequestBody Project project) {
    return repository.save(project);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
