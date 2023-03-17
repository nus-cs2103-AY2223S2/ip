package duke.utils;

import java.util.function.Supplier;

import duke.exceptions.DukeException;


/**
 * An interface for objects that are executables.
 *
 * @param <T> The store to be supplied.
 */
public interface IExecutable<T> {
    String execute(Supplier<? extends T> store) throws DukeException;
}
