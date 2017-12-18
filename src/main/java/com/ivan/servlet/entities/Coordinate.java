package com.ivan.servlet.entities;

public class Coordinate {

  private Integer id;
  private Double latitude;
  private Double longitude;
  private Integer routeId;
  private Route route;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Coordinate that = (Coordinate) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
    if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
    if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;
    return route != null ? route.equals(that.route) : that.route == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
    result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
    result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
    result = 31 * result + (route != null ? route.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Coordinate{" +
        "id=" + id +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", routeId=" + routeId +
        ", route=" + route +
        '}';
  }
}
