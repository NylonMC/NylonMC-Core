package io.github.nylonmc.core;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import org.beeware.rubicon.Python;

public class Core implements ModInitializer {
	public static final String NYLONMC_FOLDER = FabricLoader.getInstance().getGameDir().toString() + File.separator
			+ "nylonmc";
	public static final String NYLONMC_PATH_FOLDER = NYLONMC_FOLDER + File.separator + "python_path";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
		NativeHack.addNativePath(NYLONMC_FOLDER + File.separator + "native");
		if (Python.init(null, NYLONMC_PATH_FOLDER + ":.", null) != 0) {
			System.out.println("Error initializing Python VM.");
		}
		if (Python.run("thetest", new String[0]) != 0) {
			System.out.println("Error running Python script.");
		}
		System.out.println("Python Loaded");
		Python.stop();
	}
}
