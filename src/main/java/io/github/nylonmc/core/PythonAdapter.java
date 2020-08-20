package io.github.nylonmc.core;

import io.github.nylonmc.core.proxies.ClientModInitializerProxy;
import io.github.nylonmc.core.proxies.DedicatedServerModInitializerProxy;
import io.github.nylonmc.core.proxies.ModInitializerProxy;
import io.github.nylonmc.core.proxies.PreLaunchEntrypointProxy;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.LanguageAdapter;
import net.fabricmc.loader.api.LanguageAdapterException;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class PythonAdapter implements LanguageAdapter {

    @Override
    public <T> T create(ModContainer mod, String value, Class<T> type) throws LanguageAdapterException {
        if (type.equals(ModInitializer.class)) {
            return type.cast(new ModInitializerProxy(value));
        }
        if (type.equals(ClientModInitializer.class)) {
            return type.cast(new ClientModInitializerProxy(value));
        }
        if (type.equals(DedicatedServerModInitializer.class)) {
            return type.cast(new DedicatedServerModInitializerProxy(value));
        }
        if (type.equals(PreLaunchEntrypoint.class)) {
            return type.cast(new PreLaunchEntrypointProxy(value));
        }
        return null;
    }

}