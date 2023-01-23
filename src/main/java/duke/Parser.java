package duke;

import exception.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.IOException;

public class Parser {
    enum Actions {LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE}

    private TaskList tasks;
    private Storage storage;

    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public void parse(String command) {
        String[] s;
        Actions selection;
        try {
            s = command.split(" ");
            selection = Actions.valueOf(s[0].toUpperCase());
            switch (selection) {
            case LIST:
                tasks.printList();
                break;
            case MARK:
                Task t1 = tasks.getTask(Integer.parseInt(s[1]) - 1);
                t1.markDone();
                break;
            case UNMARK:
                Task t2 = tasks.getTask(Integer.parseInt(s[1]) - 1);
                t2.markNotDone();
                break;
            case TODO:
                if (s.length < 2)
                    throw new DukeException("The description of a todo cannot be empty.");
                Todo todo = new Todo(command.substring(5));
                tasks.addTask(todo, false);
                break;
            case DEADLINE:
                if (s.length < 2)
                    throw new DukeException("The description of a deadline cannot be empty.");
                String[] deadlineInfo = command.substring(9).split(" /by ");
                if (deadlineInfo.length < 2)
                    throw new DukeException("tasks.Deadline cannot be empty.");
                Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                tasks.addTask(deadline, false);
                break;
            case EVENT:
                if (s.length < 2)
                    throw new DukeException("The description of a event cannot be empty.");
                String[] eventInfo = command.substring(6).split(" /from ");
                if (eventInfo.length < 2)
                    throw new DukeException("tasks.Event start time and end time are required.");
                String[] eventTime = eventInfo[1].split(" /to ");
                if (eventTime.length < 2)
                    throw new DukeException("tasks.Event start time and end time are required.");
                Event event = new Event(eventInfo[0], eventTime[0], eventTime[1]);
                tasks.addTask(event, false);
                break;
            case DELETE:
                if (s.length < 2)
                    throw new DukeException("You must choose a task to delete");
                int taskNumber = Integer.parseInt(s[1]);
                if (taskNumber > tasks.getSize() || taskNumber <= 0)
                    throw new DukeException("No such task found");
                tasks.deleteTask(taskNumber - 1);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            storage.saveTasks();
        } catch (DukeException | IOException e) {
            Ui.print(String.valueOf(e));
        } catch (IllegalArgumentException e) {
            Ui.print("Please enter a valid action!");
        }
    }
}
