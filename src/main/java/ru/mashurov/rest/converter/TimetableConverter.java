package ru.mashurov.rest.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mashurov.rest.model.Timetable;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TimetableConverter implements AttributeConverter<Timetable, String> {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Timetable attribute) {

		try {
			return objectMapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Timetable convertToEntityAttribute(String dbData) {

		try {
			return objectMapper.readValue(dbData, Timetable.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
