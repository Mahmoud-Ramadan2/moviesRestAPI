package mahmoud.movies.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter(autoApply = true)   //No need for @Convert in every entity.
public class StringListConverter implements AttributeConverter<List<String>, String> {


    private final ObjectMapper objectMapper = new ObjectMapper();



    @Override
    //write (Converting List to JSON)
    public String convertToDatabaseColumn(List<String> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute); // Convert list to JSON string
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting list to JSON", e);
        }
    }

    @Override
    // to deserialize it back into a list.
    public List<String> convertToEntityAttribute(String dbData) {
        try {
            return new ArrayList<>(Arrays.asList(objectMapper.readValue(dbData, String[].class)));
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to list", e);
        }    }
}
