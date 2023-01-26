import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static final String FILE_PATH = "./userRecords/records.txt";

    private static void printDashes() {
        System.out.println ("****************************************");
    }

    private static void printList(ArrayList<Task> items) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= items.size(); i++) {
            System.out.println(i + "." + items.get(i-1));
        }
    }

    private static void numOfTasks(ArrayList<Task> items) {
        System.out.println("Now you have " + items.size() + " tasks in the list.");
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Task> list = new ArrayList<>();
        String request = "";
        int chosenTask;
        recordManager manager = new recordManager(FILE_PATH);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);

        printDashes();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! This is Esther!");
        System.out.println("How can I help you today? ^_^");
        printDashes();

        System.out.println("Here is your previous records. ^_^");
        list.addAll(manager.loadRecord(FILE_PATH));
        printDashes();

        while(!request.equals("bye")) {
            String userInput = sc.nextLine().trim();
            request = userInput.split("\\s+")[0];

            try{
                switch(request) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon! ^_^");
                        break;
                    case "list":
                        printList(list);
                        break;
                    case "mark":
                        if(userInput.indexOf(" ") == -1) {
                            throw new missingNumber("mark");
                        }
                        chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                        if(chosenTask > list.size() || chosenTask < 1) {
                            throw new taskNotExist();
                        }
                        list.get(chosenTask - 1).setIsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(chosenTask - 1));
                        manager.writeToFile(list);
                        break;
                    case "unmark":
                        if(userInput.indexOf(" ") == -1) {
                            throw new missingNumber("unmark");
                        }
                        chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                        if(chosenTask > list.size() || chosenTask < 1) {
                            throw new taskNotExist();
                        }
                        list.get(chosenTask - 1).revertIsDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list.get(chosenTask - 1));
                        manager.writeToFile(list);
                        break;
                    case "todo":
                        if(userInput.indexOf(" ") == -1) {
                            throw new missingDescription("todo");
                        }
                        String todo_descrip = userInput.substring(userInput.indexOf(" ")).trim();
                        Todo todo = new Todo(todo_descrip);
                        list.add(todo);
                        System.out.println("Got it. I've added this task:\n" + todo);
                        numOfTasks(list);
                        manager.writeToFile(list);
                        break;
                    case "deadline":
                        if(userInput.indexOf(" ") == -1) {
                            throw new missingDescription("deadline");
                        }
                        String dd_full = userInput.substring(userInput.indexOf(" ")).trim();
                        String dd_descrip = dd_full.split("/")[0];
                        String dd_date = dd_full.split("/")[1].substring(dd_full.split("/")[1].indexOf(" ")).trim();
                        Deadline deadline = new Deadline(dd_descrip, dd_date);
                        list.add(deadline);
                        System.out.println("Got it. I've added this task:\n" + deadline);
                        numOfTasks(list);
                        manager.writeToFile(list);
                        break;
                    case "event":
                        if(userInput.indexOf(" ") == -1) {
                            throw new missingDescription("event");
                        }
                        String event_full = userInput.substring(userInput.indexOf(" ")).trim();
                        String event_descrip = event_full.split("/")[0];
                        String event_from = event_full.split("/")[1].substring(event_full.split("/")[1].indexOf(" ")).trim();
                        String event_to = event_full.split("/")[2].substring(event_full.split("/")[2].indexOf(" ")).trim();
                        Event event = new Event(event_descrip, event_from, event_to);
                        list.add(event);
                        System.out.println("Got it. I've added this task:\n" + event);
                        numOfTasks(list);
                        manager.writeToFile(list);
                        break;
                    case "delete":
                        if(userInput.indexOf(" ") == -1) {
                            throw new missingNumber("delete");
                        }
                        chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                        if(chosenTask > list.size() || chosenTask < 1) {
                            throw new taskNotExist();
                        }
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(list.get(chosenTask - 1));
                        list.remove(chosenTask - 1);
                        numOfTasks(list);
                        manager.writeToFile(list);
                        break;
                    default:
                        throw new unknownInputException();
                }
            } catch (DukeException e) {
                System.out.println(e.eMessage);
            } catch(NumberFormatException e) {
                System.out.println("The operation must follow by a integer");
            } catch (IOException e) {
                System.out.println(e);
            }
            printDashes();
        }
    }
}

class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone() {
        isDone = true;
    }

    public void revertIsDone() {
        isDone = false;
    }

    public String getTaskDes() {
        return taskDescription;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.getTaskDes();
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}

class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}

class DukeException extends Exception {
    protected String eMessage;

    public DukeException(String eMessage) {
        this.eMessage = eMessage;
    }
}

class unknownInputException extends DukeException {
    public unknownInputException() {
        super("Oh no, I am not sure what that means, could you try again?");
    }
}

class missingDescription extends DukeException {
    public missingDescription(String taskType) {
        super("Oh no, the description of a " + taskType + " cannot be empty! Please try again.");
    }
}

class missingNumber extends DukeException {
    public missingNumber(String operationType) {
        super("Oh no, the " + operationType + " must specific the task number! Please try again.");
    }
}

class taskNotExist extends DukeException {
    public taskNotExist() {
        super("Oh no, the task is not exist yet! Please try again.");
    }
}

class recordManager {
    private final File file;

    public recordManager(String filePath) throws IOException {
        this.file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public ArrayList<Task> loadRecord(String filePath) throws IOException {
        Scanner sc = new Scanner(this.file);
        int index = 1;
        ArrayList<Task> records = new ArrayList<>();

        while (sc.hasNext()) {
            String history = sc.nextLine();
            System.out.println(index++ + "." + history);
            String recordType = history.substring(0, 3);
            String recordDescription = history.substring(7);
            String recordStatus = history.substring(3, 6);
            Task item = null;

            switch (recordType) {
                case "[T]":
                    item = new Todo(recordDescription);
                    break;
                case "[D]":
                    String byDate = recordDescription.substring(recordDescription.indexOf(":") + 2, recordDescription.length() - 1);
                    recordDescription = recordDescription.substring(0, recordDescription.indexOf("("));
                    item = new Deadline(recordDescription, byDate);
                    break;
                case "[E]":
                    String fromDate = recordDescription.substring(recordDescription.indexOf("from:") + 6, recordDescription.indexOf("to:") - 1);
                    String toDate = recordDescription.substring(recordDescription.indexOf("to:") + 4, recordDescription.length() - 1);
                    recordDescription = recordDescription.substring(0, recordDescription.indexOf("("));
                    item = new Event(recordDescription, fromDate, toDate);
                    break;
            }
            if(recordStatus.equals("[X]")) {
                item.setIsDone();
            }
            records.add(item);
        }
        return records;
    }

    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for(int i = 0; i < tasks.size(); i++) {
            fw.write((tasks.get(i)).toString());
            fw.write("\n");
        }
        fw.close();
    }
}