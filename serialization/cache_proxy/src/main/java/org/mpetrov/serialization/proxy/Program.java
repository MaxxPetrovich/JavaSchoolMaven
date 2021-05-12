package org.mpetrov.serialization.proxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Program {
    public static void main(String[] args) {
        Map<String, Double> cache = new HashMap<>();
        Service delegate = new HardWorkService();
        Service memoryHardWorkService = (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheInvocationHandler(delegate, cache));
        run(memoryHardWorkService);
    }

    static void run(Service service) {
        long start = System.currentTimeMillis();
        double r1 = service.doHardWork("work1", 10); //считает результат
        System.out.println("time:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        double r2 = service.doHardWork("work2", 5);  //считает результат
        System.out.println("time:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        double r3 = service.doHardWork("work1", 10); //результат из кеша
        System.out.println("time:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        double r4 = service.doHardWork("work3", 10); //результат из кеша
        System.out.println("time:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        double r5 = service.doHardWork("work4", 10); //результат из кеша
        System.out.println("time:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        double r6 = service.doHardWork("work3", 10); //результат из кеша
        System.out.println("time:" + (System.currentTimeMillis() - start));
    }


}
