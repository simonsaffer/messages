package com.messages.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class ConfigUtil {

  public static final String KAFKA_PROPERTIES = "kafka.properties";

  public static Optional<Properties> readPropertiesFile(String path) {
    Properties properties = new Properties();
    try {
      properties.load(new FileInputStream("kafka.properties"));
      return Optional.of(properties);
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  public static Properties getKafkaProperties() {
    return readPropertiesFile(KAFKA_PROPERTIES).get(); // FIXME: 2016-05-19
  }
}
