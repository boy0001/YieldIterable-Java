package com.boydti.collection;

import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class YieldIterableExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        YieldIterable<String> yielder = new YieldIterable<>();

        Future<Boolean> future = executor.submit(() -> {
            try (YieldIterable<String> closeable = yielder) {
                closeable.accept("Hello");
                closeable.accept("World");
                closeable.accept("!");
            }
            return true;
        });
        yielder.setFuture(future);

        for (String word : yielder) {
            System.out.println(word);
        }
    }
}
