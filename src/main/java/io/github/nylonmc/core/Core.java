package io.github.nylonmc.core;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.util.logging.LogManager;

import org.beeware.rubicon.Python;

public class Core implements ModInitializer {
	public static final String NYLONMC_FOLDER = FabricLoader.getInstance().getGameDir().toString() + File.separator
			+ "nylonmc";
	public static final String NYLONMC_PATH_FOLDER = NYLONMC_FOLDER + File.separator + "python_path";

	static {
		System.out.println("Hello Fabric world!");
		NativeHack.addNativePath(NYLONMC_FOLDER + File.separator + "native");
		if (Python.init(null, NYLONMC_PATH_FOLDER + ":.", null) != 0) {
			System.out.println("Error initializing Python VM.");
		}
		// if (Python.run("thetest", new String[0]) != 0) {
		// 	System.out.println("Error running Python script.");
		// }
		runPythonModule("thetest");
		System.out.println("Python Loaded");
	}

	/**
	 * Runs a python module. Using this method ensures python will be statically loaded.
	 * @param module Module to run __main__ of
	 */
	public static void runPythonModule(String module) {
		if (Python.run(module, new String[0]) != 0) {
			System.out.println("Error running Python script.");
		}
	}

	@Override
	public void onInitialize() {
		//Python.stop(); //Cause Python Print statments to work?
	}
}
