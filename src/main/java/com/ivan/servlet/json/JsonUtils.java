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
          jsonGenerator.writeNumberField("id", user.getId());
        } else {
          jsonGenerator.writeNullField("id");
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
        } else {
            jsonGenerator.writeNullField("id");
        }
    }
}
