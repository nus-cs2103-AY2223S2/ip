package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> listOfTasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String str;
            for (Task task : listOfTasks) {
                str = task.toString();
                str = str.replace("[ ]", " | 0 |");
                str = str.replace("[X]", " | 1 |");
                str = str.replace("[", "");
                str = str.replace("]", "");
                if (str.startsWith("D")) {
                    str = str.replace("(by:", "|");
                    str = str.replace(")", "");
                } else if (str.startsWith("E")) {
                    str = str.replace("(from:", "|");
                    str = str.replace(" to: ", " - ");
                    str = str.replace(")", "");
                }
                str = str + "\n";
                fw.write(str);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e);
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>(100);
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            Task task;
            String description, str, taskInfo;
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");

            while (s.hasNext()) {
                str = s.nextLine();
                taskInfo = str.substring(8);
                if (str.startsWith("T")) {
                    description = taskInfo;
                    task = new Todo(description);
                } else if (str.startsWith("D")) {
                    int byIdx = taskInfo.indexOf("|");
                    description = taskInfo.substring(0, byIdx - 1);
                    LocalDateTime by = LocalDateTime.parse(taskInfo.substring(byIdx + 2), format);
                    task = new Deadline(description, by);
                } else {
                    int fromIdx = taskInfo.indexOf("|");
                    int toIdx = taskInfo.indexOf("-");
                    description = taskInfo.substring(0, fromIdx - 1);
                    LocalDateTime from = LocalDateTime.parse(taskInfo.substring(fromIdx + 2, toIdx - 1), format);
                    LocalDateTime to = LocalDateTime.parse(taskInfo.substring(toIdx + 2), format);
                    task = new Event(description, from, to);
                }
                if (str.charAt(4) == '1') {
                    task.markAsDone();
                }
                listOfTasks.add(task);
            }
        } catch (FileNotFoundException | DateTimeParseException e) {
            throw new DukeException();
        }
        return listOfTasks;
    }
}















