package duke.gui;

import duke.Duke;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Window {

    static public Pane makeWindow(Duke duke) {
        //pane to gather user input
        Pane inputLayout = new Pane();

        //pane to hold list of tasks
        VBox outputLayout = new VBox();
        duke.getFn().setOutputLayout(outputLayout);

        //pane to display all task
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(outputLayout);
        scrollPane.setPrefSize(385, 200);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        outputLayout.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //pane to hold 3 buttons for each task
        UI ui = new UI(duke.getFn());
        HBox buttonLayout = ui.createButtons(inputLayout, outputLayout);

        //main layout
        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(buttonLayout, inputLayout, scrollPane);
        mainLayout.setPrefSize(400.0, 600.0);
        return mainLayout;
    }

    static public void setStage(Stage stage,Scene scene) {
        //set stage
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }


}
