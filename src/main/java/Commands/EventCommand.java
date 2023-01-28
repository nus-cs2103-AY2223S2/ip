package Commands;

import Storage.Storage;
import Storage.TaskList;
import Tasks.Event;
import Ui.Ui;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;
    
    public EventCommand(String userInput) {
        this.description = getDescription(userInput);
        this.from = getFrom(userInput);
        this.to = getTo(userInput);
    }

    public String getDescription(String userInput) {
        return userInput.substring(6).split(" /from ")[0]; //substring(6) is used because "event " has 6 characters
    }

    public String getFrom(String userInput) {
        return userInput.substring(6).split(" /from ")[1].split(" /to ")[0]; //substring(6) is used because "event " has 6 characters
    }

    public String getTo(String userInput) {
        return userInput.substring(6).split(" /from ")[1].split(" /to ")[1]; //substring(6) is used because "event " has 6 characters
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(this.description, this.from, this.to);
        tasks.addTask(event);
        ui.showAddTask(event, tasks.getSize());
    }

    @Override
    public boolean isContinueConvo() {
        return true;
    }
}
