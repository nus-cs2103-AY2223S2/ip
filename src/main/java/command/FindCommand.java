package command;

import duke.Ui;
import task.TaskList;

/**
 * This class will carry the implementation of find command.
 * Takes in command line command, parses it as well as finding.
 */
public class FindCommand {

    private String[] inputs;

    private static StringBuilder strBuild = new StringBuilder();

    public FindCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This method invokes the find function to search
     * the keyword in tasks.
     */
    public void find() {
        String keyword = "";
        if (inputs.length != 2) {
            Ui.printEnterKeyword();
            keyword = Ui.readCommand();
        } else {
            keyword = inputs[1];
        }
        TaskList.search(keyword);

    }

}
