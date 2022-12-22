package org.example;

/**
 * SomeBean class, where exists field1, field2, but not initialized anywhere.
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;
    @AutoInjectable
    private SomeOtherInterface field2;
    public void foo(){
        field1.doSomething();
        field2.doSomeOther();
    }
}
