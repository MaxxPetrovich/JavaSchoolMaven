package org.mpetrv.classloader.task1;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        URLClassLoader classLoaderByDir = getClassLoaderByDir(pluginRootDirectory, true);
        return (Plugin) classLoaderByDir.loadClass( pluginName + "." + pluginClassName).newInstance();
    }

    public static URLClassLoader getClassLoaderByDir(String directoryName, boolean parent) throws MalformedURLException {
        File dir = new File(directoryName);
        if (!parent) {
            return new URLClassLoader(new URL[]{dir.toURI().toURL()}, null);
        }
        return new URLClassLoader(new URL[]{dir.toURI().toURL()});
    }
}
