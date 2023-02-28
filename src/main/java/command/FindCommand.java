package command;

import duke.DukeException;
import duke.MainWindow;
import duke.Ui;
import task.TaskList;

/**
 * This class will carry the implementation of find command.
 * Takes in command line command, parses it as well as finding.
 */
public class FindCommand {

    private String[] inputs;
    private MainWindow mw;
    private static StringBuilder strBuild = new StringBuilder();

    public FindCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This method invokes the find function to search
     * the keyword in tasks.
     */
    public String find() throws DukeException {
        assert inputs[0].equalsIgnoreCase("FIND") : "Contact developer on Find bug";
        String keyword = "";
        if (inputs.length != 2) {
            throw new DukeException(Ui.printEnterKeyword());
        } else {
            keyword = inputs[1];
            UndoCommand.find();
        }
        return TaskList.search(keyword);

    }

}
