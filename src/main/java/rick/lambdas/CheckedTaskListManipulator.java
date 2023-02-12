package rick.lambdas;

import rick.TaskList;
import rick.exceptions.RickException;

/**
 * Represents a Function that, when given a parameter of type T, attempts an
 * operation on a TaskList instance and returns a value of type R.
 *
 * @author SeeuSim
 *         AY22/23-S2 CS2103T
 * @param <T> The type of the input parameter.
 * @param <R> The type of the return value.
 */
@FunctionalInterface
public interface CheckedTaskListManipulator<T, R> {
    R apply(TaskList ts, T t) throws RickException;
}
