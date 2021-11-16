package br.com.todolist.todolist.controller;

import java.util.List;
import java.util.Optional;

import br.com.todolist.todolist.model.Comment;
import br.com.todolist.todolist.repository.CommentRepository;
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
@RequestMapping(value = "/comment", consumes = "application/json", produces = "application/json")
public class CommentController {

  private final CommentRepository repository;

  @Autowired
  public CommentController(CommentRepository repository) {
    this.repository = repository;
  }

  @GetMapping(path = "/all")
  public List<Comment> findAll() {
    return repository.findAll();
  }

  @GetMapping(path = "/task/{id}")
  public List<Comment> findByTaskId(@PathVariable("id") Long id) {
    return repository.findByTaskIdOrderById(id);
  }

  @GetMapping(path = "/{id}")
  public Optional<Comment> findById(@PathVariable("id") Long id) {
    return repository.findById(id);
  }

  @PostMapping
  public Comment save(@RequestBody Comment comment) {
    return repository.save(comment);
  }

  @PutMapping
  public Comment update(@RequestBody Comment comment) {
    return repository.save(comment);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }


}
