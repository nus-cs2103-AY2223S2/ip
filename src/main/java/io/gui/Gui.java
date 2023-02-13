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
    TaskList taskList;
    Storage<TaskList> storage;
    private FXMLLoader fxmlLoader;
    private AnchorPane mainWindow;

    @Override
    public void start(Stage stage) {
        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            mainWindow = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().gui = this;
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene);
            stage.setMinWidth(300);
            stage.setTitle("D");
            stage.show();

            loadTasks();
            fxmlLoader.<MainWindow>getController().showReply(String.format("Current tasks: %s", taskList.toString()));

            // Check if properties were initialized.
            assert (storage != null);
            assert (taskList != null);
            assert (fxmlLoader != null);
            assert (mainWindow != null);

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
     * Entry point into app
     */
    public static void launch(String... args) {
        Application.launch(Gui.class, args);
    }

    /**
     * Attempts to load previous tasks.
     */
    private void loadTasks() {
        storage = Storage.of(TaskList.class, "taskList.ser");
        taskList = storage.load().match(
                lst -> lst,
                error -> {
                    switch (error) {
                        case FILE_NOT_FOUND:
                            return new TaskList();
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

}
