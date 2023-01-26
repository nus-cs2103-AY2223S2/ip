import exception.InvalidCommandException;
import exception.TaskFactoryException;
import exception.TreeBotException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeBot {
    private static final String EXIT_TOKEN = "bye";
    private ArrayList<Task> tasks = new ArrayList<>();
    private TaskFactory taskFactory = new TaskFactory();
    private Ui ui = new Ui();
    private Storage storage = new Storage("data/treebot.txt");

    public TreeBot() throws IOException {
    }

    public void start() {
        ui.showWelcome();
        listen();
    }
    private void listen() {
        while (true) {

            String commandString = ui.readCommand();

            if (commandString.equals(EXIT_TOKEN)) {
                exit();
                break;
            }

            try {
                execute(commandString);
            } catch (TreeBotException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("cant execute due to storage error");
            }

        }

    }
    private void execute(String commandString) throws TreeBotException, IOException {
        String[] splitStr = commandString.split("\\s+", 2);
        String command = splitStr[0];

        switch (command) {
        case "list":
            listTasks();
            break;
        case "todo":
        case "deadline":
        case "event":
            addTask(this.taskFactory.make(commandString));
            storage.saveTasks(this.tasks);
            break;
        case "mark":
            markTask(Integer.parseInt(splitStr[1]));
            storage.saveTasks(this.tasks);
            break;
        case "unmark":
            unmarkTask(Integer.parseInt(splitStr[1]));
            storage.saveTasks(this.tasks);
            break;
        case "delete":
            deleteTask(Integer.parseInt(splitStr[1]));
            storage.saveTasks(this.tasks);
            break;
        default:
            throw new InvalidCommandException("This command is invalid");
        }
    }
    private void addTask(Task task) {
        this.tasks.add(task);
    }
    private void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + "." + this.tasks.get(i));
        }
    }
    private void markTask(int idx) {
        this.tasks.get(idx - 1).markAsDone();
    }
    private void unmarkTask(int idx) {
        this.tasks.get(idx - 1).markAsUndone();
    }

    private void deleteTask(int idx) {
        this.tasks.remove(idx - 1);
    }


    private void greet() {
        System.out.println("Hello, I'm a tree. How may I be of service?");
    }

    private void echo(String command) {
        System.out.println(command);
    }



    private void exit() {
        System.out.println("Thank you, i'll be rooting for you.");
    }
}
