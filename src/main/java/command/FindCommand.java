package command;

import duke.MainWindow;
import duke.Ui;
import task.TaskList;

/**
 * This class will carry the implementation of find command.
 * Takes in command line command, parses it as well as finding.
 */
public class FindCommand {

    private String[] inputs;
    private MainWindow mw = new MainWindow();
    private static StringBuilder strBuild = new StringBuilder();

    public FindCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This method invokes the find function to search
     * the keyword in tasks.
     */
    public String find() {
        assert inputs[0].equalsIgnoreCase("FIND") : "Contact developer on Find bug";
        String keyword = "";
        if (inputs.length != 2) {
            mw.printDuke(Ui.printEnterKeyword());
            keyword = Ui.readCommand();
            mw.printUser(keyword);
        } else {
            keyword = inputs[1];
        }
        return TaskList.search(keyword);

    }

}
