package Command;

import DukeException.MissingArgumentException;
import Storage.TaskList;
import Task.Event;

public class AddEventCommand extends Command {

    private String request;

    /**
     * Constructor to add event according to user's request
     * @param request user's request to be processed into event
     */
    public AddEventCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException {
        String[] req = this.request.split(" ");
        req = request.split("event")[1].split("/from");
        String task = req[0];
        String duration[] = req[1].split(" /to ");
        String from = duration[0];
        String to = duration[1];
        Event newEvent = tasks.addEvent(task, from, to);
        return "Great! I've added this task for you \n" + newEvent +
                "\nYou have " + tasks.numOfTask() + " tasks in the list";
    }
}
