import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String text;
        String horiLine = "___________________________";
        String terminate = "bye";
        Scanner s = new Scanner(System.in);
        String[] taskList = new String[100];
        Boolean[] markList = new Boolean[100];
        Arrays.fill(markList, false);

        int counter = 0;
        boolean flag = true;
        String notDone = "[ ]";
        String done = "[X]";

        System.out.println(horiLine);
        System.out.println("Hello! I'm Duke \nWhat can I do for you");
        System.out.println(horiLine);

        while (flag) {
            text = s.nextLine();
            if (text.equals(terminate)) {
                System.out.println(horiLine);
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println(horiLine);
                flag = false;
            } else if (text.equals("list")) {
                System.out.println(horiLine);
                System.out.println("Here are the tasks in your list:");
                for (int i=1; i<counter + 1; i++) {
                    System.out.format("%d. %s %s \n", i, markList[i - 1] ? done : notDone, taskList[i - 1]);
                }
                System.out.println(horiLine);
            } else if (text.split(" ", 2)[0].equals("mark")) {
                System.out.println(horiLine);
                System.out.println("Nice! I've marked this task as done:");
                int taskNumber = Integer.parseInt(text.split(" ", 2)[1]);
                markList[taskNumber - 1] = true;
                System.out.format(" %s %s \n", markList[taskNumber - 1] ? done : notDone, taskList[taskNumber - 1]);
                System.out.println(horiLine);
            } else if (text.split(" ", 2)[0].equals("unmark")) {
                System.out.println(horiLine);
                System.out.println("OK, I've marked this task as not done yet:");
                int taskNumber = Integer.parseInt(text.split(" ", 2)[1]);
                markList[taskNumber - 1] = false;
                System.out.format(" %s %s \n", markList[taskNumber - 1] ? done : notDone, taskList[taskNumber - 1]);
                System.out.println(horiLine);
            } else {
                System.out.println(horiLine);
                System.out.println("added: " + text);
                System.out.println(horiLine);
                taskList[counter] = text;
                counter++;
            }
        }

    }
}
