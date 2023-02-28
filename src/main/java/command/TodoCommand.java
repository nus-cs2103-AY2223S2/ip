package command;

import duke.Duke;
import duke.DukeException;
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
    public String create() throws DukeException {
        assert inputs[0].equalsIgnoreCase("TODO") : "Contact developer on Todo bug";
        if (inputs.length == 1) {
            throw new DukeException(Ui.printMissingName());
        } else {
            for (int i = 1; i < inputs.length; i++) {
                strBuild.append(inputs[i]);
                if (i + 1 != inputs.length) {
                    strBuild.append(" ");
                }
            }
            Todo t = new Todo(strBuild.toString(), false);
            strBuild.setLength(0);
            UndoCommand.todo(t);
            TaskList.addToList(t);
            return Ui.printDefault(t);
        }
    }
}
