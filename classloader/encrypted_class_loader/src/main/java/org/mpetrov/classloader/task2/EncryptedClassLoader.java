package org.mpetrov.classloader.task2;

import java.io.*;

public class EncryptedClassLoader extends ClassLoader {
    private final int key;
    private final String dir;

    public EncryptedClassLoader(int key, String dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            String classPath = className.replaceAll("[.]", "\\\\");
            byte[] b = fetchClassFromFS(dir +
                    classPath.substring(0, classPath.lastIndexOf("\\")) + "\\"
                    + className.substring(className.lastIndexOf('.') + 1) + ".class");
            for (int i = 0; i < b.length; i++) {
                b[i] ^= key;
            }
            return defineClass(className, b, 0, b.length);
        } catch (IOException ex) {
            return super.findClass(className);
        }

    }

    private byte[] fetchClassFromFS(String path) throws IOException {
        InputStream is = new FileInputStream(path);

        // Get the size of the file
        long length = new File(path).length();

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + path);
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public void encryptClassFromFS(String className) throws IOException {
        String classPath = className.replaceAll("[.]", "\\\\");
        String s = dir +
                classPath.substring(0, classPath.lastIndexOf("\\")) + "\\"
                + className.substring(className.lastIndexOf('.') + 1) + ".class";
        byte[] bytes = fetchClassFromFS(s);
        OutputStream os = new FileOutputStream(s);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= key;
        }
        os.write(bytes, 0, bytes.length);
        os.close();
    }
}

