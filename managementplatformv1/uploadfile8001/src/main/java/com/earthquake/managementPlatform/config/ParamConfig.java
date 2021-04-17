package com.earthquake.managementPlatform.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties("data-source")
public class ParamConfig {
    public List<String> li = new ArrayList<String>();

    public List<String> getLi() {
        return li;
    }

    public void setLi(List<String> li) {
        this.li = li;
    }

    public List<String> predictionLi = new ArrayList<>();

    public void setPredictionLi(List<String> predictionLi) {
        this.predictionLi = predictionLi;
    }

    public List<String> getPredictionLi() {
        return predictionLi;
    }
}
