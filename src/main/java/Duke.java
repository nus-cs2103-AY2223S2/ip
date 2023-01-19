import java.util.Scanner;

public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Task[] ls = new Task[100];
        int lsCount = 0;
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                for (int i = 0; i < lsCount; i++) {
                    System.out.println((i + 1) + ". " + ls[i]);
                }
            }
            else {
                String firstWord = s.substring(0, s.indexOf(" "));
                String restOfString = s.substring(s.indexOf(" ") + 1);
                if (firstWord.equals("mark")) {
                    try {
                        int i = Integer.parseInt(restOfString);
                        ls[i-1].markUnmark(true);
                    } catch (NumberFormatException err) {
                    }
                }
                else if (firstWord.equals("unmark")) {
                    try {
                        int i = Integer.parseInt(restOfString);
                        ls[i-1].markUnmark(false);
                    } catch (NumberFormatException err) {
                    }
                } else if (firstWord.equals("todo")) {
                    ToDo t = ToDo.addToDo(restOfString);
                    ls[lsCount] = t;
                    lsCount += 1;
                    System.out.println("Now you have " + lsCount + " tasks in the list.");
                } else if (firstWord.equals("deadline")) {
                    Deadline t = Deadline.addDeadline(restOfString);
                    ls[lsCount] = t;
                    lsCount += 1;
                    System.out.println("Now you have " + lsCount + " tasks in the list.");
                } else if (firstWord.equals("event")) {
                    Event t = Event.addEvent(restOfString);
                    ls[lsCount] = t;
                    lsCount += 1;
                    System.out.println("Now you have " + lsCount + " tasks in the list.");
                } else {

                }
            }
            s = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
