package io.github.nylonmc.core.proxies;

import org.beeware.rubicon.Python;

import io.github.nylonmc.core.Core;
import net.fabricmc.api.ModInitializer;

public class ModInitializerProxy implements ModInitializer {
    private String module;

    public ModInitializerProxy(String module) {
        this.module = module;
    }

    @Override
    public void onInitialize() {
        Core.runPythonModule(module);
        Python.stop();
    }
    
}