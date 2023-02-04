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

    /**
     * Method to create buttons and their corresponding actions.
     *
     * @param inputLayout  Pane that will hold subsequent Panes to gather user input
     * @param outputLayout Pane that will hold the output of each button function
     * @return HBox that holds all the buttons
     */
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

        assert buttonList.size() > 0 : "No buttons are added";

        for (DukeButton b : buttonList) {
            buttonLayout.getChildren().add(b.getButton());
        }

        return buttonLayout;
    }
}
