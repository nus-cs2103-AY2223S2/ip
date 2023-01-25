package dukes.command;

import dukes.util.*;
import dukes.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FindCommand extends Command {

    public FindCommand(boolean isExit, boolean isValid,
                      String header, String body) {
        super(isExit, isValid, header, body);
        // here body = search pattern
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        List<Task> findList = tasks.searchTaskList(this.body);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < findList.size(); i++) {
            sb.append(i+1).append(". ");
            sb.append(findList.get(i).toString()).append("\n");
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        ui.showFind(sb.toString());
    }
}
