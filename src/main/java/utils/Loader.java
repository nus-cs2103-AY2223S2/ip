package utils;

import exceptions.DukeException;

/**
 * An interface for performing the loading action.
 * @param <T> The store to load to.
 */
@FunctionalInterface
public interface Loader<T> {
    Boolean load(T store) throws DukeException;
}
