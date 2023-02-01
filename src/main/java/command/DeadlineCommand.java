package command;

import duke.Ui;

import task.Deadline;
import task.TaskList;

/**
 * This class will carry the implementation of the Deadline Command
 * Takes in the command line command,parses as well as executing it.
 *
 * @author Bryan Ong
 *
 */
public class DeadlineCommand {
    private String[] inputs;
    private static StringBuilder strBuild = new StringBuilder();

    public DeadlineCommand(String[] inputs) {
        this.inputs = inputs;
    }


    /**
     * This method is used to create the Deadline command.
     * Parsing as well as creation of Deadline object is done here.
     */
    public void create() {
        boolean isName = true;
        String name = " ", end = " ";
        for (int i = 1; i < inputs.length; i++) {
            if (isName) {
                if (!inputs[i + 1].equalsIgnoreCase("/by")) {
                    strBuild.append(inputs[i]);
                    strBuild.append(" ");
                } else {
                    strBuild.append(inputs[i]);
                    name = strBuild.toString();
                    strBuild.setLength(0);
                    isName = false;
                    i++;
                }
            } else {
                strBuild.append(inputs[i]);
                if (i + 1 != inputs.length) {
                    strBuild.append(" ");
                }
            }
        }
        end = strBuild.toString();
        strBuild.setLength(0);
        Deadline d = new Deadline(name, end, false);
        TaskList.addToList(d);
        Ui.printDefault(d);
    }
}
