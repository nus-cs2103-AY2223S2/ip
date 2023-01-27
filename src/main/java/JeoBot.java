package main.java;

import main.java.jeo.parser.Parser;
import main.java.jeo.database.Storage;
import main.java.jeo.database.TaskList;
import main.java.jeo.exception.JeoException;
import main.java.jeo.task.Deadline;
import main.java.jeo.task.Event;
import main.java.jeo.task.Task;
import main.java.jeo.task.ToDo;
import main.java.jeo.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.HashMap;

/**
 * Main Bot class which the user may run the program from
 */
public class JeoBot {
    protected final String DATE_PARSE = "yyyy-MM-dd";
    protected Ui ui;
    protected Storage store;
    protected TaskList taskList;

    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, DUE
    }

    public JeoBot(String path) {
        ui = new Ui();
        store = new Storage(path);
        try {
            taskList = new TaskList(store.load());
        } catch (FileNotFoundException | IllegalStateException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Executes commands which the user inputs accordingly
     */
    public void run() {
        ui.showGreetingMessage();
        boolean hasInput = true;
        Scanner sc = new Scanner(System.in);
        while (hasInput) {
            String s = sc.nextLine();
            try {
                ui.showBodyDivider();
                // Parse
                HashMap<String, String> hm = Parser.parseString(s);
                Command command = Command.valueOf(hm.get("command").toUpperCase());
                switch (command) {
                case BYE:
                    hasInput = false;
                    ui.showExitMessage();
                    break;
                case LIST:
                    ui.showAllTasks(taskList);
                    break;
                case MARK:
                    int index = Integer.parseInt(hm.get("index"));
                    Task task = taskList.getTaskAtIndex(index);
                    taskList.markTask(index);
                    ui.showTaskMarked(task);
                    break;
                case UNMARK:
                    index = Integer.parseInt(hm.get("index"));
                    task = taskList.getTaskAtIndex(index);
                    taskList.unmarkTask(index);
                    ui.showTaskUnmarked(task);
                    break;
                case DELETE:
                    index = Integer.parseInt(hm.get("index"));
                    task = taskList.getTaskAtIndex(index);
                    taskList.deleteTask(index);
                    ui.showTaskDeleted(task, taskList.getNumberOfTasks());
                    break;
                case TODO:
                    String desc = hm.get("description");
                    task = new ToDo(desc);
                    taskList.addTask(task);
                    ui.showTaskAdded(task, taskList.getNumberOfTasks());
                    break;
                case DEADLINE:
                    desc = hm.get("description");
                    String by = hm.get("by");
                    task = new Deadline(desc, by);
                    taskList.addTask(task);
                    ui.showTaskAdded(task, taskList.getNumberOfTasks());
                    break;
                case EVENT:
                    desc = hm.get("description");
                    String from = hm.get("from");
                    String to = hm.get("to");
                    task = new Event(desc, from, to);
                    taskList.addTask(task);
                    ui.showTaskAdded(task, taskList.getNumberOfTasks());
                    break;
                case DUE:
                    by = hm.get("by");
                    DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_PARSE);
                    LocalDate byDate = LocalDate.parse(by, formatterParse);
                    ui.showTasksDue(byDate, taskList);
                    break;
                }
                store.save(taskList.getTaskList());
            } catch (IOException e) {
                ui.showSavingError();
            } catch (IllegalArgumentException e) {
                ui.showInvalidCommand();
            } catch (IndexOutOfBoundsException e) {
                ui.showIndexingError();
            } catch (DateTimeParseException e) {
                ui.showDateTimeParsingError();
            } catch (JeoException e) {
                ui.showJeoErrorMessage(e.getMessage());
            } finally {
                ui.showBodyDivider();
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new JeoBot("./data.txt").run();
    }
}
