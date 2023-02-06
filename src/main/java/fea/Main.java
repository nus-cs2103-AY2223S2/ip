package fea;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import org.controlsfx.control.Notifications;

import fea.task.Task;
import fea.tasklist.TaskList;
import fea.ui.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * A GUI for FEA using FXML.
 */
public class Main extends Application {
    private static final String APP_NAME = "Fate Eggplant Assistant";
    private static final String MAIN_WINDOW_PATH = "/view/MainWindow.fxml";
    private static final String LOGO_PATH = "/images/logo.png";
    private String filePath = String.format("data%sfea.txt", File.separator);
    private Fea fea = new Fea(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle(APP_NAME);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream(LOGO_PATH)));
            stage.setScene(scene);
            stage.sizeToScene();
            fxmlLoader.<MainWindow>getController().setFea(fea);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());

            TaskList tasks = fea.getTaskList();
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.getTask(i).getReminder() != null) {
                    scheduleReminder(tasks.getTask(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Schedules a notification to be displayed during the set reminder time for a
     * task.
     *
     * @param task The task with a reminder.
     */
    public static void scheduleReminder(Task task) {
        LocalDateTime reminder = task.getReminder();
        if (reminder != null) {
            Duration duration = Duration.between(LocalDateTime.now(), reminder);
            if (duration.isNegative()) {
                return;
            }
            long delay = duration.toMillis();
            String reminderMessage = String.format("Reminder for this task: %s", task.toString());
            Timer timer = new Timer();

            Text text = new Text(reminderMessage);
            text.setWrappingWidth(300);
            text.setTextAlignment(TextAlignment.JUSTIFY);
            text.setFill(Color.WHITE);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setPrefWidth(300);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        Notifications.create()
                                .title(APP_NAME)
                                .text(" ")
                                .hideAfter(new javafx.util.Duration(60000))
                                .graphic(textFlow)
                                .darkStyle()
                                .show();
                        String soundFile = "/sounds/reminder.mp3";
                        Media sound = new Media(Main.class.getResource(soundFile).toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(sound);
                        mediaPlayer.play();
                    });
                }
            }, delay);
        }
    }
}
