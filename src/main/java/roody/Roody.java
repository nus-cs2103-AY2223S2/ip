package roody;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Roody {
    private ArrayList<Task> list;
    private Storage storage;
    private Ui ui;

    public Roody(String filepath){
        // Assumed no more than 100 tasks
        this.list = new ArrayList<Task>();
        this.ui = new Ui();
        this.storage = new Storage(filepath);
    }
    
    // toggles completion status of tasks
    private void completeTask(int index, boolean complete) throws RoodyException{
        if (index > list.size() - 1 || list.get(index) == null) {
            throw new RoodyException("Sorry, that task doesn't exist");
        } else {
            Task task = list.get(index);
            if (complete) {
                task.setDone();
            } else {
                task.setUnDone();
            }
            ui.showMarkStatus(complete, task);
        }
    }

    private void findTaskByKeyword(String keyword) throws RoodyException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : list) {
            // Splits by "|"
            String[] words = task.saveTask().split("\\|");
            // Further splits description by whitespace
            String[] desc = words[0].split("\\s");
            for (String word : desc) {
                // Searches for a match to keyword
                if (word.equals(keyword)) {
                    foundTasks.add(task);
                }
            }
        }
        ui.showFoundTasks(foundTasks);
    }

    private void deleteTask(int index) throws RoodyException{
        if (index > list.size() - 1 || list.get(index) == null) {
            throw new RoodyException("Sorry, that task doesn't exist");
        } else {
            Task task = list.get(index);
            list.remove(index);
            ui.showDeleteTask(task, list.size());
        }
    }

    public void run() {
        // Sends initial greeting
        ui.greet();
        list = storage.loadFile();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        Task task;
        while(!isExit) {
            ui.startNextLine();
            try {
                String command = scanner.nextLine();
                String[] commands = Parser.parse(command);
                switch (commands[0]) {
                    case "list":
                        ui.printList(list);
                        break;
                    case "todo":
                        if (commands.length < 2) {
                            throw new RoodyException("Tasks require a description");
                        }
                        task = new Todo(commands[1]);
                        list.add(task);
                        ui.showAddTask(task, list.size());
                        break;
                    case "deadline":
                        if (commands.length < 2) {
                            throw new RoodyException("Tasks require a description");
                        }
                        try {
                            task = new Deadline(commands[1], LocalDate.parse(commands[2]));
                            list.add(task);
                            ui.showAddTask(task, list.size());
                        } catch (DateTimeParseException e) {
                            throw new RoodyException("Accepted date format is yyyy-mm-dd.");
                        }
                        break;
                    case "event":
                        if (commands.length < 2) {
                            throw new RoodyException("Tasks require a description");
                        }
                        try {
                            task = new Event(commands[1], LocalDate.parse(commands[2]), LocalDate.parse(commands[3]));
                            list.add(task);
                            ui.showAddTask(task, list.size());
                        } catch (DateTimeParseException e) {
                            throw new RoodyException("Accepted date format is yyyy-mm-dd.");
                        }
                        break;
                    case "delete":
                    case "mark":
                    case "unmark":
                        if (commands.length != 2) {                    
                            throw new RoodyException("Please enter a index number to be marked/unmarked/deleted - \"mark/unmark/delete {index}\"");
                        }
                        int index = Integer.parseInt(commands[1]) - 1;
                        if (commands[0].equals("delete")) {
                            deleteTask(index);
                        } else { 
                            completeTask(index, commands[0].equals("mark"));
                        }
                        break;
                    case "find":
                        if (commands.length != 2) {
                            throw new RoodyException("Please enter a single keyword to be searched - \"find {keyword}\"");
                        }
                        findTaskByKeyword(commands[1]);
                        break;
                    case "bye":
                        isExit = true;
                        break;
                    default:
                        throw new RoodyException("I don't quite understand that.");
                }
            } catch (RoodyException e) {
                ui.showLine();
                System.out.println(e.getMessage());
            } finally {
                ui.showLine(); 
            }
        }
        ui.bye();
        scanner.close();
        storage.saveFile(list);
    }

    public static void main(String[] args){
        Roody roody = new Roody("./data/Roody.txt");
        roody.run();
    }
}
