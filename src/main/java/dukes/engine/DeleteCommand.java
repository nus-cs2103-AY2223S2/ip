package dukes.engine;

import dukes.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeleteCommand extends Command {
    public DeleteCommand(boolean isExit, boolean isValid,
                       String header, String body) {
        // here body = index
        super(isExit, isValid, header, body);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        int index = Integer.parseInt(body);
        try {
            Task theTask = taskList.get(index - 1);
            taskList.remove(theTask);
            ui.showDelete(theTask, tasks);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("You have entered an invalid index.");
        }
    }
}
