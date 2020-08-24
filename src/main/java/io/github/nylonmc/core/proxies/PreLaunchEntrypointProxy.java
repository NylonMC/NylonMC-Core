package io.github.nylonmc.core.proxies;

import io.github.nylonmc.core.Core;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class PreLaunchEntrypointProxy extends BaseProxy implements PreLaunchEntrypoint {

    public PreLaunchEntrypointProxy(String module) {
        super(module);
    }

    @Override
    public void onPreLaunch() {
        Core.runPythonModule(module);
    }
    
}