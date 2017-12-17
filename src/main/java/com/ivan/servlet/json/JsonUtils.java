package com.ivan.servlet.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.ivan.servlet.entities.Route;
import com.ivan.servlet.entities.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUtils {

    public static void createUserJson(HttpServletResponse response, User user) throws IOException {
        JsonFactory factory = new JsonFactory();
        JsonGenerator jsonGenerator = factory.createGenerator(response.getWriter());

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

    public static void createRouteJson(HttpServletResponse response, Route route) throws IOException {
        JsonFactory factory = new JsonFactory();
        JsonGenerator jsonGenerator = factory.createGenerator(response.getWriter());

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

    public static void createErrorJson(HttpServletResponse response, int errorCode, String message) throws IOException {
        JsonFactory factory = new JsonFactory();
        JsonGenerator jsonGenerator = factory.createGenerator(response.getWriter());

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
}
