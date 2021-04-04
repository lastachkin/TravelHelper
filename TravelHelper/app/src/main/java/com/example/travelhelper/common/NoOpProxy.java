package com.example.travelhelper.common;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NoOpProxy implements InvocationHandler {
    private WeakReference<Object> view;
    public NoOpProxy(Object view) {
        this.view = new WeakReference<>(view);
    }
    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if(view == null || view.get() == null) {
            return null;
        }

        return method.invoke(view.get(), args);
    }
}
