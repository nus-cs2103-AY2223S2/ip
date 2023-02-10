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

    @Override
    public void execute(TaskList toDoList) {
        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < toDoList.size(); i++) {
            Task curTask = toDoList.get(i);
            String curDescription = curTask.getDescription();
            for (String word : curDescription.split(" ")) {
                if (word.equals(this.keyword)) {
                    matches.add(curTask);
                }
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
