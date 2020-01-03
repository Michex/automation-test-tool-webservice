package com.toster.tosterbackend.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.Getter;

import java.io.File;

@Getter
public class Config {

    private final static String yamlSource = "src/main/resources/config.yaml";

    @JsonProperty("testSuiteFilePath")
    String testSuiteFilePath;

    @JsonProperty("testStatusFilePath")
    String testStatusFilePath;

    private static final Config INSTANCE = new Config();

    public static Config getConfig() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return Try.of(() -> {
            return mapper.readValue(new File(yamlSource), Config.class);
        }).onFailure(Throwable::printStackTrace).get();
    }

    private Config() {

    }

}
