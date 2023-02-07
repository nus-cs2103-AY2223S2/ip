package Nerd.Commands;

import Nerd.entities.Task;
import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

public class FindCommand extends Command{
    private final String description;

    /**
     * Constructor of Find commands
     *
     * @param description The description of the task.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for an FindCommand.
     *
     * @param list Tasklist of the Chat bot.
     * @param ui User interface of the Chat bot.
     * @return The tasks searched by the command.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        String output = "Here are tasks that associate with " + description + ":\n";
        for (int i = 0; i < list.getSize(); i++) {
            boolean toPrint = false;
            Task currentTask = list.getTask(i);
            String[] split = currentTask.getDescription().split(" ");
            for (int j = 0; j < split.length; j++) {
                if(split[j].equals(description)) {
                    toPrint = true;
                }
            }
            if(toPrint) {
                output += String.format("%s\n",currentTask.toString());
            }
        }
        return output;
    }
}