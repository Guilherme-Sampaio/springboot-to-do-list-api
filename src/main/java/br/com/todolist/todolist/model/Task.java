package br.com.todolist.todolist.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

  @ManyToOne
  @JoinColumn(name = "project_id")
  @JsonIgnoreProperties({"tasks"})
  private Project project;

  @OneToMany(mappedBy = "task")
  private List<Comment> comments;

  @Override
  public String toString() {
    return "Task{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", done=" + done +
        ", project=" + project +
        ", comments=" + comments +
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

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
}
