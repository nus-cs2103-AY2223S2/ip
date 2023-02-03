import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.PrintWriter;
import java.io.IOException;

public class Duke {

    final static String ENTRY = "Hello! I'm Duke\nWhat can I do for you?";
    final static String EXIT_COMMAND = "bye";
    final static String LIST_COMMAND = "list";
    final static String DELETE_COMMAND = "delete";
    final static String MARK_COMMAND = "mark";
    final static String UNMARK_COMMAND = "unmark";
    final static String TODO_COMMAND = "todo";
    final static String DEADLINE_COMMAND = "deadline";
    final static String EVENT_COMMAND = "event";
    final static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/d HHmm");

    final static DateTimeFormatter outputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


    public static ArrayList<Task> list;

    public static void main(String[] args) throws IOException {
        System.out.println(ENTRY);
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<Task>();
        while (true) {
            try {
                // handling input
                String input = sc.nextLine();
                String[] inputWords = input.split(" ", 2);
                String command = inputWords[0];
                if (command.equals(EXIT_COMMAND)) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals(LIST_COMMAND)) {
                    getList();
                } else if (isMark(command)) {
                    handleMark(inputWords);
                } else if (isUnmark(command)) {
                    handleUnmark(inputWords);
                } else if (isToDo(command)) {
                    handleToDo(inputWords);
                } else if (isDeadline(command)) {
                    handleDeadline(inputWords);
                } else if (isEvent(command)) {
                    handleEvent(inputWords);
                } else if (command.equals(DELETE_COMMAND)) {
                    handleDelete(inputWords);
                } else {
                    throw new InvalidInputException();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        save();
        sc.close();
    }

    public static void handleDelete(String[] inputWords) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.get(index - 1);
        list.remove(index-1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
    }

    public static void handleMark(String[] inputWords) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.get(index - 1);
        task.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    public static void handleUnmark(String[] inputWords) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.get(index - 1);
        task.unmark();
        System.out.println("OK,, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }

    public static void addTask(Task newTask) {
        list.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
    }

    public static void handleToDo(String[] inputWords) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String description = inputWords[1];
        Task newTask = new ToDo(description);
        addTask(newTask);
    }

    public static void handleDeadline(String[] inputWords) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String[] splitedString = inputWords[1].split(" /by ");
        String action = splitedString[0];
        String date = splitedString[1]; // in yyyy-mm-dd HHMM format
        LocalDateTime inputDateTime = LocalDateTime.parse(date, inputFormatter);
        String outputDateTime = inputDateTime.format(outputFormatter);
        Task newTask = new Deadline(action, outputDateTime);
        addTask(newTask);
    }

    public static void handleEvent(String[] inputWords) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String[] splitedString = inputWords[1].split(" /from ");
        String action = splitedString[0];
        String duration = splitedString[1];
        String[] fromTo = duration.split(" /to ");
        String from = fromTo[0];
        String to = fromTo[1];
        Task newTask = new Event(action, from, to);
        addTask(newTask);
    }
    public static void getList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println(i+1 + "." + task.toString());
        }
    }

    public static boolean isMark(String word) {
        return word.equals(MARK_COMMAND);
    }

    public static boolean isUnmark(String word) {
        return word.equals(UNMARK_COMMAND);
    }

    public static boolean isToDo(String word) {
        return word.equals(TODO_COMMAND);
    }

    public static boolean isDeadline(String word) {
        return word.equals(DEADLINE_COMMAND);
    }

    public static boolean isEvent(String word) {
        return word.equals(EVENT_COMMAND);
    }


    public static void checkEmptyDescription(String[] inputWords) throws EmptyDescriptionException {
        if (inputWords.length < 2) {
            throw new EmptyDescriptionException();
        }
    }

    public static void save() throws IOException {
        String home = System.getProperty("user.dir");
        PrintWriter output = new PrintWriter(home + File.separator + "data" + File.separator + "duke.txt");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            output.println(i+1 + "." + task.toString());
        }
        output.close();
    }
}






