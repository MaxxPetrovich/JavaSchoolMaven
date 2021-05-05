package org.mpetrv.classloader.task1;

import java.net.MalformedURLException;

public class Program {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PluginManager manager = new PluginManager("C:\\Intel");
        manager.load("plugin", "Plugin1").doUseful();
    }
}
