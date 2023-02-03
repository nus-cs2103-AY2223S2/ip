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
    
    // Stores input to string
    private void addToList(String input) {
        String[] inputs = input.split("/");
        for (String str : inputs) {
            str.trim();
        }
        Task task = new Todo(input.substring("todo ".length()).trim());
        char type = input.charAt(0);
        if (type == 't') {
        } else if (type == 'd') {
            // more than one / detected,
            if (inputs.length > 2) {
                new RoodyException("I don't understand that. Don't use additonal \"/\" for deadlines.");
                return;
            } else {
                inputs[0] = inputs[0].substring("deadline ".length()).trim();
                inputs[1] = inputs[1].substring("by ".length()).trim();
                try {
                    task = new Deadline(inputs[0], LocalDate.parse(inputs[1]));
                } catch (DateTimeParseException e) {
                    new RoodyException("Accepted date format is yyyy-mm-dd.");
                    return;
                }
            }
        } else if (type == 'e') {
            // more or less than two / detected,
            if (inputs.length != 3) {
                new RoodyException("I don't understand that. Don't use additonal \"/\" for events.");
                return;
            } else {
                inputs[0] = inputs[0].substring("event ".length()).trim();
                inputs[1] = inputs[1].substring("from ".length()).trim();
                inputs[2] = inputs[2].substring("to ".length()).trim();
                try {
                    task = new Event(inputs[0], LocalDate.parse(inputs[1]), LocalDate.parse(inputs[2]));
                } catch (DateTimeParseException e) {
                    new RoodyException("Accepted date format is yyyy-mm-dd.");
                    return;
                }
            }
        } else {
            new RoodyException("Error, wrong input detected");
            return;
        }
        list.add(task);
        ui.showAddTask(task, list.size());
    }

    /* 
    // Prints entire list in this.list
    private void printList() {
        int count = 0;
        int listIndex = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (!list.isEmpty()) {
            printBuffer.add("Here are the tasks in your list:");
            while (count < list.size()) {

                listIndex = count + 1;
                stringBuilder.append(listIndex);
                stringBuilder.append(".[");
                // get type
                stringBuilder.append(list.get(count).getType());
                stringBuilder.append("][");
                // if is done, set as 'X'
                if (list.get(count).isDone()) {
                    stringBuilder.append("X] ");
                // not done, set as ' '
                } else {
                    stringBuilder.append(" ] ");
                }
                stringBuilder.append(list.get(count).toString());
                printBuffer.add(stringBuilder.toString());
                
                // Clears and updates values
                stringBuilder.setLength(0);
                count++;
            }
            speak(printBuffer);
        } else {
            new RoodyException("There doesn't seem to be any tasks in your list.");
        }
    }
    */ 

    // toggles completion status of tasks
    private void complete(String index, boolean complete){
        int taskIndex = Integer.parseInt(index) - 1; 
        if (taskIndex > list.size() - 1 || list.get(taskIndex) == null) {
            new RoodyException("Sorry, that task doesn't exist");
        } else {
            Task task = list.get(taskIndex);
            if (complete) {
                task.setDone();
            } else {
                task.setUnDone();
            }
            ui.showMarkStatus(complete, task);
        }
    }

    private void delete(String index) {
        int taskIndex = Integer.parseInt(index) - 1;
        if (taskIndex > list.size() - 1 || list.get(taskIndex) == null) {
            new RoodyException("Sorry, that task doesn't exist");
        } else {
            Task task = list.get(taskIndex);
            list.remove(Integer.parseInt(index) - 1);
            ui.showDeleteTask(task, list.size());
        }
    }

    public void run() {
        // Sends initial greeting
        ui.greet();
        storage.loadFile();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        Task task;
        while(!isExit) {
            String command = scanner.nextLine();
            String[] commands = Parser.Parse(command);
            switch (commands[0]) {
                case "list":
                    ui.printList(list);
                    break;
                case "todo":
                    if (commands.length < 2) {
                        new RoodyException("Tasks require a description");
                        break;
                    }
                    task = new Todo(commands[1]);
                    list.add(task);
                    ui.showAddTask(task, list.size());
                    break;
                case "deadline":
                    if (commands.length < 2) {
                        new RoodyException("Tasks require a description");
                        break;
                    }
                    try {
                        task = new Deadline(commands[1], LocalDate.parse(commands[3]));
                        list.add(task);
                        ui.showAddTask(task, list.size());
                    } catch (DateTimeParseException e) {
                        new RoodyException("Accepted date format is yyyy-mm-dd.");
                    }
                    break;
                case "event":
                    if (commands.length < 2) {
                        new RoodyException("Tasks require a description");
                        break;
                    }
                    try {
                        task = new Event(commands[1], LocalDate.parse(commands[3]), LocalDate.parse(commands[5]));
                        list.add(task);
                        ui.showAddTask(task, list.size());
                    } catch (DateTimeParseException e) {
                        new RoodyException("Accepted date format is yyyy-mm-dd.");
                    }
                    break;
                case "delete":
                    if (commands.length == 2) {                    
                        delete(commands[1]);
                    } else {
                        new RoodyException("Please enter a index number to be deleted");
                    }
                    break;
                case "mark":
                case "unmark":
                    try {
                        if (commands.length == 2) {                    
                            complete(commands[1], commands[0].equals("mark"));
                        } else {
                            new RoodyException("Please enter a index number to be marked/unmarked");
                        }
                    } catch (NumberFormatException e) {
                        new RoodyException("Please enter a valid index.");
                    }
                    break;
                case "bye":
                    ui.bye();
                    isExit = true;
                    break;
                default:
                    new RoodyException("I don't quite understand that.");
            }
        }
        scanner.close();
        storage.saveFile(list);
    }

    public static void main(String[] args){
        Roody roody = new Roody("./data/Roody.txt");
        roody.run();
    }
}
