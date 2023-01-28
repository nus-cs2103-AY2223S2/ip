package command;

import java.util.ArrayList;

import dukeException.MissingArgumentException;
import storage.TaskList;
import task.Task;

public class FindCommand extends Command {

    private String request;
    public FindCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) {
        String[] inquiry = request.trim().split("find");
        if (inquiry.length == 0) {
            throw new MissingArgumentException("What do you wish to find?");
        }

        ArrayList<Task> result = tasks.find(inquiry[1].strip());
        if (result.size() == 0) {
            return "Sorry, I couldn't find what you want. :(";
        }
        StringBuilder list = new StringBuilder();
        for (Task task : result) {
            list.append(task.toString()).append("\n");
        }
        return list.toString();
    }
}
