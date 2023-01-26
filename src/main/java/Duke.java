import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

class TimeConvertor {
    private LocalDateTime realDate;

    public TimeConvertor(String timing) {
        this.realDate = LocalDateTime.parse(timing, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public TimeConvertor(String timing, String type) {
        this.realDate = LocalDateTime.parse(timing, DateTimeFormatter.ofPattern(type));
    }

    public String getDate() {
        return realDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getDate(String type) {
        return realDate.format(DateTimeFormatter.ofPattern(type));
    }

    @Override
    public String toString() {
        return realDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
    }
}

class Ui {
    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printDashes();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! This is Esther!");
        System.out.println("How can I help you today? ^_^");
        System.out.println(
                "Please follow the date format: yyyy-MM-dd hh:mm (e.g. 2019-10-15 18:00), otherwise your input will be invalid.");
        printDashes();
    }

    public void printDashes() {
        System.out.println("****************************************");
    }

    public void println(String ss) {
        System.out.println(ss);
    }
}

class Storage {
    private final File file;

    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public ArrayList<String> loadRecord() throws IOException {
        Scanner sc = new Scanner(this.file);
        ArrayList<String> records = new ArrayList<>();
        while (sc.hasNext()) {
            records.add(sc.nextLine());
        }
        return records;
    }

    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toString() + "\n");
        }
        fw.close();
    }
}

class Parser {
    public static Task parseFile(String userInput) {
        String recordType = userInput.substring(0, 3);
        String recordStatus = userInput.substring(3, 6);
        String recordDescription = userInput.substring(7);
        Task item = null;

        switch (recordType) {
            case "[T]":
                item = new Todo(recordDescription);
                break;
            case "[D]":
                String byDate = recordDescription.substring(recordDescription.indexOf(":") + 2,
                        recordDescription.length() - 1);
                recordDescription = recordDescription.substring(0, recordDescription.indexOf("("));
                item = new Deadline(recordDescription, new TimeConvertor(byDate, "MMM dd yyyy hh:mma"));
                break;
            case "[E]":
                String fromDate = recordDescription.substring(recordDescription.indexOf("from:") + 6,
                        recordDescription.indexOf("to:") - 1);
                String toDate = recordDescription.substring(recordDescription.indexOf("to:") + 4,
                        recordDescription.length() - 1);
                recordDescription = recordDescription.substring(0, recordDescription.indexOf("("));
                item = new Event(recordDescription, new TimeConvertor(fromDate, "MMM dd yyyy hh:mma"),
                        new TimeConvertor(toDate, "MMM dd yyyy hh:mma"));
                break;
        }
        if (recordStatus.equals("[X]")) {
            item.setIsDone();
        }
        return item;
    }

    public static boolean parseInput(TaskList list, String userInput)
            throws taskNotExist, missingNumber, missingDescription {
        int chosenTask;
        switch (userInput.split("\\s+")[0]) {
            case "bye":
                list.Bye();
                return false;
            case "list":
                list.printList();
                return false;
            case "mark":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingNumber("mark");
                }
                chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                list.mark(chosenTask);
                return true;
            case "unmark":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingNumber("unmark");
                }
                chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                list.unmark(chosenTask);
                return true;
            case "todo":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingDescription("todo");
                }
                String todo_descrip = userInput.substring(userInput.indexOf(" ")).trim();
                list.todo(todo_descrip);
                return true;
            case "deadline":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingDescription("deadline");
                }
                if (userInput.indexOf("/") == -1) {
                    throw new missingDescription("deadline");
                }
                String dd_full = userInput.substring(userInput.indexOf(" ")).trim();
                String dd_descrip = dd_full.split("/")[0];
                String dd_date = dd_full.split("/")[1].substring(dd_full.split("/")[1].indexOf(" ")).trim();
                list.deadline(dd_descrip, new TimeConvertor(dd_date));
                return true;
            case "event":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingDescription("event");
                }
                if (userInput.indexOf("/") == -1) {
                    throw new missingDescription("event");
                }
                String event_full = userInput.substring(userInput.indexOf(" ")).trim();
                String event_descrip = event_full.split("/")[0];
                String event_from = event_full.split("/")[1].substring(event_full.split("/")[1].indexOf(" "))
                        .trim();
                String event_to = event_full.split("/")[2].substring(event_full.split("/")[2].indexOf(" "))
                        .trim();
                list.event(event_descrip, new TimeConvertor(event_from), new TimeConvertor(event_to));
                return true;
            case "delete":
                if (userInput.indexOf(" ") == -1) {
                    throw new missingNumber("delete");
                }
                chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                list.delete(chosenTask);
                return true;
            case "check":
                if (userInput.indexOf("/") == -1) {
                    throw new missingNumber("check");
                }
                String checkDeadline = userInput.split("/")[1];
                list.check(checkDeadline);
                return false;
            default:
                System.out.println("Oh no, I am not sure what that means, could you try again?");
                return false;
        }
    }
}

class TaskList {
    ArrayList<Task> list = new ArrayList<>();

    public TaskList(ArrayList<String> input) {
        for (String item : input) {
            list.add(Parser.parseFile(item));
        }
    }

    public TaskList() {

    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void Bye() {
        System.out.println("Bye. Hope to see you again soon! ^_^");
        System.exit(0);
    }

    public void printList() {
        if (list.size() == 0) {
            System.out.println("No tasks in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + "." + list.get(i - 1));
            }
        }
    }

    public void mark(int index) throws taskNotExist {
        if (index > list.size() || index < 1) {
            throw new taskNotExist();
        }
        list.get(index - 1).setIsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index - 1));
    }

    public void unmark(int index) throws taskNotExist {
        if (index > list.size() || index < 1) {
            throw new taskNotExist();
        }
        list.get(index - 1).revertIsDone();
        System.out.println("Nice! I've marked this task as not done:");
        System.out.println(list.get(index - 1));
    }

    public void todo(String desc) {
        Todo todo = new Todo(desc);
        list.add(todo);
        System.out.println("Got it. I've added this task:\n" + todo);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void deadline(String desc, TimeConvertor time) {
        Deadline deadline = new Deadline(desc, time);
        list.add(deadline);
        System.out.println("Got it. I've added this task:\n" + deadline);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void event(String desc, TimeConvertor from, TimeConvertor to) {
        Event event = new Event(desc, from, to);
        list.add(event);
        System.out.println("Got it. I've added this task:\n" + event);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void delete(int index) throws taskNotExist {
        if (index > list.size() || index < 1) {
            throw new taskNotExist();
        }
        System.out.println("Got it. I've remove this task:\n" + list.remove(index - 1));
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void check(String checkDeadline) {
        boolean ifDeadlineExist = false;
        for (int i = 0; i < list.size(); i++) {
            Task currT = list.get(i);
            if (currT instanceof Deadline) {
                Deadline dTask = (Deadline) currT;
                if (dTask.getDeadline().equals(checkDeadline)) {
                    ifDeadlineExist = true;
                    System.out.println(i + "." + dTask);
                }
            }
        }
        if (!ifDeadlineExist) {
            System.out.println("No deadline found on " + checkDeadline);
        }
    }

}

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadRecord());
            tasks.printList();
            ui.printDashes();
        } catch (IOException e) {
            System.out.println("Error occurs when try to load.");
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                if (Parser.parseInput(tasks, sc.nextLine().trim())) {
                    storage.writeToFile(tasks);
                }
            } catch (DukeException e) {
                ui.println(e.eMessage);
            } catch (NumberFormatException e) {
                ui.println("The operation must follow by a integer");
            } catch (IOException e) {
                ui.println("Error occurs when try to access your file");
            } catch (DateTimeException e) {
                ui.println("Invalid date time format, please try again!");
            }
            ui.printDashes();
        }
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("./userRecords/records.txt");
        duke.run();
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
    private TimeConvertor by;

    public Deadline(String description, TimeConvertor by) {
        super(description);
        this.by = by;
    }

    public String getDeadline() {
        return this.by.getDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}

class Event extends Task {
    private TimeConvertor from;
    private TimeConvertor to;

    public Event(String description, TimeConvertor from, TimeConvertor to) {
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