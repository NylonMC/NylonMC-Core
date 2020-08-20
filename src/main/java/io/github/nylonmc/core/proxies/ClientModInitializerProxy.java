package io.github.nylonmc.core.proxies;

import io.github.nylonmc.core.Core;
import net.fabricmc.api.ClientModInitializer;

public class ClientModInitializerProxy implements ClientModInitializer {
    private String module;

    public ClientModInitializerProxy(String module) {
        this.module = module;
    }

    @Override
    public void onInitializeClient() {
        Core.runPythonModule(module);
    }
    
}