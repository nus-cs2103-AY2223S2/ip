package parser;

import java.time.LocalDate;

import command.Command;
import command.ReminderCommand;

/**
 * Parser that handles command that starts with reminder keyword.
 */
public class ReminderParser implements Parser {
    @Override
    public Command parse(String requestContent) {
        return new ReminderCommand(LocalDate.now());
    }
}
