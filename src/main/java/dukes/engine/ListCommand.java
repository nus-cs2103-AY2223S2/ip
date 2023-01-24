package dukes.engine;

import dukes.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ListCommand extends Command {
    protected LocalDate date;
    protected int action;

    public ListCommand(boolean isExit, boolean isValid,
                       String header, String body) {
        // here body = index
        super(isExit, isValid, header, body);
        this.date = LocalDate.parse("1970-01-01");
        this.action = 0;
    }

    public ListCommand(boolean isExit, boolean isValid,
                       String header, String body, LocalDate theDate) {
        // here body = index
        super(isExit, isValid, header, body);
        this.date = theDate;
        this.action = 1;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        if (action == 0) { // just list
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                sb.append(i+1).append(". ");
                sb.append(taskList.get(i).toString()).append("\n");
            }
            if (sb.length() != 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            ui.showList(sb.toString(), 0);
        } else { // search
            StringBuilder sb = new StringBuilder();
            int counter = 0;
            for (int i = 0; i < taskList.size(); i++) {
                Task theTask = taskList.get(i);
                if (theTask.getTag().equals("D") &&
                        theTask.getDeadLine().equals(date)) {
                    counter += 1;
                    sb.append(counter).append(". ");
                    sb.append(theTask.toString()).append("\n");
                } else if (theTask.getTag().equals("E") &&
                        theTask.getStart().isBefore(date) &&
                        theTask.getEnd().isAfter(date)) {
                    // begin < theDate, end > theDate
                    counter += 1;
                    sb.append(counter).append(". ");
                    sb.append(theTask.toString()).append("\n");
                }
            }
            if (sb.length() != 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            ui.showList(sb.toString(), 1);
        }
    }
}
