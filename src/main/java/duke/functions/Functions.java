package duke.functions;

import duke.storage.Storage;
import duke.storage.TaskList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Functions {
    /**
     * Represents the available functions of the Duke program
     */
    TaskList tl;
    Storage st;
    Pane outputLayout;

    /**
     * Constructor for an instance of Function.
     *
     * @param tl TaskList object that stores all defined tasks
     * @param st Storage object that controls writing and loading onto/from file
     */
    public Functions(TaskList tl, Storage st) {
        this.tl = tl;
        this.st = st;
    }

    /**
     * Constructor for an instance of Function.
     *
     * @param tl TaskList object that stores all defined tasks
     * @param st Storage object that controls writing and loading onto/from file
     * @param op Pane to display the output for all functions
     */
    public Functions(TaskList tl, Storage st, Pane op) {
        this.tl = tl;
        this.st = st;
        this.outputLayout = op;
    }

    public TaskList getTl() {
        return this.tl;
    }

    public Storage getSt() {
        return this.st;
    }

    public Pane getOutputLayout() {
        return this.outputLayout;
    }

    public void setOutputLayout(Pane outputLayout) {
        this.outputLayout = outputLayout;
    }

    public Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

}
