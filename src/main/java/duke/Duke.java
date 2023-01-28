package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * <h1>Duke task checklist</h1>
 * The Duke program helps keep track of your ongoin task.
 * <p>
 *
 * @author Chin Jun An
 * @version 1.0
 * @since 2023
 */
public class Duke extends Application {
    /**
     * Represents a Duke program.
     */
    private Functions fn;
    private Scene scene;

    public Duke() {

    }

    /**
     * Constructor for a Duke instance. Load tasks previously saved.
     *
     * @param fp Indicate the file path to save task scheduled
     */
    public Duke(String fp) {
        try {
            Storage st = new Storage(fp);
            TaskList tl = st.load();
            this.fn = new Functions(tl, st);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Functions getFn() {
        return fn;
    }

    @Override
    public void start(Stage stage) {
        Duke duke = new Duke("tasks.txt");

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
        scene = new Scene(mainLayout);

        //set stage
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }
}
