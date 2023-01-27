import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    Storage storage;
    ArrayList<Task> list;

    public Duke(String filePath, ArrayList<Task> list) throws IOException {
        storage = new Storage(filePath);
        Ui userInterface = new Ui();
        userInterface.welcomeMessage();
        storage.loadData(list);
        this.list = list;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

            while (true) {
                try {
                    Parser parser = new Parser();
                    String input = scanner.nextLine().trim();
                    if (parser.parserInput(list, input)) {
                        storage.saveData(list);
                    } else if (parser.parserInput(list, input)) {
                        storage.realTimeSave(list);
                    }
                }catch (DukeException e) {
                    System.out.println(e.message);
                }
            }
        }


    public static void main(String[] args) throws IOException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        Duke duke = new Duke("./userData/duke.txt", taskArrayList);
        duke.run();
    }
}

class Storage {
    private final File file;

    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
    }

    public void saveData(ArrayList<Task> tasks) {
        try {
            File userData = new File("userData");
            if (!userData.exists()) {
                userData.mkdir();
            }
            File dukeTxt = new File(userData, "duke.txt");
            if (!dukeTxt.exists()) {
                dukeTxt.createNewFile();
            } else {
                dukeTxt.delete();
                dukeTxt.createNewFile();
            }
            FileWriter fw = new FileWriter(dukeTxt);
            BufferedWriter dukeWrite = new BufferedWriter(fw);
            for (int i = 0; i < tasks.size(); i++) {
                dukeWrite.write(tasks.get(i).toString());
                dukeWrite.newLine();
            }
            dukeWrite.close();
        } catch (IOException e) {
            System.out.println("Oh no!!");
        }
    }

    public void loadData(ArrayList<Task> tasks) {
        try {
            File userData = new File("userData");
            if (!userData.exists()) {
                userData.mkdir();
            }
            File dukeTxt = new File(userData, "duke.txt");
            if (!dukeTxt.exists()) {
                dukeTxt.createNewFile();
            } else {
                FileReader fw = new FileReader(dukeTxt);
                BufferedReader dukeRead = new BufferedReader(fw);
                String line = dukeRead.readLine();
                while (line != null) {
                    if (line.contains("[D]")) {
                        tasks.add(new Deadline(line.replace("[D]", "")));
                    } else if (line.contains("[T]")) {
                        tasks.add(new ToDo(line.replace("[T]", "")));
                    } else if (line.contains("[E]")) {
                        tasks.add(new Event(line.replace("[E]", "")));
                    } else {
                        tasks.add(new Task(line));
                    }
                    line = dukeRead.readLine();
                }
                dukeRead.close();
            }
        } catch (IOException e) {
            System.out.println("Error: Data loading to the system fails !!");
        }
    }

    public void realTimeSave(ArrayList<Task> tasks) {
        try {
            File dukeTxt = new File("duke.txt");
            if (!dukeTxt.exists()) {
                dukeTxt.createNewFile();
            }
            FileWriter fw = new FileWriter(dukeTxt, true);
            BufferedWriter dukeWrite = new BufferedWriter(fw);
            int i = tasks.size() - 1;
            dukeWrite.write(tasks.get(i).toString());
            dukeWrite.newLine();
            dukeWrite.close();
        } catch (IOException e) {
            System.out.println("Oh no!!");
        }
    }

    public void deleteAll(ArrayList<Task> tasks) throws IOException {
        tasks.clear();
        File dukeTxt = new File("duke.txt");
        dukeTxt.delete();
        dukeTxt.createNewFile();
    }

}

class Ui {
    private final String markAsDone = "Nice! I've marked this task as done:";
    private final String unMarkTask = "OK, I've marked this task as not done yet:";
    private final String addedTask = "Got it, I've added this task:";

    public Ui() {

    }

    public void welcomeMessage() {
        System.out.println("Hello from Bench Monster");
        System.out.println("What can I do for you?");
    }

    public void setMarkAsDone() {
        System.out.println(markAsDone);
    }

    public void setUnMarkTask() {
        System.out.println(unMarkTask);
    }

    public void setAddedTask() {
        System.out.println(addedTask);
    }

}

class Parser {
    Ui userInterface = new Ui();
    TaskList handler = new TaskList();

    public boolean parserInput(ArrayList<Task> taskArrayList, String inputType) throws DukeException {
        String[] tokens = inputType.split("\\s+");
        if (tokens[0].equalsIgnoreCase("bye")) {
            handler.bye();
            return true;
        } else if (tokens[0].equalsIgnoreCase("list")) {
            handler.showList(taskArrayList);
            return true;
        } else if (tokens[0].equalsIgnoreCase("mark")) {
            int index = Integer.parseInt(tokens[1]);
            handler.mark(taskArrayList, index);
            return true;
        } else if (tokens[0].equalsIgnoreCase("unmark")) {
            int index = Integer.parseInt(tokens[1]);
            handler.unMark(taskArrayList, index);
            return true;
        } else if (tokens[0].equalsIgnoreCase("todo")) {
            String des = inputType.substring(inputType.indexOf(" ")).trim();
            handler.toDo(taskArrayList, des);
            return true;
        } else if (tokens[0].equalsIgnoreCase("deadline")) {
            handler.deadline(taskArrayList, inputType);
            return true;
        } else if (tokens[0].equalsIgnoreCase("event")) {
            handler.event(taskArrayList, inputType);
            return true;
        } else if (tokens[0].equalsIgnoreCase(("delete"))) {
            handler.delete(taskArrayList, inputType);
            return true;
        } else if (inputType.contains("by")) {
            handler.deadlineChecker(taskArrayList, inputType);
            return true;
        } else {
            System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
            return false;
        }
    }
}


class TaskList {
    Ui userInterface = new Ui();

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(1);
    }
    public void showList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int no = i + 1;
            System.out.println(no + "." + tasks.get(i));
        }
    }

    public void mark(ArrayList<Task> taskArrayList, int index) throws TaskNotExist {
        index -= 1;
        if (index >= taskArrayList.size()) {
            throw new TaskNotExist();
        }
        userInterface.setMarkAsDone();
        taskArrayList.get(index).mark();
        System.out.println(taskArrayList.get(index).toString());
    }

    public void unMark(ArrayList<Task> taskArrayList, int index) throws TaskNotExist {
        index -= 1;
        if (index >= taskArrayList.size()) {
            throw new TaskNotExist();
        }
        userInterface.setUnMarkTask();
        taskArrayList.get(index).unmarked();
        System.out.println(taskArrayList.get(index).toString());
    }

    public void toDo(ArrayList<Task> taskArrayList, String description) throws MissingDescription {
        if (description == null) {
            throw new MissingDescription();
        }
        Task newTask = new ToDo(description);
        userInterface.setAddedTask();
        taskArrayList.add(newTask);
        System.out.println(newTask);
        System.out.println("Now you have " + taskArrayList.size() + " task(s) in the list.");
    }

    public void deadline(ArrayList<Task> taskArrayList, String inputType) throws MissingDescription {
        try {
            DateStringConverter converter = new DateStringConverter();
            if (!inputType.contains(" ")) {
                throw new MissingDescription();
            }
            String des = inputType.substring(inputType.indexOf(" ")).trim();
            String[] deadline = des.split("/by");
            String[] timeExists = deadline[1].trim().split(" ");
            if (timeExists.length > 1) {
                String dateInString = timeExists[0];
                String timeInString = timeExists[1];
                LocalDate date = converter.convertDateInput(dateInString);
                LocalTime time = converter.convertTimeInput(timeInString);
                Deadline dead = new Deadline(deadline[0].trim(), date, time);
                taskArrayList.add(dead);
                System.out.println(dead);
            } else {
                String dateInString = deadline[1].trim();
                LocalDate date = converter.convertDateInput(dateInString);
                Deadline dead = new Deadline(deadline[0].trim(), date);
                taskArrayList.add(dead);
                System.out.println(dead);
            }
            userInterface.setAddedTask();
            System.out.println("Now you have " + taskArrayList.size() + " task(s) in the list.");
        } catch (DateTimeException e) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty and please enter in this date format (YYYY-MM-DD OR YYYY/MM/DD) or " +
                    "Day of the week (E.g. Monday, Tuesday, Wednesday).\n" +
                    "If time is provided, please key in this format 1200 after entering the date");
        }
    }

    public void event(ArrayList<Task> taskArrayList, String inputType) throws MissingDescription {
        if (!inputType.contains(" ")) {
            throw new MissingDescription();
        }
        String des = inputType.substring(inputType.indexOf(" "));
        String[] events = des.split("/");
        Event e = new Event(events[0].trim(), events[1].trim(), events[2].trim());
        taskArrayList.add(e);
        userInterface.setAddedTask();
        System.out.println(e);
        System.out.println("Now you have " + taskArrayList.size() + " task(s) in the list.");

    }

    public void delete(ArrayList<Task> taskArrayList, String inputType) {
        try {
            Storage storage = new Storage("./userRecords/duke.txt");
            if (!inputType.contains(" ")) {
                throw new DukeException("OOPS!! Please indicate the task index to delete!");
            }

            String[] index = inputType.split(" ");
            if (index[1].equalsIgnoreCase("all")) {
                storage.deleteAll(taskArrayList);
                System.out.println("Noted: I've removed all tasks");
            } else {
                int deleteIndex = Integer.parseInt(index[1]);
                if (deleteIndex > taskArrayList.size() || deleteIndex <= -1) {
                    throw new DukeException("OOPS!! The index requested to be deleted does not exist!");
                } else {
                    System.out.println("Noted: I've removed this task");
                    Task whichTask = taskArrayList.get(deleteIndex - 1);
                    System.out.println(whichTask);
                    taskArrayList.remove(deleteIndex - 1);
                }
            }
        } catch (DukeException | IOException e) {
            System.out.println("Oh no either file or the task doesn't exist. Please try again");
        }
    }

    public void deadlineChecker(ArrayList<Task> taskArrayList, String inputType) {
        try {
            String[] index = inputType.split("/");
            DateStringConverter converter = new DateStringConverter();
            LocalDate deadline = converter.convertDateInput(index[1].trim());
            ArrayList<Deadline> deadlineTasks = checkDeadlineTask(taskArrayList, deadline);
            System.out.println("Here is the list before this deadline: " + deadline);
            for (int i = 0; i < deadlineTasks.size(); i++) {
                System.out.println(deadlineTasks.get(i));
            }
        } catch (Exception e) {
            System.out.println("I do not understand what you type >.< !! Enter in by/ YYYY-MM-DD");
        }
    }

    private ArrayList<Deadline> checkDeadlineTask(ArrayList<Task> tasks, LocalDate date) {
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                Deadline singleTask = ((Deadline) tasks.get(i));
                String[] s = singleTask.toString().split(":");
                DateStringConverter converter = new DateStringConverter();
                LocalDate dueDate = converter.convertDateInput(s[1].replace(")", "").trim());
                if (dueDate.isBefore(date)) {
                    deadlineTasks.add(singleTask);
                }
            }
        }
        return deadlineTasks;
    }
}

class Task {
    private final String details;
    private boolean status;

    public Task(String details) {
        this.details = details;
        this.status = false;
    }

    public String isDone() {
        if (status) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public void mark() {
        status = true;
    }

    public void unmarked() {
        status = false;
    }

    @Override
    public String toString() {
        if (status && (details.contains("[ ]") || details.contains("[X]"))) {
            return details.replace("[ ]", "[X]");
        } else if (!status && (details.contains("[ ]") || details.contains("[X]"))) {
            return details.replace("[X]", "[ ]");
        } else if (!details.contains("[ ]") || !details.contains("[X]")) {
            return isDone() + " " + this.details;
        } else {
            return this.details;
        }
    }
}

class ToDo extends Task {
    String icon = "[T]";

    public ToDo(String details) {
        super(details);

    }

    @Override
    public String toString() {
        return icon + super.toString();
    }
}

class Deadline extends Task {
    String icon = "[D]";
    LocalDate date;
    LocalTime time;

    public Deadline(String details, LocalDate date, LocalTime time) {
        super(details);
        this.date = date;
        this.time = time;
    }

    public Deadline(String details, LocalDate date) {
        super(details);
        this.date = date;
    }

    public Deadline(String details) {
        super(details);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        if (time != null) {
            return icon + super.toString() + "(by: " + date + " " + time + ")";
        } else if (date != null) {
            return icon + super.toString() + "(by: " + date + ")";
        } else {
            return icon + super.toString();
        }
    }
}

class Event extends Task {
    String icon = "[E]";
    String from;
    String to;

    public Event(String details, String from, String to) {
        super(details);
        this.from = from.replace("from", "");
        this.to = to.replace("to", "");
    }

    public Event(String details) {
        super(details);
    }

    @Override
    public String toString() {
        return icon + super.toString() + " (from:" + from + " to:" + to + ")";
    }
}

class DateStringConverter {
    private final String[] DAY_OF_THE_WEEK = new String[]{"MONDAY", "TUESDAY", "WEDNESDAY",
            "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};

    public LocalDate convertDateInput(String input) {
        input.replace("/", "-");
        for (int i = 0; i < DAY_OF_THE_WEEK.length; i++) {
            if (input.equalsIgnoreCase(DAY_OF_THE_WEEK[i])) {
                DayOfWeek dayOfWeek = DayOfWeek.valueOf(input.toUpperCase());
                LocalDate today = LocalDate.now();
                LocalDate nextDate = today.with(dayOfWeek);
                return nextDate;
            }
        }
        return LocalDate.parse(input);
    }

    public LocalTime convertTimeInput(String input) {
        String hour = input.substring(0, 2);
        String mins = input.substring(2, 4);
        LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(mins));
        return time;
    }
}

class DukeException extends Exception {
    String message;

    public DukeException(String message) {
        this.message = message;
    }
}

class MissingDescription extends DukeException {
    public MissingDescription() {
        super("OOPS!!! The description of a task cannot be empty");
    }
}

class TaskNotExist extends DukeException {
    public TaskNotExist() {
        super("Invalid value, there isn't this much tasks in the list :-( ");
    }
}



