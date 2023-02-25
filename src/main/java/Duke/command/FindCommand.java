package Duke.command;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Storage.Storage;
import Duke.TaskList;
import Duke.Ui;


public class FindCommand extends Command {
    private final String filteredList;

    public FindCommand(String filteredList) {
        this.filteredList = filteredList;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeMainExceptions {
        String output = "";
        String[] list = filteredList.split(",");
        for (int i = 0; i < list.length; i++) {
            String str = list[i];
            String curr = String.format("%d. " + str + "\n", i + 1);
            output += (curr);
        }
        return output;

    }


}
