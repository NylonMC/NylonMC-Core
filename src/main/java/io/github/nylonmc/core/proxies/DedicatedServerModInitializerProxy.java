package io.github.nylonmc.core.proxies;

import io.github.nylonmc.core.Core;
import net.fabricmc.api.DedicatedServerModInitializer;

public class DedicatedServerModInitializerProxy extends BaseProxy implements DedicatedServerModInitializer {

    public DedicatedServerModInitializerProxy(String module) {
        super(module);
    }

    @Override
    public void onInitializeServer() {
        Core.runPythonModule(module);
    }
    
}