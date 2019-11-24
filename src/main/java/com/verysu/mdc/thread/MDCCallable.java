package com.verysu.mdc.thread;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Cocodroid
 * @create 2019-11-25 0:15
 */
public class MDCCallable<V> implements Callable<V> {

    private final Callable<V> callable;

    private transient final Map<String, String> _cm = MDC.getCopyOfContextMap();

    public MDCCallable(Callable<V> callable) {
        this.callable = callable;
    }

    @Override
    public V call() throws Exception {
        if (_cm != null) {
            MDC.setContextMap(_cm);
        }
        try {
            return callable.call();
        } finally {
            MDC.clear();
        }
    }
}
