package brotherbot.commands;

import brotherbot.storage.TaskList;
import brotherbot.storage.Task;
import brotherbot.ui.Ui;


public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    public void execute(TaskList storage, Ui ui) {
        int i = Integer.parseInt(input.substring(7)) - 1;
        Task removed = storage.get(i);
        storage.remove(i);
        ui.toUser("Deleted this task for you my brother:\n" + removed.toString());
        ui.toUser("Now you have " + storage.size() + " tasks left");
    }
}
