package Exceptions;

import Exceptions.MunchException;

public class InvalidInputException extends MunchException {

    public InvalidInputException() {
        super("Bro, I'm sorry but I do not understand the command. ");
    }
}
