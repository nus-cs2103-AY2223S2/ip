package dukes.command;

import dukes.util.*;
import dukes.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ExitCommand extends Command {

    public ExitCommand(boolean isExit, boolean isValid,
                       String header, String body) {
        super(isExit, isValid, header, body);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        // do nothing
    }

}
