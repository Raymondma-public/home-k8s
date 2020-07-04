package com.ma.raymond.k8smanagerservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VMConfigs {
    String dockerBuildString;
    String k8sConfig;

    public VMConfigs(String dockerBuildString, String k8sConfig) {
        this.dockerBuildString = dockerBuildString;
        this.k8sConfig = k8sConfig;
    }
}
