package org.example;

/**
 * Main file where injection is called.
 */
public class Main {
    public static void main(String[] args) {
        SomeBean sb = (new Injector().inject(new SomeBean()));
        sb.foo();
    }
}




