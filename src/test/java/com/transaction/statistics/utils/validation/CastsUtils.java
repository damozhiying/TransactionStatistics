package com.transaction.statistics.utils.validation;

public class CastsUtils {
    public static <T> Class<T> cast(Class<?> target) {
        return (Class<T>) target;
    }
}
