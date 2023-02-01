package com.robsil.erommerce.util;

import java.util.Optional;
import java.util.function.Supplier;

public class ThrowableUtil {

    public static <T> Optional<T> ofThrowable(Supplier<T> supplier) {
        try {
            return Optional.ofNullable(supplier.get());
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

}
