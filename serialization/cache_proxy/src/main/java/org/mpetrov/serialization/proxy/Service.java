package org.mpetrov.serialization.proxy;

public interface Service {
    @Cache
    double doHardWork(String name, double number);
}
