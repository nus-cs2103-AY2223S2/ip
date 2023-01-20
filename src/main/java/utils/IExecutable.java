package utils;

import exceptions.DukeException;

import java.util.function.Supplier;

public interface IExecutable<T> {
    void execute(Supplier<? extends T> store) throws DukeException;
}
