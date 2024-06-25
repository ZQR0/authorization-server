package com.testing.auth_server.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * Специльный сериализатор стэктрейса в Json для удобного отображения ошибок в API
 * */
@Component
public class StackElementJsonSerializer extends JsonSerializer<StackTraceElement[]> {
    @Override
    public void serialize(StackTraceElement[] stackTraceElements,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException
    {
        if (stackTraceElements != null) {
            jsonGenerator.writeStartArray();
            List<String> items = Arrays.stream(stackTraceElements)
                    .map(StackTraceElement::toString)
                    .toList();

            for (String item : items) {
                jsonGenerator.writeString(item);
            }

            jsonGenerator.writeEndArray();
        }
    }
}
