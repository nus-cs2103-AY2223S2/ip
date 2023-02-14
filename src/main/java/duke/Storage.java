package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.enums.Commands;
import duke.enums.Views;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class to manage storage of persistent files
 */
public class Storage {
    /**
     * Given a list of tasks, it will save it into a txt file
     *
     * @param tasks TaskList object to get and set the list
     * @throws DukeException
     */
    public void save(TaskList tasks) throws DukeException {
        File dukeData = new File("duke_data.txt"); // Magic Strings
        try (PrintWriter writer = new PrintWriter(new FileWriter(dukeData));) {
            for (Task item : tasks.getList()) {
                writer.println(item.toExport()
                        .replace("[T]", "todo")
                        .replace("[D]", "deadline")
                        .replace("(by:", "/by")
                        .replace("[E]", "event")
                        .replace("(from:", "/from")
                        .replace("to:", "/to")
                        .replace(")", ""));
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Given a list of tasks, it will save it into a md file
     *
     * @param tasks TaskList object to get and set the list
     * @throws DukeException
     */
    public void saveMarkdown(TaskList tasks) throws DukeException {
        File dukeData = new File("todo.md"); // Magic Strings
        try (PrintWriter writer = new PrintWriter(new FileWriter(dukeData));) {
            writer.println(Views.MD_HEADER_STRING.str());
            for (Task item : tasks.getList()) {
                writer.println(item.toMarkdownString()
                        .replace("[T]", "")
                        .replace("[D]", " Deadline: ")
                        .replace("[E]", " Event: "));
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Loads the items from storage
     *
     * @return Array of tasks
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasksList = new ArrayList<Task>(100);
        try {
            Scanner scanner = new Scanner(new File("duke_data.txt"));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                boolean isDone = line.indexOf("[X]") == -1 ? false : true;
                line = line.replace("[ ]", "");
                line = line.replace("[X]", "");
                String[] parsed = Parser.handleTaskInput(line);
                String title = parsed[0];
                if (line.startsWith(Commands.TODO.cmd())) {
                    tasksList.add(new Todo(title, isDone));
                } else if (line.startsWith(Commands.DEADLINE.cmd())) {
                    String deadline = parsed[1];
                    tasksList.add(new Deadline(title, deadline, isDone));
                } else if (line.startsWith(Commands.EVENT.cmd())) {
                    String from = parsed[1];
                    String to = parsed[2];
                    tasksList.add(new Event(title, from, to, isDone));
                } else {
                    throw new DukeException(Views.LOAD_EXTRA_ERR_STRING);
                }
            }
            scanner.close();
        } catch (Exception e) {
            if (e instanceof DukeException) {
                throw new DukeException(e.getMessage());
            }
            // Silent fail if cannot load previous file
        }
        return tasksList;
    }

}
