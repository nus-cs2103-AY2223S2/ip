package duke.gui;

import duke.buttons.*;
import duke.functions.Functions;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class UI {
    /**
     * Represents the actions that the user can input
     */
    String inp;
    String[] inpLine;
    Functions fn;

    /**
     * Constructor for an instance of a UI object
     *
     * @param fn Functions object
     */
    public UI(Functions fn) {
        this.fn = fn;
    }

    public void setFn(Functions fn) {
        this.fn = fn;
    }

    public HBox createButtons(Pane inputLayout, Pane outputLayout) {
        HBox buttonLayout = new HBox();
        List<DukeButton> buttonList = new ArrayList<>();
        buttonList.add(new TodoButton("To do", inputLayout, outputLayout, fn));
        buttonList.add(new DeadlineButton("Deadline", inputLayout, outputLayout, fn));
        buttonList.add(new EventButton("Event", inputLayout, outputLayout, fn));
        buttonList.add(new ListButton("List", inputLayout, outputLayout, fn));
        buttonList.add(new MarkButton("Mark", inputLayout, outputLayout, fn));
        buttonList.add(new UnmarkButton("UnMark", inputLayout, outputLayout, fn));
        buttonList.add(new DeleteButton("Delete", inputLayout, outputLayout, fn));
        buttonList.add(new FindButton("Find", inputLayout, outputLayout, fn));

        for (DukeButton b : buttonList) {
            buttonLayout.getChildren().add(b.getButton());
        }

        return buttonLayout;
    }
}
