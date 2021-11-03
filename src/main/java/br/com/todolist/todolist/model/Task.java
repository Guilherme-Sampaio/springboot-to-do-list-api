package br.com.todolist.todolist.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
  @Id
  @Column
  @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
  private Long id;

  @Column
  private String title;

  @Column
  private boolean done;

  @Override
  public String toString() {
    return "Task{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", done=" + done +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Task task = (Task) o;
    return done == task.done && Objects.equals(id, task.id) && Objects.equals(title, task.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, done);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }
}
