package utils;

import exceptions.DukeException;

@FunctionalInterface
public interface Executable {
    void execute() throws DukeException;
}
