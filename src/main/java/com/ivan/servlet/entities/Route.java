package com.ivan.servlet.entities;

import java.util.Date;

public class Route {

  private Integer id;
  private Date date;
  private Integer userId;
  private String name;
  private User user;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Route route = (Route) o;

    if (id != null ? !id.equals(route.id) : route.id != null) return false;
    if (date != null ? !date.equals(route.date) : route.date != null) return false;
    if (userId != null ? !userId.equals(route.userId) : route.userId != null) return false;
    if (name != null ? !name.equals(route.name) : route.name != null) return false;
    return user != null ? user.equals(route.user) : route.user == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (date != null ? date.hashCode() : 0);
    result = 31 * result + (userId != null ? userId.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (user != null ? user.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Route{" +
        "id=" + id +
        ", date=" + date +
        ", userId=" + userId +
        ", name=" + name +
        ", user=" + user +
        '}';
  }
}
