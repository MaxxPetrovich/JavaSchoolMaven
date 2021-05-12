package org.mpetrov.serialization.proxy;

public class HardWorkService implements Service {
    @Override
    public double doHardWork(String name, double number) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return number;
    }
}
