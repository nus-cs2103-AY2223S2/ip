package command;

import duke.Ui;
import task.Deadline;
import task.TaskList;

public class DeadlineCommand {
    private String[] inputs;
    private static StringBuilder strBuild = new StringBuilder();

    public DeadlineCommand(String[] inputs) {
        this.inputs = inputs;
    }

    public void create() {
        boolean isName = true;
        String n = " ", e = " ";
        for (int i = 1; i < inputs.length; i++) {
            if (isName) {
                if (!inputs[i + 1].equalsIgnoreCase("/by")) {
                    strBuild.append(inputs[i]);
                    strBuild.append(" ");
                } else {
                    strBuild.append(inputs[i]);
                    n = strBuild.toString();
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
        e = strBuild.toString();
        strBuild.setLength(0);
        Deadline d = new Deadline(n, e, false);
        TaskList.addToList(d);
        Ui.printDefault(d);
    }
}
