package com.messages.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

  public static final String KAFKA_PROPERTIES = "kafka.properties";

  public static Properties readPropertiesFile(String path) throws IOException {
    Properties properties = new Properties();
    properties.load(new FileInputStream("kafka.properties"));
    return properties;
  }

  public static Properties getKafkaProperties(String groupId) throws IOException {
    Properties properties = readPropertiesFile(KAFKA_PROPERTIES);
    properties.setProperty("group.id", groupId);
    return properties;
  }
}
