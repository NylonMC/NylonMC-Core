package io.github.nylonmc.core;

import java.lang.reflect.Field;
import java.util.Arrays;

public class NativeHack {
    private NativeHack(){}

    // https://stackoverflow.com/questions/15409223/adding-new-paths-for-native-libraries-at-runtime-in-java
    public static void addNativePath(String pathToAdd) {
        try {
            final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
            usrPathsField.setAccessible(true);

            //get array of paths
            final String[] paths = (String[])usrPathsField.get(null);

            //check if the path to add is already present
            for(String path : paths) {
                if(path.equals(pathToAdd)) {
                    return;
                }
            }

            //add the new path
            final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
            newPaths[newPaths.length-1] = pathToAdd;
            usrPathsField.set(null, newPaths);
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
        
    }
}