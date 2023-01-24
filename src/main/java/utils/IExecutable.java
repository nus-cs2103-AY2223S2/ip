package utils;

import java.util.function.Supplier;

import exceptions.DukeException;


/**
 * An interface for objects that are executables.
 *
 * @param <T> The store to be supplied.
 */
public interface IExecutable<T> {
    void execute(Supplier<? extends T> store) throws DukeException;
}
