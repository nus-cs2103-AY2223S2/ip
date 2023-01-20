package duke;

import java.util.ArrayList;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.Todos;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task mark(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toChange = this.taskList.get(position);
        toChange.mark();
        return toChange;
    }

    public Task unmark(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toChange = this.taskList.get(position);
        toChange.unmark();
        return toChange;
    }

    public Task todo(String input) {
        Task toAdd = new Todos(input);
        this.taskList.add(toAdd);
        return toAdd;
    }

    public Task deadline(String input) {
        String[] split = input.split(" /by ");
        Task toAdd = new Deadlines(split[0], split[1]);
        this.taskList.add(toAdd);
        return toAdd;
    }

    public Task event(String input) {
        String[] split = input.split(" /from | /to ");
        Task toAdd = new Events(split[0], split[1], split[2]);
        this.taskList.add(toAdd);
        return toAdd;
    }

    public Task delete(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toRemove = this.taskList.remove(position);
        return toRemove;
    }

    public void loader(String[] line) {
        try {
            switch (line[0]) {
            case "T": {
                Todos toAdd = new Todos(line[2]);
                if (line[1].equals("1")) {
                    toAdd.mark();
                }
                this.taskList.add(toAdd);
            }
            break;

            case "D": {
                Deadlines toAdd = new Deadlines(line[2], line[3]);
                if (line[1].equals("1")) {
                    toAdd.mark();
                }
                this.taskList.add(toAdd);
            }
            break;

            case "E": {
                Events toAdd = new Events(line[2], line[3], line[4]);
                if (line[1].equals("1")) {
                    toAdd.mark();
                }
                this.taskList.add(toAdd);
            }
            break;

            default:
                throw new DukeException("Invalid data found");

            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
