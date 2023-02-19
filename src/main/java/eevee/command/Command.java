package eevee.command;

import eevee.Storage;
import eevee.TaskList;
import eevee.Ui;
import eevee.exception.NoTaskToDeleteException;
import eevee.exception.TaskNoContentException;
import eevee.exception.TaskNoNameException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public abstract class Command {

    protected String command;
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException,
            IndexOutOfBoundsException, DateTimeParseException, TaskNoContentException,
            NoTaskToDeleteException, TaskNoNameException;

    public abstract boolean isExit();
}
