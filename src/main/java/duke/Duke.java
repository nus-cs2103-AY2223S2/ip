package duke;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Duke(String dataFilePath) {
        ui = new Ui();
        storage = new Storage(dataFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        ui.showGreeting();

        String[] parsedCommand;
        String command;
        Task task;

        label:
        while (true) {
            try {
                parsedCommand = Parser.parseCommand(ui.readCommand());
                command = parsedCommand[0];
                switch (command) {
                case "bye":
                    ui.showGoodbye();
                    break label;
                case "list":
                    ui.showTextWithLines(tasks.toString());
                    break;
                case "mark": {
                    int taskNumber = Integer.parseInt(parsedCommand[1]);
                    tasks.setDone(taskNumber, true);
                    task = tasks.getTask(taskNumber);
                    ui.showMarkTaskMessage(task);
                    break;
                }
                case "unmark": {
                    int taskNumber = Integer.parseInt(parsedCommand[1]);
                    tasks.setDone(taskNumber, false);
                    task = tasks.getTask(taskNumber);
                    ui.showUnmarkTaskMessage(task);
                    break;
                }
                case "delete": {
                    int taskNumber = Integer.parseInt(parsedCommand[1]);
                    task = tasks.getTask(taskNumber);
                    tasks.deleteTask(taskNumber);
                    ui.showDeleteTaskMessage(task, tasks);
                    break;
                }
                case "find":
                    ui.showFindTaskMessage(tasks.findTasks(parsedCommand[1]));
                    break;
                case "todo":
                    task = new ToDo(parsedCommand[1]);
                    tasks.addTask(task);
                    ui.showAddTaskMessage(task, tasks);
                    break;
                case "deadline":
                    task = new Deadline(parsedCommand[1], parsedCommand[2], false);
                    tasks.addTask(task);
                    ui.showAddTaskMessage(task, tasks);
                    break;
                case "event":
                    task = new Event(parsedCommand[1], parsedCommand[2], parsedCommand[3], false);
                    tasks.addTask(task);
                    ui.showAddTaskMessage(task, tasks);
                    break;
                }

                // After each command, save the current task list to the file
                storage.saveTasks(tasks);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                // For unexpected exceptions, show the full message
                ui.showError(e.toString());
            }
        }
    }
}
