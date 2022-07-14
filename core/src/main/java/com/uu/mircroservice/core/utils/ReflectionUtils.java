package com.uu.mircroservice.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtils {
    public static <T> T getFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        T value = (T) field.get(object);
        return value;
    }

    public static <E> E copyValue(Object fromObject, Class<E> eClass){
        Field[] allFields = eClass.getDeclaredFields();
        E newInstance;
        try {
            newInstance = eClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
        for (Field field : allFields) {
            try {
                var val = getFieldValue(fromObject, field.getName());
                field.setAccessible(true);
                field.set(newInstance, val);
            } catch (Exception ignored) {
            }
        }
        return newInstance;
    }
}
