package io.gui;

import java.io.IOException;

import io.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import task.TaskList;

/**
 * Graphical User Interface
 */
public class Gui extends Application {
    private static final double MIN_HEIGHT = 300.0;
    private static final double MIN_WIDTH = 300.0;

    private TaskList taskList;
    private Storage<TaskList> storage;
    private FXMLLoader fxmlLoader;

    /**
     * Entry point into app
     */
    public static void launch(String... args) {
        Application.launch(Gui.class, args);
    }

    /**
     * Actions to perform when Application is launched.
     * Loads children, formats scene and sets the MainWindow gui property.
     */
    @Override
    public void start(Stage stage) {
        try {
            fxmlLoader = new FXMLLoader(this.getClass()
                    .getResource("/view/MainWindow.fxml"));
            AnchorPane mainWindow = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController()
                    .setGui(this);
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene);
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setTitle("D");
            stage.getIcons().add(fxmlLoader.<MainWindow>getController().getDImg());
            stage.show();

            loadTasks();
            fxmlLoader.<MainWindow>getController()
                    .showReply(String.format("Current tasks: %s", taskList.toString()));

            // Check if properties were initialized.
            assert (storage != null);
            assert (taskList != null);
            assert (fxmlLoader != null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Attempts to save tasks if window is closed.
     */
    @Override
    public void stop() {
        storage.save(taskList);
    }

    /**
     * Attempts to load previous tasks.
     */
    private void loadTasks() {
        storage = Storage.of(TaskList.class, "taskList.ser");
        taskList = storage.load()
                .match(lst -> lst, error -> {
                    switch (error) {
                    case IO_ERROR:
                    case CAST_ERROR:
                        fxmlLoader.<MainWindow>getController()
                                .showReply("Error loading tasks, tasks have been reset");
                        return new TaskList();
                    default:
                        return new TaskList();
                    }
                });
    }

    /**
     * @return Task list of app
     */
    TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * @return Storage class of app
     */
    Storage<TaskList> getStorage() {
        return this.storage;
    }
}
