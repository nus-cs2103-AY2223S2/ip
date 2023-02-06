package command;

import duke.MainWindow;
import duke.Ui;

import task.TaskList;
import task.Todo;

/**
 * This class will carry the implementation of the Todo Command
 * Takes in the command line command,parses as well as executing it.
 *
 * @author Bryan Ong
 *
 */
public class TodoCommand {

    private String[] inputs;
    private MainWindow mw = new MainWindow();
    private static StringBuilder strBuild = new StringBuilder();

    public TodoCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This method is used to create the Todo command.
     * Parsing as well as creation of Todo object is done here.
     *
     */
    public String create() {
        if (inputs.length == 1) {
            return Ui.printMissingName();
        } else {
            for (int i = 1; i < inputs.length; i++) {
                strBuild.append(inputs[i]);
                if (i + 1 != inputs.length) {
                    strBuild.append(" ");
                }
            }
            Todo t = new Todo(strBuild.toString(), false);
            strBuild.setLength(0);
            TaskList.addToList(t);
            return Ui.printDefault(t);
        }
    }
}
