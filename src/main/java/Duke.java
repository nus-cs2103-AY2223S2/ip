import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Duke {
    private static final String LINE_STRING = "____________________________________________________________";
    private static final String WELCOME_STRING = "Hello! I'm Duke\n      What can I do for grades?";
    private static final String END_STRING = "Bye. Hope to see you again soon!";
    private static final String EMPTY_LIST_STRING = "Hey, the list is empty!";
    private static final String MARK_DONE_STRING = "Nice! I've marked this task as done\n       ";
    private static final String UNMARK_DONE_STRING = "OK, I've marked this task as not done yet\n       ";
    private static final String NO_INT_ERR_STRING = "Hey, you did not enter any numbers";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);

        printer(WELCOME_STRING);

        ArrayList<Tasks> todoList = new ArrayList<Tasks>(100);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            boolean end = false;
            switch (input) {
                case "list":
                    if (todoList.size() == 0) {
                        printer(EMPTY_LIST_STRING);
                    } else {
                        String toPrint = "";
                        for (int i = 0; i < todoList.size(); i++) {
                            toPrint += ((i + 1) + ". " + todoList.get(i)) + "\n      ";
                        }
                        printer(toPrint.substring(0, toPrint.length() - 7));
                    }
                    break;
                case "bye":
                    end = true;
                    printer(END_STRING);
                    break;
                case "exit":
                    end = true;
                    printer(END_STRING);
                    break;
                default:
                    if (input.contains("mark")) {
                        try {
                            int taskNo = getNumbers(input) - 1;
                            if (input.contains("unmark")) {
                                todoList.get(taskNo).unmarkAsDone();
                                printer(UNMARK_DONE_STRING + todoList.get(taskNo));
                            } else {
                                todoList.get(taskNo).markAsDone();
                                printer(MARK_DONE_STRING + todoList.get(taskNo));
                            }
                        } catch (Exception e) {
                            printer(NO_INT_ERR_STRING);
                        }
                        break;
                    } else {
                        todoList.add(new Tasks(input));
                        printer("added: " + input);
                    }
                    break;
            }
            if (end) {
                break;
            }
        }
        sc.close();
    }

    private static void printer(String toPrint) {
        System.out.println("    " + LINE_STRING);
        System.out.println("      " + toPrint);
        System.out.println("    " + LINE_STRING);
        System.out.println();
    }

    private static int getNumbers(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String numberString = matcher.group();
            int number = Integer.parseInt(numberString);
            return number;
        } else {
            throw new InputMismatchException("Invalid input. Please enter a number.");
        }
    }
}

class Tasks {
    private String title;
    private boolean done = false;

    Tasks(String title) {
        this.title = title;
    }

    void markAsDone() {
        this.done = true;
    }

    void unmarkAsDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String returnString = "";
        if (done) {
            returnString = "[X] ";
        } else {
            returnString = "[ ] ";
        }
        return returnString + title;
    }

}
