import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        String userInput;
        boolean flag = true;
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (flag) {
            userInput = keyboard.next();
            System.out.println("--------------------------------");
            if (userInput.equals("bye")) {
                //System.out.println("--------------------------------");
                System.out.println("Bye. Have a nice Day~");
                //System.out.println("--------------------------------");
                flag = false;
            } else if (userInput.equals("list")) {
                //System.out.println("--------------------------------");
                System.out.println("Here are the current tasks:");

                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print((i + 1) + ".");
                    System.out.println(taskList.get(i).toString());
                }
                //System.out.println("--------------------------------");

            } else if (userInput.equals("todo")) {
                System.out.println("This task had been added!");
                Todo t = new Todo(keyboard.nextLine());
                System.out.println("  " + t.toString());
                taskList.add(t);
                System.out.println("Now you have " + taskList.size() + " tasks in the list");

            } else if (userInput.equals("event")) {
                System.out.println("This event had been added! Hope you will enjoy it :D");
                userInput = keyboard.nextLine();
                int first = userInput.indexOf('/');
                int last = userInput.lastIndexOf('/');
                String name = userInput.substring(0, first);
                String from = userInput.substring(first + 1, last);
                String to = userInput.substring(last + 1, userInput.length());

                Event t = new Event(name, from, to);
                System.out.println("  " + t.toString());

                taskList.add(t);
                System.out.println("Now you have " + taskList.size() + " tasks in the list");

            } else if (userInput.equals("deadline")) {
                System.out.println("This deadline had been added! Try to finish it early 0v0");
                userInput = keyboard.nextLine();
                int dateID = userInput.indexOf('/');
                String name = userInput.substring(0, dateID);
                String date = userInput.substring(dateID+1, userInput.length());
                Deadline t = new Deadline(name, date);
                System.out.println("  " + t.toString());

                taskList.add(t);
                System.out.println("Now you have " + taskList.size() + " tasks in the list");

            } else if (userInput.equals("mark")) {
                //System.out.println("--------------------------------");
                int itemID = Integer.valueOf(keyboard.next());
                if ((itemID) > taskList.size()) {
                    System.out.println("I cannot find task " + (itemID) + " as it exceeds the total tasks number");
                } else {
                    System.out.println("Nice! Great job for completing this task:");
                    taskList.get(itemID - 1).setDone();
                    System.out.println((taskList.get(itemID - 1).toString()));
                }
                // System.out.println("--------------------------------");

            } else if (userInput.equals("unmark")) {
                //System.out.println("--------------------------------");
                int itemID = Integer.valueOf(keyboard.next()) - 1;
                if ((itemID + 1) > taskList.size()) {
                    System.out.println("I cannot find task " + (itemID + 1) + " as it exceeds the total tasks number");
                } else {
                    System.out.println("This item is marked as not done yet");
                    taskList.get(itemID).setNotDone();
                    System.out.println((taskList.get(itemID).toString()));
                }
                //System.out.println("--------------------------------");

            } else {
                //System.out.println("--------------------------------");
                System.out.println("Hiiii it's great to talk with you :D");
                System.out.println("May I know what type of task this is?");
                // System.out.println("--------------------------------");
            }
            System.out.println("--------------------------------");
        }
    }
}