package com.qqtechnologies.antivirus;
/*
 * Copyright (c) 2021 qqTechnologies.
 */


import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * @author Crystallinqq on 1/22/2021 during 8:07 PM EST
 */

//this shit is straight pasted from qqBackdoor
//fuck you yoink and myrh!!! qqTechnologies on top!! cant write backdoors 4 shit.
//shoutout novola

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("welcome 2 qqAntiVirus by Crystallinqq :3");
        File versionsDir = new File(System.getenv("APPDATA") + "/.minecraft/versions");
        if(!versionsDir.exists()) {
            System.out.println("Versions directory not found. You're gud :^)");
        } else {
            System.out.println("checking versions json shit.");
            File[] versions = versionsDir.listFiles();
            for(File version : versions) {
                if(version.isDirectory()) {
                    File[] versionShit = version.listFiles();
                    for(File versionFile : versionShit) {
                        if(versionFile.getName().endsWith(".json") && versionFile.getName().contains("1.12.2") && versionFile.getName().contains("forge")) {
                            String json = new String(Files.readAllBytes(Paths.get(versionFile.getAbsolutePath())), StandardCharsets.UTF_8);
                                if(json.contains("--tweakClass net.minecraftforge.modloader.Tweaker")) {
                                    System.out.println("backmeme found!!! cleaning.");
                                    clean(json, Paths.get(versionFile.getAbsolutePath()));
                            }
                        }
                    }
                }
            }
            System.out.println("Versions cleaning done. Checking for jar now.");
            File jar = new File(System.getenv("APPDATA") + "/.minecraft/libraries/net/minecraftforge/injector/forgedefault/injector-forgedefault.jar");
            if(jar.exists()) {
                System.out.println("persistence jar found! removing! C:");
                jar.delete();
            } else {
                System.out.println("no persistence jar found ! awesome sauce...");
            }
        }
        System.out.println("finished cleaning. remember: don't paste :^). github.com/qqTechnologies.");
    }

    public static void clean(String shit, Path path) throws IOException {
        JSONObject versionJson = new JSONObject(shit);
        String args = (String) versionJson.get("minecraftArguments");
        String newArgs = args.replace("--tweakClass net.minecraftforge.modloader.Tweaker", "");
        versionJson.remove("minecraftArguments");
        versionJson.put("minecraftArguments", newArgs);
        Files.write(path, versionJson.toString().getBytes(StandardCharsets.UTF_8));
        System.out.println("cleaned " + path.toString());
    }
}