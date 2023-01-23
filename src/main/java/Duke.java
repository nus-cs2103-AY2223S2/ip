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
//        ArrayList<String> itemList = new ArrayList<String>();
//        ArrayList<String> doneList = new ArrayList<String>();
        ArrayList<Task> taskList = new ArrayList<Task>();

        while(flag) {
            userInput = keyboard.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("--------------------------------");
                System.out.println("Bye. Have a nice Day~");
                System.out.println("--------------------------------");
                flag = false;
            } else if (userInput.equals("list")) {
                System.out.println("--------------------------------");
                System.out.println("Here are the current tasks:");

                for (int i = 0; i < taskList.size(); i ++) {
                    System.out.print((i+1) + ".");
                    System.out.println(taskList.get(i).getStatusIcon());
                }
                System.out.println("--------------------------------");

            } else if (userInput.length() >= 4 && userInput.substring(0,4).equals("mark")) {
                System.out.println("--------------------------------");

                int len = userInput.length();
                int itemID = Integer.valueOf(userInput.substring(5, len)) - 1;
                if ((itemID + 1) > taskList.size()) {
                    System.out.println("I cannot find task " + (itemID + 1) + " as it exceeds the total tasks number");
                } else {
                    System.out.println("Nice! Great job for completing this task:");
                    taskList.get(itemID).setDone();
                    System.out.println((taskList.get(itemID).getStatusIcon()));
                }
                System.out.println("--------------------------------");

            } else if (userInput.length() >= 6 && userInput.substring(0,6).equals("unmark")) {
                System.out.println("--------------------------------");
                int len = userInput.length();
                int itemID = Integer.valueOf(userInput.substring(7, len)) - 1;
                if ((itemID + 1) > taskList.size()) {
                    System.out.println("I cannot find task " + (itemID + 1) + " as it exceeds the total tasks number");
                } else {
                    System.out.println("This item is marked as not done yet");
                    taskList.get(itemID).setNotDone();
                    System.out.println((taskList.get(itemID).getStatusIcon()));
                }
                System.out.println("--------------------------------");

            }else {
                System.out.println("--------------------------------");
                Task t = new Task(userInput);
                taskList.add(t);
                System.out.println("added: " + t.description);
                System.out.println("--------------------------------");

            }
        }
    }
}