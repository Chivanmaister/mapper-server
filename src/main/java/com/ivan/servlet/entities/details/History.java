package com.ivan.servlet.entities.details;

import com.ivan.servlet.entities.Coordinate;
import com.ivan.servlet.entities.Route;

import java.util.LinkedList;
import java.util.List;

public class History {

  private Integer routeId;
  private Route route;
  private List<Integer> coordinateIds = new LinkedList<>();
  private List<Coordinate> coordinates = new LinkedList<>();

  public Integer getRouteId() {
    return routeId;
  }

  public void setRouteId(Integer routeId) {
    this.routeId = routeId;
  }

  public Route getRoute() {
    return route;
  }

  public void setRoute(Route route) {
    this.route = route;
  }

  public List<Integer> getCoordinateIds() {
    return coordinateIds;
  }

  public void setCoordinateIds(List<Integer> coordinateIds) {
    this.coordinateIds = coordinateIds;
  }

  public List<Coordinate> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    History history = (History) o;

    if (routeId != null ? !routeId.equals(history.routeId) : history.routeId != null) return false;
    if (route != null ? !route.equals(history.route) : history.route != null) return false;
    if (coordinateIds != null ? !coordinateIds.equals(history.coordinateIds) : history.coordinateIds != null)
      return false;
    return coordinates != null ? coordinates.equals(history.coordinates) : history.coordinates == null;
  }

  @Override
  public int hashCode() {
    int result = routeId != null ? routeId.hashCode() : 0;
    result = 31 * result + (route != null ? route.hashCode() : 0);
    result = 31 * result + (coordinateIds != null ? coordinateIds.hashCode() : 0);
    result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "History{" +
        "routeId=" + routeId +
        ", route=" + route +
        ", coordinateIds=" + coordinateIds +
        ", coordinates=" + coordinates +
        '}';
  }
}
