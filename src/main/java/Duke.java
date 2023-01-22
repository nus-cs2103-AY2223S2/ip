import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    static void validate(Integer length, Integer error_length, String task) throws DukeException {
        if (length == error_length) {
            throw new DukeException("☹ OOPS!!! The description of a " + task + " cannot be empty.");
        }
    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\nHello! I'm Duke\nWhat can I do for you?\n");

        ArrayList<Task> myList = new ArrayList<Task>(100);

        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < myList.size(); i++) {
                    System.out.println(String.valueOf(i + 1) + "." + myList.get(i).toString());
                }
            }
            else if (userInput.startsWith("mark")) {
                Matcher matcher = Pattern.compile("\\d+$").matcher(userInput);
                if (matcher.find()) {
                    int lastInteger = Integer.parseInt(matcher.group()) - 1;
                    myList.get(lastInteger).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(myList.get(lastInteger).toString());
                }
            }
            else if (userInput.startsWith("unmark")) {
                Matcher matcher = Pattern.compile("\\d+$").matcher(userInput);
                if (matcher.find()) {
                    int lastInteger = Integer.parseInt(matcher.group()) - 1;
                    myList.get(lastInteger).setUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(myList.get(lastInteger).toString());

                }
            }
            else if (userInput.startsWith("delete")) {
                Matcher matcher = Pattern.compile("\\d+$").matcher(userInput);
                if (matcher.find()) {
                    int lastInteger = Integer.parseInt(matcher.group()) - 1;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(myList.get(lastInteger).toString());
                    System.out.println("Now you have " + String.valueOf(myList.size() - 1) + " tasks in the list.");
                    myList.remove(lastInteger);
                }
            }
            else {
                Task task;
                if (userInput.startsWith("todo")) {
                    validate(userInput.length(), 4, "todo");
                    int idx = userInput.indexOf(" ");
                    String taskName = userInput.substring(idx + 1);
                    task = new ToDos(taskName);
                }
                else if (userInput.startsWith("deadline")) {
                    validate(userInput.length(), 8, "deadline");
                    int idx = userInput.indexOf(" ");
                    int by_idx = userInput.indexOf("/by");
                    String taskName = userInput.substring(idx + 1, by_idx);
                    String modifier = userInput.substring(by_idx + 3);
                    task = new Deadline(taskName, modifier);
                }
                else if (userInput.startsWith("event")) {
                    validate(userInput.length(), 5, "event");
                    int idx = userInput.indexOf(" ");
                    int from_idx = userInput.indexOf("/from");
                    int to_idx = userInput.indexOf("/to");
                    String taskName = userInput.substring(idx + 1, from_idx);
                    String fromDate = userInput.substring(from_idx + 5, to_idx);
                    String toDate = userInput.substring(to_idx + 3);
                    task = new Events(taskName, fromDate , toDate);
                }
                else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                myList.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task.toString());
                System.out.println("Now you have " + String.valueOf(myList.size()) + " tasks in the list.");
            }
            userInput = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }



}
