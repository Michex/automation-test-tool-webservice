package com.toster.webservices.testconfig;

import com.toster.webservices.testconfig.model.Config;
import com.toster.webservices.testconfig.model.NewConfig;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Config addConfig(@PathVariable("id") Long testSuiteId, @RequestBody NewConfig newConfig) {
        return configService.addConfig(testSuiteId, newConfig);
    }
}
