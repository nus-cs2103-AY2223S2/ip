import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
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
                    System.out.println(String.valueOf(i + 1) + ".[" + myList.get(i).getStatusIcon() + "] " +  myList.get(i).getTaskDesc());
                }
            }
            else if (userInput.startsWith("mark")) {
                Matcher matcher = Pattern.compile("\\d+$").matcher(userInput);
                if (matcher.find()) {
                    int lastInteger = Integer.parseInt(matcher.group()) - 1;
                    myList.get(lastInteger).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + myList.get(lastInteger).getStatusIcon() + "] " + myList.get(lastInteger).getTaskDesc());
                }

            }
            else if (userInput.startsWith("unmark")) {
                Matcher matcher = Pattern.compile("\\d+$").matcher(userInput);
                if (matcher.find()) {
                    int lastInteger = Integer.parseInt(matcher.group()) - 1;
                    myList.get(lastInteger).setUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + myList.get(lastInteger).getStatusIcon() + "] " + myList.get(lastInteger).getTaskDesc());

                }
            }
            else {
                Task task = new Task(userInput);
                myList.add(task);
                System.out.println("added: " + task.getTaskDesc());
            }
            userInput = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }



}
