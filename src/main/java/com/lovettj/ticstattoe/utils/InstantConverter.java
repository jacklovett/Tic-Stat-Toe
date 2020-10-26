package com.lovettj.ticstattoe.utils;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JsonSerializer for Instant.class
 */
public class InstantConverter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {

  private static final Logger logger = LoggerFactory.getLogger(InstantConverter.class);

  private static final DateTimeFormatter INSTANT_FORMATTER = DateTimeFormatter.ISO_INSTANT;

  public JsonElement serialize(Instant instant, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(INSTANT_FORMATTER.format(instant));
  }

  public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
    try {
      return INSTANT_FORMATTER.parse(json.getAsString(), Instant::from);
    } catch (JsonParseException e) {
      logger.error(e.getMessage());
    }
    return null;
  }
}