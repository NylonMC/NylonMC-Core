package io.github.nylonmc.core.proxies;

import io.github.nylonmc.core.Core;
import net.fabricmc.api.ClientModInitializer;

public class ClientModInitializerProxy extends BaseProxy implements ClientModInitializer {

    public ClientModInitializerProxy(String module) {
        super(module);
    }

    @Override
    public void onInitializeClient() {
        Core.runPythonModule(module);
    }
    
}