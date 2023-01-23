import java.util.*;
public class Duke {

    final static String ENTRY = "Hello! I'm Duke\nWhat can I do for you?";
    final static String EXIT_COMMAND = "bye";
    final static String LIST_COMMAND = "list";


    public static ArrayList<Task> list;

    public static void main(String[] args) {
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
                } else {
                    throw new InvalidInputException();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
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
        String date = splitedString[1];
        Task newTask = new Deadline(action, date);
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
        return word.equals("mark");
    }

    public static boolean isUnmark(String word) {
        return word.equals("unmark");
    }

    public static boolean isToDo(String word) {
        return word.equals("todo");
    }

    public static boolean isDeadline(String word) {
        return word.equals("deadline");
    }

    public static boolean isEvent(String word) {
        return word.equals("event");
    }


    public static void checkEmptyDescription(String[] inputWords) throws EmptyDescriptionException {
        if (inputWords.length < 2) {
            throw new EmptyDescriptionException();
        }
    }
}






