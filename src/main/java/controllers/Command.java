package controllers;

import enums.CommandType;
import exceptions.DukeException;
import utils.Executable;

import java.util.regex.Pattern;

abstract public class Command implements Executable {
    private final CommandType commandType;
    private boolean isTerminating = false;
    protected static final Pattern NUMBERS = Pattern.compile("[-+]?\\d+");
    protected static final Pattern DATE_FORMAT =
            Pattern.compile("(?<year>\\d{4})-(?<month>0[0-9]|1[0-2])-(?<day>0[0-9]|1[0-9]|2[0-9]|3[0-1])$");

    public Command(CommandType cmdType) {
        this.commandType = cmdType;
    }
    public Command(CommandType cmdType, boolean isTerminating) {
        this.commandType = cmdType;
        this.isTerminating = isTerminating;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public abstract void execute() throws DukeException;
    public boolean isTerminating() { return isTerminating; }
}
