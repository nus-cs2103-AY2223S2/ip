package cbot.command;

import java.time.format.DateTimeParseException;

@FunctionalInterface
interface ThrowingBiFunction<T, U, R> {
    R apply(T t, U u) throws DateTimeParseException, PoorInputException;
}
