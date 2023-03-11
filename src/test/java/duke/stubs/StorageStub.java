package duke.stubs;

import duke.enums.TaskTypes;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

public class StorageStub implements Storage {
    @Override
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        Todo todo = new Todo("test todo");
        todo.markAsDone();
        tasks.add(todo);
        tasks.add(new Deadline("test deadline", LocalDate.of(2023, 6, 20)));
        tasks.add(new Event("test event",
                LocalDate.of(2020, 4, 20),
                LocalDate.of(2022, 12, 17)));
        return tasks;
    }

    @Override
    public void addTask(Task t, TaskTypes type) {

    }

    @Override
    public void deleteTask(int index) {

    }

    @Override
    public void mark(int index) {

    }

    @Override
    public void unmark(int index) {

    }
}
