package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private Parser parser;

    public Duke(String directory, String path) {
        storage = new Storage(directory, path);
        taskList = new TaskList();
        ui = new UI();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke("data", "../../../data/duke.txt").run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.printWelcomeMessage();

        try {
            storage.loadTasks(taskList);
        } catch (FileNotFoundException e) {
            ui.printMessage("No save data found!");
        } catch (IOException | DukeException e) {
            ui.printMessage("Error loading save data");
        }

        while (true) {
            try {
                String[] command = sc.nextLine().split(" ", 2);
                if (command[0].equals("bye")) {
                    storage.saveTasks(taskList);
                    ui.printMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (command[0].equals("list")) {
                    ui.printMessage(taskList.listTasks());
                } else if (command[0].equals("mark")) {
                    int taskNum = parser.getTaskNum(command);
                    taskList.markTask(parser.getTaskNum(command));
                    ui.printSuccessMessage("Nice! I've marked this task as done:", taskList.getTask(taskNum));
                } else if (command[0].equals("unmark")) {
                    int taskNum = parser.getTaskNum(command);
                    taskList.unmarkTask(taskNum);
                    ui.printSuccessMessage("OK, I've marked this task as not done yet:", taskList.getTask(taskNum));
                } else if (command[0].equals("delete")) {
                    int taskNum = parser.getTaskNum(command);
                    Task removedTask = taskList.deleteTask(taskNum);
                    ui.printTaskMessage("Noted. I've removed this task:", removedTask, taskList.getSize());
                } else {
                    taskList.addTask(parser.getTaskToAdd(command));
                    ui.printTaskMessage("Got it. I've added this task:", taskList.getLatestTask(), taskList.getSize());
                }
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            } catch (NumberFormatException e) {
                ui.printMessage("Valid task number required");
            } catch (IOException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }
}
