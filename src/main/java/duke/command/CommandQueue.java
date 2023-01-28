package duke.command;

import duke.task.TaskList;

import java.util.ArrayList;

public class CommandQueue {
    private ArrayList<Commands> queue = new ArrayList<>(); // Create an ArrayList object

    public void add(Commands command) {
        queue.add(command);
    }

    public void executeQueue(TaskList list) {
        for (Commands command : queue) {
            command.execute(list);
        }
        queue.clear();
    }
}
