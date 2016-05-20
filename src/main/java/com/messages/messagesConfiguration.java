package com.messages;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import be.tomcools.dropwizard.websocket.WebsocketBundleConfiguration;
import be.tomcools.dropwizard.websocket.WebsocketConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class MessagesConfiguration extends Configuration implements WebsocketBundleConfiguration {

  @NotEmpty
  private String template;

  @NotEmpty
  private String defaultName = "Stranger";

  @JsonProperty
  public String getTemplate() {
    return template;
  }

  @JsonProperty
  public void setTemplate(String template) {
    this.template = template;
  }

  @JsonProperty
  public String getDefaultName() {
    return defaultName;
  }

  @JsonProperty
  public void setDefaultName(String name) {
    this.defaultName = name;
  }

  @Valid
  @NotNull
  private DataSourceFactory database = new DataSourceFactory();

  @JsonProperty("database")
  public void setDataSourceFactory(DataSourceFactory factory) {
    this.database = factory;
  }

  @JsonProperty("database")
  public DataSourceFactory getDataSourceFactory() {
    return database;
  }

  @Valid
  @NotNull
  @JsonProperty
  private final WebsocketConfiguration websocketConfiguration = new WebsocketConfiguration();

  @Override
  public WebsocketConfiguration getWebsocketConfiguration() {
    return websocketConfiguration;
  }
}
