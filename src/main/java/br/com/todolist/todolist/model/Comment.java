package br.com.todolist.todolist.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "task_comments")
public class Comment implements Serializable {
  @Id
  @Column
  @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")
  private Long id;

  @Column
  private String message;

  @ManyToOne
  @JoinColumn(name = "task_id")
  @JsonIgnoreProperties({"comments"})
  private Task task;

  @Override
  public String toString() {
    return "Comment{" +
        "id=" + id +
        ", message='" + message + '\'' +
        ", task=" + task +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Comment comment = (Comment) o;
    return id.equals(comment.id) && message.equals(comment.message) && task.equals(comment.task);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, message, task);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }
}
