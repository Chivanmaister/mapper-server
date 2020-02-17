package com.ivan.servlet.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.ivan.servlet.entities.Coordinate;
import com.ivan.servlet.entities.Route;
import com.ivan.servlet.entities.User;
import com.ivan.servlet.entities.details.History;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class JsonUtils {

  private static final JsonFactory jsonFactory = new JsonFactory();

  public static void createUserResponse(StringWriter response, User user) throws IOException {
    JsonGenerator jsonGenerator = jsonFactory.createGenerator(response);

    jsonGenerator.writeStartObject();
    if (user != null) {
      if (user.getId() != null) {
        jsonGenerator.writeNumberField("id", user.getId());
      } else {
        jsonGenerator.writeNullField("id");
      }
      if (user.getEmail() != null) {
        jsonGenerator.writeStringField("email", user.getEmail());
      } else {
        jsonGenerator.writeNullField("email");
      }
    } else {
      jsonGenerator.writeNullField("id");
      jsonGenerator.writeNullField("email");
    }
    jsonGenerator.writeEndObject();
    jsonGenerator.close();
  }

  public static void createRouteResponse(StringWriter response, Route route) throws IOException {
    JsonGenerator jsonGenerator = jsonFactory.createGenerator(response);

    jsonGenerator.writeStartObject();
    if (route != null) {
      if (route.getId() != null) {
        jsonGenerator.writeNumberField("id", route.getId());
      } else {
        jsonGenerator.writeNullField("id");
      }
      if (route.getDate() != null) {
        jsonGenerator.writeNumberField("date", route.getDate().getTime());
      } else {
        jsonGenerator.writeNullField("date");
      }
      if (route.getName() != null) {
        jsonGenerator.writeStringField("name", route.getName());
      } else {
        jsonGenerator.writeNullField("name");
      }
      if (route.getUserId() != null) {
        jsonGenerator.writeNumberField("userId", route.getUserId());
      } else {
        jsonGenerator.writeNullField("userId");
      }
    } else {
      jsonGenerator.writeNullField("id");
      jsonGenerator.writeNullField("date");
      jsonGenerator.writeNullField("name");
      jsonGenerator.writeNullField("userId");
    }
    jsonGenerator.writeEndObject();
    jsonGenerator.close();
  }

  public static void createErrorResponse(StringWriter response, int errorCode, String message) throws IOException {
    JsonGenerator jsonGenerator = jsonFactory.createGenerator(response);

    jsonGenerator.writeStartObject();
    if (errorCode != 0) {
      jsonGenerator.writeNumberField("code", errorCode);
    } else {
      jsonGenerator.writeNullField("code");
    }
    if (message != null) {
      jsonGenerator.writeStringField("message", message);
    } else {
      jsonGenerator.writeNullField("message");
    }
    jsonGenerator.writeEndObject();
    jsonGenerator.close();
  }

  public static void createHistoryResponse(StringWriter response, List<History> histories) throws IOException {
    JsonGenerator jsonGenerator = jsonFactory.createGenerator(response);

    jsonGenerator.writeStartArray();
    for (History history : histories) {
      jsonGenerator.writeStartObject();
      if (history.getRoute() != null) {
        if (history.getRoute().getId() != null) {
          jsonGenerator.writeNumberField("id", history.getRoute().getId());
        } else {
          jsonGenerator.writeNullField("id");
        }
        if (history.getRoute().getDate() != null) {
          jsonGenerator.writeNumberField("date", history.getRoute().getDate().getTime());
        } else {
          jsonGenerator.writeNullField("date");
        }
        if (history.getRoute().getName() != null) {
          jsonGenerator.writeStringField("name", history.getRoute().getName());
        } else {
          jsonGenerator.writeNullField("name");
        }
        if (history.getRoute().getUserId() != null) {
          jsonGenerator.writeNumberField("userId", history.getRoute().getUserId());
        } else {
          jsonGenerator.writeNullField("userId");
        }
      } else {
        jsonGenerator.writeNullField("id");
        jsonGenerator.writeNullField("date");
        jsonGenerator.writeNullField("name");
        jsonGenerator.writeNullField("userId");
      }
      jsonGenerator.writeArrayFieldStart("coordinates");
      for (Coordinate coordinate : history.getCoordinates()) {
        jsonGenerator.writeStartObject();
        if (coordinate != null) {
          if (coordinate.getId() != null) {
            jsonGenerator.writeNumberField("id", coordinate.getId());
          } else {
            jsonGenerator.writeNullField("id");
          }
          if (coordinate.getLatitude() != null) {
            jsonGenerator.writeNumberField("latitude", coordinate.getLatitude());
          } else {
            jsonGenerator.writeNullField("latitude");
          }
          if (coordinate.getLongitude() != null) {
            jsonGenerator.writeNumberField("longitude", coordinate.getLongitude());
          } else {
            jsonGenerator.writeNullField("longitude");
          }
        } else {
          jsonGenerator.writeNullField("id");
          jsonGenerator.writeNullField("latitude");
          jsonGenerator.writeNullField("longitude");
        }
        jsonGenerator.writeEndObject();
      }
      jsonGenerator.writeEndArray();
      jsonGenerator.writeEndObject();
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.close();
  }

  public static void createPingResponse(HttpServletResponse response, String ping) throws IOException {
    JsonGenerator jsonGenerator = jsonFactory.createGenerator(response.getWriter());

    jsonGenerator.writeStartObject();
    if (ping != null) {
      jsonGenerator.writeStringField("hello", "world");
    } else {
      jsonGenerator.writeNullField("hello");
    }
    jsonGenerator.writeEndObject();
    jsonGenerator.close();
  }

  public static void createCoordinateResponse(StringWriter response, Coordinate coordinate) throws IOException {
    JsonGenerator jsonGenerator = jsonFactory.createGenerator(response);

    jsonGenerator.writeStartObject();
    if (coordinate != null) {
      if (coordinate.getId() != null) {
        jsonGenerator.writeNumberField("id", coordinate.getId());
      } else {
        jsonGenerator.writeNullField("id");
      }
      if (coordinate.getLatitude() != null) {
        jsonGenerator.writeNumberField("latitude", coordinate.getLatitude());
      } else {
        jsonGenerator.writeNullField("latitude");
      }
      if (coordinate.getLongitude() != null) {
        jsonGenerator.writeNumberField("longitude", coordinate.getLongitude());
      } else {
        jsonGenerator.writeNullField("longitude");
      }
      if (coordinate.getRouteId() != null) {
        jsonGenerator.writeNumberField("routeId", coordinate.getRouteId());
      } else {
        jsonGenerator.writeNullField("routeId");
      }
    } else {
      jsonGenerator.writeNullField("id");
      jsonGenerator.writeNullField("latitude");
      jsonGenerator.writeNullField("longitude");
      jsonGenerator.writeNullField("routeId");
    }
    jsonGenerator.writeEndObject();
    jsonGenerator.close();
  }
}
