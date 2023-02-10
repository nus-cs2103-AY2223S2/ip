package duke.commands;

import duke.TaskList;

/**
 * This class represents the command to list all current tasks.
 */
public class ListTasks extends Command {
    private static final String RESPONSE_HEADER = "Printing tasks:\n";
    private String response;

    public ListTasks(String message) {
        super(message);
        this.response = RESPONSE_HEADER;
    }

    @Override
    public void execute(TaskList toDoList) {
        String res = "";
        for (int i = 0; i < toDoList.size(); i++) {
            res += String.format("%d.%s\n", i + 1, toDoList.get(i));
        }
        this.response += res;
    }

    @Override
    public String getResponseOutput() {
        return this.response;
    }
}
