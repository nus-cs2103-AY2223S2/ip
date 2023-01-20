package utils;

import exceptions.DukeException;

@FunctionalInterface
public interface Loader<T> {
    Boolean load(T store) throws DukeException;
}
