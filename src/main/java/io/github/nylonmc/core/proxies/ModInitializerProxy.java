package io.github.nylonmc.core.proxies;

import io.github.nylonmc.core.Core;
import net.fabricmc.api.ModInitializer;

public class ModInitializerProxy extends BaseProxy implements ModInitializer {

    public ModInitializerProxy(String module) {
        super(module);
    }

    @Override
    public void onInitialize() {
        Core.runPythonModule(module);
    }
    
}