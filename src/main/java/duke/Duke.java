package duke;

import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = scanner.nextLine();
                Parser.parseUserResponse(userInput);
                switch(Parser.getCommand()) {
                    case BYE: {
                        storage.saveToFile(tasks.getTaskList());
                        ui.showGoodbyeMessage();
                        isExit = true;
                        break;
                    }
                    case FIND: {
                        String keyword = Parser.getArgs()[1];
                        ArrayList<Task> matchingTasks = tasks.getMatchingTasks(keyword);
                        ui.showMatchingTasksMessage(matchingTasks);
                        break;
                    }
                    case LIST: {
                        ui.showTasksMessage(tasks.getTaskList());
                        break;
                    }
                    case MARK: {
                        int id = Parser.parseTask(Parser.getArgs());
                        Task chosen = tasks.getTask(id);
                        tasks.markTask(chosen);
                        ui.markTaskMessage(chosen);
                        break;
                    }
                    case UNMARK: {
                        int id = Parser.parseTask(Parser.getArgs());
                        Task chosen = tasks.getTask(id);
                        tasks.unmarkTask(chosen);
                        ui.unmarkTaskMessage(chosen);
                        break;

                    }
                    case DELETE: {
                        int id = Parser.parseTask(Parser.getArgs());
                        Task chosen = tasks.getTask(id);
                        tasks.deleteTask(chosen);
                        ui.deleteTaskMessage(chosen, tasks.getTaskList());
                        break;
                    }
                    case TODO: {
                        Todo created = Parser.parseTodo(Parser.getArgs());
                        tasks.addTask(created);
                        ui.addedTaskMessage(created, tasks.getTaskList());
                        break;
                    }
                    case DEADLINE: {
                        Deadline created = Parser.parseDeadline(Parser.getArgs());
                        tasks.addTask(created);
                        ui.addedTaskMessage(created, tasks.getTaskList());
                        break;
                    }
                    case EVENT: {
                        Event created = Parser.parseEvent(Parser.getArgs());
                        tasks.addTask(created);
                        ui.addedTaskMessage(created, tasks.getTaskList());
                        break;
                    }

                }
            } catch(DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }

    }

    public static void main(String[] args){
        new Duke("/duke.txt").run();
    }
}