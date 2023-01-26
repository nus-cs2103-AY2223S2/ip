import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
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
