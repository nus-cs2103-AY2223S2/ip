package yj;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class YJ {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public YJ() {
        ui = new Ui();
        storage = new Storage("tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        ui.clear();
        Command command = Parser.parseCommand(input);
        switch (command) {
            case LIST:
                tasks.forEachTask((task, i) -> ui.print((i + 1) + "." + task.toString()));
                break;
            case DELETE:
                try {
                    Integer taskNumber = Parser.parseDeleteCommand(input);
                    Task task = tasks.deleteTask(taskNumber);
                    ui.print("I've removed this task as u lazily requested:");
                    ui.print(task.toString());
                    ui.print("Now you have like this many tasks left: " + tasks.getNumberofTasks());
                } catch (IndexOutOfBoundsException e) {
                    ui.print("Crapadoodle! You need to specify a task number or a valid task to delete.");
                }
                break;
            case MARK: {
                Integer taskNumber = Parser.parseMarkCommand(input);
                if (taskNumber != null && tasks.getTask(taskNumber - 1) != null) {
                    Task task = tasks.getTask(taskNumber - 1);
                    task.markAsDone();
                    ui.print("Niceoooo you're done wit this: ");
                    ui.print(task.toString());
                }
                break;
            }
            case UNMARK: {
                Integer taskNumber = Parser.parseUnMarkCommand(input);
                if (taskNumber != null && tasks.getTask(taskNumber - 1) != null) {
                    Task task = tasks.getTask(taskNumber - 1);
                    task.markAsNotDone();
                    ui.print("Ok lah you haven't finish this yet");
                    ui.print(task.toString());
                }
                break;
            }
            case TODO:
                try {
                    String description = Parser.parseToDoCommand(input);
                    ToDo newToDo = new ToDo(description);
                    tasks.addTask(newToDo);
                    ui.print("Ok! I've added this todo! " + newToDo.toString());
                    ui.print("You now have this many tasks: " + tasks.getNumberofTasks());
                } catch (IllegalArgumentException e) {
                    ui.print(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    Map<String, String> result = Parser.parseDeadlineCommand(input);
                    Deadline newDeadline = new Deadline(result.get("description"), result.get("by"));
                    tasks.addTask(newDeadline);
                    ui.print("Ok! I've added this deadline!" + newDeadline.toString());
                    ui.print("You now have this many tasks: " + tasks.getNumberofTasks());
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.print("Crapadoodle! You need to specify a deadline in the correct format!");
                }
                break;
            case EVENT:
                try {
                    Map<String, String> result = Parser.parseEventCommand(input);
                    Event newEvent = new Event(result.get("description"), result.get("from"), result.get("to"));
                    tasks.addTask(newEvent);
                    ui.print("Ok! I've added this event!" + newEvent);
                    ui.print("You now have this many tasks: " + tasks.getNumberofTasks());
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.print("Crapadoodle! You need to specify an event in the correct format!");
                }
                break;
            case BYE:
                // Write tasks to file
                storage.save(tasks.getTasks());
                ui.print("Byebye, YJ will miss you :(");
                System.exit(0);
                break;
            default:
                ui.print("Crapdoodledy, I don't know what that means man");
        }
        System.out.println("THe message: " + ui.getMessage());
        return ui.getMessage();
    }
}

