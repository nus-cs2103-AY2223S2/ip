package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the command input by the user and executes it.
     *
     * @param command Command input by the user.
     * @param ui User interface to interact with the user.
     * @param tasks Task operations.
     * @param storage Storage to save tasks.
     * @throws DukeException  If string index is out of range or incorrect date time format.
     */
    public static void parse(String command, Ui ui, TaskList tasks, Storage storage) throws DukeException {
        String firstWord = command.split(" ")[0].toLowerCase();
        String description;
        int index;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        switch (firstWord) {
        case "bye":
            ui.exit();
            break;
        case "list":
            ui.listTasks(tasks.getListOfTasks());
            break;
        case "mark":
            index = Integer.parseInt(command.split(" ")[1]);
            tasks.markTask(index);
            storage.save(tasks.getListOfTasks());
            break;
        case "unmark":
            index = Integer.parseInt(command.split(" ")[1]);
            tasks.unmarkTask(index);
            storage.save(tasks.getListOfTasks());
            break;
        case "delete":
            index = Integer.parseInt(command.split(" ")[1]);
            tasks.deleteTask(index);
            storage.save(tasks.getListOfTasks());
            break;
        case "todo":
            try {
                description = command.substring(5);
                tasks.saveTask(description);
                storage.save(tasks.getListOfTasks());
                break;
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException(firstWord);
            }
        case "deadline":
            try {
                int byIdx = command.indexOf("/by");
                description = command.substring(9, byIdx - 1);
                LocalDateTime by = LocalDateTime.parse(command.substring(byIdx + 4), format);
                tasks.saveTask(description, by);
                storage.save(tasks.getListOfTasks());
                break;
            } catch (DateTimeParseException e) {
                throw new DukeException(e);
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException(firstWord);
            }
        case "event":
            try {
                int fromIdx = command.indexOf("/from");
                int toIdx = command.indexOf("/to");
                description = command.substring(6, fromIdx - 1);
                LocalDateTime from = LocalDateTime.parse(command.substring(fromIdx + 6, toIdx - 1), format);
                LocalDateTime to = LocalDateTime.parse(command.substring(toIdx + 4), format);
                tasks.saveTask(description, from, to);
                storage.save(tasks.getListOfTasks());
                break;
            } catch (DateTimeParseException e) {
                throw new DukeException(e);
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException(firstWord);
            }
        default:
            throw new DukeException();
        }
    }
}
