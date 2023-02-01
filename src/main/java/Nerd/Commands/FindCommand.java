package Nerd.Commands;

import Nerd.Ui.Ui;
import Nerd.entities.Task;
import Nerd.entities.TaskList;

public class FindCommand extends Command{

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for an FindCommand.
     *
     * @param list Tasklist
     * @param ui User interface of the Chat bot.
     */
    public void processCommand(TaskList list, String item, Ui ui) {
        ui.print("Here are tasks that associate with " + item + ":");

        for (int i = 0; i < list.getSize(); i++) {
            boolean toPrint = false;
            Task currentTask = list.getTask(i);
            String[] split = currentTask.getDescription().split(" ");
            for (int j = 0; j < split.length; j++) {
                if(split[j].equals(item)) {
                    toPrint = true;
                }
            }
            if(toPrint) {
                ui.print(currentTask.toString());
            }
        }
        ui.printDivider();
    }
}