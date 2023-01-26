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

    public void start() {
        ui.showWelcome();
        loadTasks();
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
            }

        }

    }
    private void execute(String commandString) throws TreeBotException {
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
            saveTasks();
            break;
        case "mark":
            markTask(Integer.parseInt(splitStr[1]));
            saveTasks();
            break;
        case "unmark":
            unmarkTask(Integer.parseInt(splitStr[1]));
            saveTasks();
            break;
        case "delete":
            deleteTask(Integer.parseInt(splitStr[1]));
            saveTasks();
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

    private void saveTasks() {
        try {
            FileWriter fw = new FileWriter("data/treebot.txt");
            for (Task task : this.tasks) {
                fw.write(task.toStorageFormatString() + System.lineSeparator());
            }
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("file does not exist");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadTasks() {
        try {
            File f = new File("data/treebot.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String formatString = s.nextLine();
                this.tasks.add(formatStringToTask(formatString));
            }
        } catch (FileNotFoundException e) {
            System.out.println("file is not found");
        }
    }
    private Task formatStringToTask(String formatString) {
        String[] splitStr = formatString.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        Task task;
        switch (splitStr[0]) {
        case "T":
            task = new Todo(splitStr[2]);
            break;
        case "D":
            task =  new Deadline(splitStr[2], LocalDateTime.parse(splitStr[3], formatter));
            break;
        case "E":
            task = new Event(splitStr[2], LocalDateTime.parse(splitStr[3], formatter), LocalDateTime.parse(splitStr[4], formatter));
            break;
        default:
            task =  null;

        }

        if (splitStr[1].equals("1")) {
           task.markAsDone();
           return task;
        }

        return task;
    }

    private void exit() {
        System.out.println("Thank you, i'll be rooting for you.");
    }
}
