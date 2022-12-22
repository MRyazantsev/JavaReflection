package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Class to inject.
 */
public class Injector  {
    /**
     * Variable with file name of property file
     */
    private String file_with_properties = "properties";

    /**
     * a parameterized method that initializes fields marked with an annotation with instances of classes
     * that are specified as implementation corresponding interface in some file settings.
     * @param someObject Object of any class to inject.
     * @return The object after injection.
     * @param <T> Type of object.
     */
    public <T> T inject(T someObject)
    {
        try
        {
            Properties properties = new Properties();
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream in = classloader.getResourceAsStream(file_with_properties);
            properties.load(in);
            for (Field field : someObject.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(AutoInjectable.class)) {
                    field.setAccessible(true);
                    Class<?> cls = Class.forName(properties.getProperty(field.getType().getName()));
                    Object object = cls.getDeclaredConstructor().newInstance();
                    field.set(someObject,object);
                }
            }
        }
        catch (IOException|ClassNotFoundException|NoSuchMethodException|
               IllegalAccessException|InstantiationException|InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return someObject;
    }
}
