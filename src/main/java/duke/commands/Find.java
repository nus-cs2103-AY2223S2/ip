package duke.commands;

import java.util.ArrayList;

import duke.TaskList;
import duke.commands.tasks.Task;

/**
 * This class handles searching for tasks using keywords
 */
public class Find extends Command {
    private String keyword;

    public Find(String message, String keyword) {
        super(message);
        this.keyword = keyword;
    }

    private boolean hasMatch(String taskDescription) {
        for (String word : taskDescription.split(" ")) {
            if (word.equals(this.keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute(TaskList toDoList) {
        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < toDoList.size(); i++) {
            Task currTask = toDoList.get(i);
            String currDescription = currTask.getDescription();
            if (hasMatch(currDescription)) {
                matches.add(currTask);
            }
        }

        System.out.println("Got it! Here is your search result:");
        for (Task i : matches) {
            System.out.println(i);
        }
    }

    @Override
    public String toString() {
        return String.format("");
    }
}
