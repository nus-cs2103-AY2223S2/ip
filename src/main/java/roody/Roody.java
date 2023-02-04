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
    private void complete(int index, boolean complete) throws RoodyException{
        if (index > list.size() - 1 || list.get(index) == null) {
            new RoodyException("Sorry, that task doesn't exist");
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
    

    private void delete(int index) {
        if (index > list.size() - 1 || list.get(index) == null) {
            new RoodyException("Sorry, that task doesn't exist");
        } else {
            Task task = list.get(index);
            list.remove(index - 1);
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
                String[] commands = Parser.Parse(command);
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
                            task = new Deadline(commands[1], LocalDate.parse(commands[3]));
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
                            task = new Event(commands[1], LocalDate.parse(commands[3]), LocalDate.parse(commands[5]));
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
                        int index = Integer.parseInt(commands[0]) - 1;
                        if (commands[0].equals("delete")) {
                            delete(index);
                        } else { 
                            complete(index, commands[0].equals("mark"));
                        }
                        break;
                    case "bye":
                        isExit = true;
                        break;
                    default:
                        throw new RoodyException("I don't quite understand that.");
                }
            // mark/unmark index
            } catch (Exception e) { 
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
