package kal.commands;

import java.util.ArrayList;

import kal.TaskList;
import kal.commands.tasks.Task;

/**
 * This class handles searching for tasks using keywords.
 */
public class Find extends Command {
    private static final String RESPONSE_HEADER = "Got it! Showing your search results meow:\n";
    private String keyword;
    private String searchResults;

    /**
     * Constructs a new Find object.
     *
     * @param message The full command message that is represented by the Find object.
     * @param keyword The keyword to search for.
     */
    public Find(String message, String keyword) {
        super(message);
        this.keyword = keyword;
        this.searchResults = RESPONSE_HEADER;
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
        for (Task i : matches) {
            this.searchResults += (i.toString() + '\n');
        }
    }

    @Override
    public String getResponseOutput() {
        return this.searchResults;
    }
}
