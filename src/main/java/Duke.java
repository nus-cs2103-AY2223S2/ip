import java.io.*;
import java.util.*;
public class Duke {

    private static Task[] taskstorage = new Task[101];
    private static int ind = 1;
    public static void addTask(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        taskstorage[ind] = t;
        System.out.println("Now you have " + ind + " task(s) in the list.");
        ind++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringBuilder sb = new StringBuilder();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "-------------------------------";
        System.out.println(line);
        System.out.println("Hiii Im\n" + logo);
        System.out.println("What can I do for you hmm?");
        System.out.println(line);
        String inp;

        /**
         * Simply echoes commands entered by the user,
         * and exits when the user types "bye".
         */
        //int ind = 1;
        while (true) {
            inp = br.readLine();
            String[] input = inp.split(" ");
            System.out.println(line);
            switch(input[0]) {
                case "list":
                    System.out.println("Tasks:");
                    for (int i = 1; i < ind; i++) {
                        System.out.print(i + ".");
                        System.out.println(taskstorage[i]);
                    }
                    break;

                case "bye":
                    System.out.println("Byeee! Hope to see you again! Signing off, Duke.");
                    break;

                case "mark":
                    //System.out.println("Nice! I've marked this task as done:");
                    int taskNo = Integer.parseInt(input[1]);
                    taskstorage[taskNo].markasDone();
                    break;

                case "unmark":
                    int taskNoUnmark = Integer.parseInt(input[1]);
                    taskstorage[taskNoUnmark].markasUnDone();
                    break;

                case "todo":
                    String todoTask = inp.substring(5);
                    Task todo = new Todo(todoTask);
                    addTask(todo);
                    break;

                case "deadline":
                    String deadlineStr = inp.substring(9);
                    String[] inputDeadline = deadlineStr.split("/");
                    String deadLineTaskStr = inputDeadline[0];
                    String end = inputDeadline[1].substring(3);
                    Task deadLineTask = new Deadline(deadLineTaskStr, end);
                    addTask(deadLineTask);
                    break;

                case "event":
                    String eventStr = inp.substring(6);
                    String[] eventStrsplit = eventStr.split("/");
                    String eventTaskStr = eventStrsplit[0];
                    String eventBegin = eventStrsplit[1].substring(5);
                    String eventEnd = eventStrsplit[2].substring(3);
                    Task eventTask = new Event(eventTaskStr, eventBegin, eventEnd);
                    addTask(eventTask);
                    break;

                default:
                    System.out.println("added: " + inp);
                    Task t = new Task(inp);
                    addTask(t);
            }
            System.out.println(line);
            if (inp.equals("bye")) {
                break;
            }

        }




    }
}
