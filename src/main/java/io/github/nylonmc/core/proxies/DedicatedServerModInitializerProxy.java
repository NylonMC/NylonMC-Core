package io.github.nylonmc.core.proxies;

import io.github.nylonmc.core.Core;
import net.fabricmc.api.DedicatedServerModInitializer;

public class DedicatedServerModInitializerProxy implements DedicatedServerModInitializer {
    private String module;

    public DedicatedServerModInitializerProxy(String module) {
        this.module = module;
    }

    @Override
    public void onInitializeServer() {
        Core.runPythonModule(module);
    }
    
}