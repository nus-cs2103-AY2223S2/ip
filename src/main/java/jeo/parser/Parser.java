package jeo.parser;

import jeo.command.Command;
import jeo.exception.JeoException;

public interface Parser {
    Command parse(String[] splitInput) throws JeoException;
}
