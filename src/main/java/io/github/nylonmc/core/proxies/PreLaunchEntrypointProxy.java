package io.github.nylonmc.core.proxies;

import io.github.nylonmc.core.Core;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class PreLaunchEntrypointProxy implements PreLaunchEntrypoint {
    private String module;

    public PreLaunchEntrypointProxy(String module) {
        this.module = module;
    }

    @Override
    public void onPreLaunch() {
        Core.runPythonModule(module);
    }
    
}