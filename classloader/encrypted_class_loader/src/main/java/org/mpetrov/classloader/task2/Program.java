package org.mpetrov.classloader.task2;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IOException {
        EncryptedClassLoader encryptedClassLoader = new EncryptedClassLoader(10, "C:\\Intel\\", ClassLoader.getSystemClassLoader());

        //comment out when file is encrypted
        encryptedClassLoader.encryptClassFromFS("plugin.dir1.dir2.Plugin2");

        Class<?> clazz = encryptedClassLoader.findClass("plugin.dir1.dir2.Plugin2");
        System.out.println(clazz.getMethod("doUseful").getReturnType());
    }
}