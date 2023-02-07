package duke;

import javafx.application.Platform;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.sayHello();
        try {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String command = scanner.nextLine();
                String typeOfCommand = command.split(" ")[0];
                switch (typeOfCommand) {
                case "bye":
                    ui.sayBye();
                    break;
                case "list":
                    ui.showTaskList(tasks);
                    break;
                case "mark":
                    int indexOfTaskToMarkDone = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task markedTask = tasks.markTaskAsDone(indexOfTaskToMarkDone);
                    ui.showMarkingTaskDone(markedTask);
                    break;
                case "unmark":
                    int indexOfTaskToMarkUndone = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task unmarkedTask = tasks.markTaskAsUndone(indexOfTaskToMarkUndone);
                    ui.showMarkingTaskUndone(unmarkedTask);
                    break;
                case "todo":
                    Task newTodo = Parser.makeTodoFromCommand(command);
                    tasks.addTask(newTodo);
                    ui.showAddingNewTask(newTodo, tasks);
                    break;
                case "deadline":
                    Task newDeadline = Parser.makeDeadlineFromCommand(command);
                    tasks.addTask(newDeadline);
                    ui.showAddingNewTask(newDeadline, tasks);
                    break;
                case "event":
                    Task newEvent = Parser.makeEventFromCommand(command);
                    tasks.addTask(newEvent);
                    ui.showAddingNewTask(newEvent, tasks);
                    break;
                case "delete":
                    int indexOfTaskToDelete = Integer.parseInt(command.split(" ")[1]);
                    Task taskToDelete = tasks.deleteTask(indexOfTaskToDelete);
                    ui.showDeletingTask(taskToDelete, tasks);
                    break;
                case "find":
                    String searchWord = Parser.getSearchWord(command);
                    TaskList tasksFound = tasks.makeTaskFinder(searchWord);
                    ui.showFindingTask(tasksFound);
                    break;
                default:
                    throw new DukeException("Command not recognised.");
                }
                if (typeOfCommand.equals("bye")) {
                    break;
                }
                storage.updateTaskList(tasks);
                Ui.askForNextCommand();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Something went wrong while handling this task.");
        } catch (IOException e) {
            System.out.println("Something went wrong. ):");
        }
    }

    protected String getResponse(String command) {
        try {
            String typeOfCommand = command.split(" ")[0];
            switch (typeOfCommand) {
            case "bye":
                ui.sayBye();
                Platform.exit();
            case "list":
                return ui.showTaskList(tasks);
            case "mark":
                int indexOfTaskToMarkDone = Integer.parseInt(command.split(" ")[1]) - 1;
                Task markedTask = tasks.markTaskAsDone(indexOfTaskToMarkDone);
                storage.updateTaskList(tasks);
                return ui.showMarkingTaskDone(markedTask);
            case "unmark":
                int indexOfTaskToMarkUndone = Integer.parseInt(command.split(" ")[1]) - 1;
                Task unmarkedTask = tasks.markTaskAsUndone(indexOfTaskToMarkUndone);
                storage.updateTaskList(tasks);
                return ui.showMarkingTaskUndone(unmarkedTask);
            case "todo":
                Task newTodo = Parser.makeTodoFromCommand(command);
                tasks.addTask(newTodo);
                storage.updateTaskList(tasks);
                return ui.showAddingNewTask(newTodo, tasks);
            case "deadline":
                Task newDeadline = Parser.makeDeadlineFromCommand(command);
                tasks.addTask(newDeadline);
                storage.updateTaskList(tasks);
                return ui.showAddingNewTask(newDeadline, tasks);
            case "event":
                Task newEvent = Parser.makeEventFromCommand(command);
                tasks.addTask(newEvent);
                storage.updateTaskList(tasks);
                return ui.showAddingNewTask(newEvent, tasks);
            case "delete":
                int indexOfTaskToDelete = Integer.parseInt(command.split(" ")[1]);
                Task taskToDelete = tasks.deleteTask(indexOfTaskToDelete);
                storage.updateTaskList(tasks);
                return ui.showDeletingTask(taskToDelete, tasks);
            case "find":
                String searchWord = Parser.getSearchWord(command);
                TaskList tasksFound = tasks.makeTaskFinder(searchWord);
                storage.updateTaskList(tasks);
                return ui.showFindingTask(tasksFound);
            default:
                throw new DukeException("Command not recognised.");
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Something went wrong while handling this task.";
        } catch (NullPointerException | IOException e) {
            return "Something went wrong";
        }
    }

    public static void main(String[] args){
        new Duke("data/tasks.txt").run();
    }
}
