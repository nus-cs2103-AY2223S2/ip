package duke.storage;

import duke.exception.InvalidInputException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.TodoTask;

import java.time.LocalDate;

public class Decoder {
    public static void todoDecoder(TaskList list, String description, boolean isDone) {
        TodoTask todo = new TodoTask(description);
        if (isDone) {
            todo.markAsDone();
        }
        list.addTask(todo);
    }

    public static void deadlineDecoder(TaskList list, String description, boolean isDone, String date) {
        DeadlineTask deadline = new DeadlineTask(description, LocalDate.parse(date));
        if (isDone) {
            deadline.markAsDone();
        }
        list.addTask(deadline);
    }

    public static void eventDecoder(TaskList list, String description, boolean isDone, String from, String to)
            throws InvalidInputException {
        EventTask event = new EventTask(description, LocalDate.parse(from), LocalDate.parse(to));
        if (isDone) {
            event.markAsDone();
        }
        list.addTask(event);
    }
}
