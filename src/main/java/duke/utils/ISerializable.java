package duke.utils;

import duke.exceptions.DukeException;

/**
 * An interface for performing the serializing action.
 *
 * @param <T> The type to serialize to.
 */
public interface ISerializable<T, U> {
    T marshal();
    U unmarshal() throws DukeException;
}
