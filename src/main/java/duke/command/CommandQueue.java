package duke.command;

import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Implements the command queue.
 */
public class CommandQueue {
    private ArrayList<Commands> queue = new ArrayList<>(); // Create an ArrayList object

    public void add(Commands command) {
        queue.add(command);
    }

    /**
    Executes the tasks in the queue, and then clear the queue.
     */
    public void executeQueue(TaskList list) {
        for (Commands command : queue) {
            command.execute(list);
        }
        //delete.execute();
        //list.execute(delete);

        //list.remove(index);
        queue.clear();
    }
}
