package org.mpetrov.serialization.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class CacheInvocationHandler implements InvocationHandler {
    Object delegate;
    Map<String,Double> cache;

    public CacheInvocationHandler(Object delegate, Map<String,Double> cache) {
        this.delegate = delegate;
        this.cache = cache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(delegate, args);
        }
        if (cache.containsKey((String)args[0])) return cache.get((String)args[0]);
        Object invoke = method.invoke(delegate, args);
        cache.put((String) args[0],(Double) invoke);
        return invoke;
    }
}