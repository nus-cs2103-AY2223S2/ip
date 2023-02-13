package duke.gui;

import duke.Duke;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Window {

    /**
     * Method to make the Pane that will house all other Panes
     *
     * @param duke Duke program that requires GUI
     * @return Pane that will represent the overall layout
     */
    static public Pane makeWindow(Duke duke) {
        //pane to gather user input
        Pane inputLayout = new Pane();
        inputLayout.setPrefSize(385, 180);

        //pane to hold list of tasks
        VBox outputLayout = new VBox();
        duke.getFn().setOutputLayout(outputLayout);

        //pane to display all task
        ScrollPane scrollPane = makeScrollPane(outputLayout);
        outputLayout.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        if (duke.hasError()) {
            Label textToAdd = new Label(duke.error);
            textToAdd.setWrapText(true);
            outputLayout.getChildren().add(textToAdd);
        }

        //pane to hold all buttons
        UI ui = new UI(duke.getFn());
        HBox buttonLayout = ui.createButtons(inputLayout, outputLayout);

        //main layout
        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(buttonLayout, inputLayout, scrollPane);

        assert mainLayout.getChildren().isEmpty() == false : "Main layout has not been filled with anything";
        return mainLayout;
    }

    static private ScrollPane makeScrollPane(Pane outputLayout) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(outputLayout);
        scrollPane.setPrefSize(385, 200);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        return scrollPane;
    }

    static public void setStage(Stage stage, Scene scene) {
        //set stage
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
    }


}
