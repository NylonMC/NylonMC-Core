package io.github.nylonmc.core;

import io.github.nylonmc.core.proxies.ModInitializerProxy;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.LanguageAdapter;
import net.fabricmc.loader.api.LanguageAdapterException;
import net.fabricmc.loader.api.ModContainer;

public class PythonAdapter implements LanguageAdapter {

    @Override
    public <T> T create(ModContainer mod, String value, Class<T> type) throws LanguageAdapterException {
        if (type.equals(ModInitializer.class)) {
            return type.cast(new ModInitializerProxy(value));
        }
        return null;
    }

}