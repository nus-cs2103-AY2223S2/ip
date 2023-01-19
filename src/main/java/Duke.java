import java.io.InputStreamReader;
import java.io.*;
import java.util.*;

public class Duke {

    static List<Task> storedText = new ArrayList<Task>();
    public static void main(String[] args) throws IOException, DukeExceptions, StringIndexOutOfBoundsException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        processUserInput(bReader);
        System.out.println("Bye! Hope to see you again soon!");
    }

    public static void greeting() {
        System.out.println("Hello, I am Duke!\nWhat can I do for you?");
    }

    public static void processUserInput(BufferedReader brToUse) throws IOException, DukeExceptions, StringIndexOutOfBoundsException{
        String userInput = brToUse.readLine();

        while (!userInput.equals("bye")) {
            try {
                if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < storedText.size(); i++) {
                        Integer currIndex = i + 1;
                        Task currTask = storedText.get(i);
                        String toUse = currIndex.toString() + "." + currTask.toString();
                        System.out.println(toUse);
                    }
                    userInput = brToUse.readLine();
                    continue;
                }

                if (userInput.startsWith("delete")) {
                    String[] commandSplit = userInput.split(" ");
                    if (commandSplit.length <= 1) {
                        throw new DukeExceptions("");
                    }
                    int indexToUse = Integer.parseInt(commandSplit[1]) - 1;
                    if (indexToUse >= storedText.size() || indexToUse < 0) {
                        throw new DukeExceptions("Wrong size for mark/unmark");
                    }
                    Task gettingTask = storedText.remove(indexToUse);
                    String toOutput = "Noted. I've removed this task:\n  " + gettingTask.toString() + "\nNow you have " + storedText.size() + " tasks in the list";
                    System.out.println(toOutput);
                    userInput = brToUse.readLine();
                    continue;
                }

                if (userInput.startsWith("mark")) {
                    String[] commandSplit = userInput.split(" ");
                    if (commandSplit.length <= 1) {
                        throw new DukeExceptions("");
                    }
                    int indexToUse = Integer.parseInt(commandSplit[1]) - 1;
                    if (indexToUse >= storedText.size() || indexToUse < 0) {
                        throw new DukeExceptions("Wrong size for mark/unmark");
                    }
                    Task currTask = storedText.get(indexToUse);
                    currTask.setDone();
                    // String toOutput = "Nice! I've marked this task as done:\n" + "[X] " + currTask.getName();
                    String toOutput = "Nice! I've marked this task as done:\n  " + currTask.toString();
                    System.out.println(toOutput);
                    userInput = brToUse.readLine();
                    continue;
                }

                if (userInput.startsWith("unmark")) {
                    String[] commandSplit = userInput.split(" ");
                    if (commandSplit.length <= 1) {
                        throw new DukeExceptions("");
                    }
                    int indexToUse = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (indexToUse >= storedText.size() || indexToUse < 0) {
                        throw new DukeExceptions("Wrong size for mark/unmark");
                    }
                    Task currTask = storedText.get(indexToUse);
                    currTask.setUndone();
                    String toOutput = "Ok, I've marked this task as not done yet:\n  " + currTask.toString();
                    System.out.println(toOutput);
                    userInput = brToUse.readLine();
                    continue;
                }

                if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                    String useForInit = "";
                    Task addTask = new Task("");
                    if (userInput.startsWith("todo")) {
                        useForInit = userInput.substring(4);
                        addTask = new ToDo(useForInit);
                    } else if (userInput.startsWith("deadline")) {
                        useForInit = userInput.substring(8);
                        addTask = new Deadlines(useForInit);
                    } else {
                        useForInit = userInput.substring(5);
                        addTask = new Events(useForInit);
                    }
                    storedText.add(addTask);
                    String toPrint = "";
                    if (storedText.size() == 1) {
                        toPrint = "Got it. I've added this task:\n  " + addTask.toString() + "\nNow you have " + storedText.size() + " task in the list.";
                    } else {
                        toPrint = "Got it. I've added this task:\n  " + addTask.toString() + "\nNow you have " + storedText.size() + " tasks in the list.";                   
                    }
                    System.out.println(toPrint);
                    userInput = brToUse.readLine();
                    continue;
                }
                throw new DukeExceptions("");
        } catch (DukeExceptions e) {
            System.out.println(e.toString());
            userInput = brToUse.readLine();
        }

            //Clarify if still need to have level-2 functionality.
            // Task toAdd = new Task(userInput);
            // storedText.add(toAdd);
            // String toOutput = "added: " + toAdd.name;
            // System.out.println(toOutput);
            // userInput = brToUse.readLine();

        }
    }

}
