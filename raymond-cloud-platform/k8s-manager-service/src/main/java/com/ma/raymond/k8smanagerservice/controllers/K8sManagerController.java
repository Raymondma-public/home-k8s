package com.ma.raymond.k8smanagerservice.controllers;

import com.ma.raymond.k8smanagerservice.models.VMConfigs;
import com.ma.raymond.k8smanagerservice.models.httpObject.ResponseDTO;
import com.ma.raymond.k8smanagerservice.utils.StreamGobbler;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;

@RestController("/")
public class K8sManagerController {

    @GetMapping("/getVMConfig")
    public ResponseDTO buildVM(@RequestParam("token") String token, @RequestParam("vmCode") String vmCode) {
        //token authen

        //user author

        //Get project
//        File file = new File("vmCode");
//        boolean dirCreated = file.mkdir();
        File file = new File(vmCode);
        boolean fileExists = file.exists();
        if (!fileExists) {
            return new ResponseDTO("", "VM Config not exist", "", "temp instance", "helpUrl", null);
        }

        String dockerfileString = "";
        try {
            dockerfileString = new String(Files.readAllBytes(Paths.get(vmCode + "/Dockerfile")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String k8sConfigString = "";
        try {
            k8sConfigString = new String(Files.readAllBytes(Paths.get(vmCode + "/k8sConfig.yaml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        VMConfigs vmConfigs = new VMConfigs(dockerfileString, k8sConfigString);
        return new ResponseDTO("", "VM Config get", "", "temp instance", "helpUrl", vmConfigs);
    }

    @PostMapping("/buildVM")
    public String buildVM(@RequestParam("token") String token, @RequestParam("vmCode") String vmCode, @RequestParam("dockerBuildString") String dockerBuildString, @RequestParam("k8sConfig") String k8sConfig) throws IOException, InterruptedException {
        //token authen

        //user author

        //Get project
        File file = new File(vmCode);
        boolean dirCreated = file.mkdir();
        BufferedWriter writer = new BufferedWriter(new FileWriter(vmCode + "/Dockerfile"));
        writer.write(dockerBuildString);
        writer.close();

        writer = new BufferedWriter(new FileWriter(vmCode + "/k8sConfig.yaml"));
        writer.write(k8sConfig);
        writer.close();

        //Docker build
//        Process process = Runtime.getRuntime().exec(String.format("docker build -t "+vmCode+" .", vmCode));
//        StreamGobbler streamGobbler =
//                new StreamGobbler(process.getInputStream(), System.out::println);
//        Executors.newSingleThreadExecutor().submit(streamGobbler);
//        int exitCode = process.waitFor();
//        assert exitCode == 0;

        Process process = Runtime.getRuntime().exec(String.format("kubectl apply -f k8sConfig.yaml", vmCode));
        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exitCode = process.waitFor();
        assert exitCode == 0;

        return dockerBuildString + "\n" + k8sConfig;
    }

    @RequestMapping("/vmStatus")
    public String getVMStatus() {
        return "VM Status";
    }

    @RequestMapping("/pauseVM")
    public String pauseVM() {
        return "pauseVM";
    }

    @RequestMapping("/startVM")
    public String startVM() {
        return "startVM";
    }
}
