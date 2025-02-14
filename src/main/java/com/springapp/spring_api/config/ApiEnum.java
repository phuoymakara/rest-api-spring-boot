package com.springapp.spring_api.config;

public enum ApiEnum {
  V1("v1"),
  V2("v2");

  private final String version;

  ApiEnum(String version) {
      this.version = version;
  }

  public String getVersion() {
      return version;
  }
}
