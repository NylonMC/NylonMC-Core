package io.github.nylonmc.core;

import java.util.HashMap;
import java.util.function.Function;

import io.github.nylonmc.core.proxies.BaseProxy;
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
    private static HashMap<Class, Function<String, BaseProxy>> proxies = new HashMap<>();

    static {
        putProxy(ModInitializer.class, ModInitializerProxy::new);
        putProxy(ClientModInitializer.class, ClientModInitializerProxy::new);
        putProxy(DedicatedServerModInitializer.class, DedicatedServerModInitializerProxy::new);
        putProxy(PreLaunchEntrypoint.class, PreLaunchEntrypointProxy::new);
    }

    public static void putProxy(Class classs, Function<String, BaseProxy> proxyCreator) {
        proxies.put(classs, proxyCreator);
    }

    @Override
    public <T> T create(ModContainer mod, String value, Class<T> type) throws LanguageAdapterException {
        Function<String, BaseProxy> a = proxies.get(type);
        if (a == null) return null;
        return type.cast(a.apply(value));
    }

}