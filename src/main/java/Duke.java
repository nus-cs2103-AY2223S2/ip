import java.io.*;
import java.util.*;
public class Duke {
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
        Task[] taskstorage = new Task[100];
        /**
         * Simply echoes commands entered by the user,
         * and exits when the user types "bye".
         */
        int ind = 0;
        while (true) {
            inp = br.readLine();
            String[] input = inp.split(" ");
            System.out.println(line);
            switch(input[0]) {
                case "list":
                    System.out.println("Tasks:");
                    for (int i = 0; i < ind; i++) {
                        System.out.println(i + 1 + ". " + taskstorage[i]);
                    }
                    break;

                case "bye":
                    System.out.println("Byeee! Hope to see you again! Signing off, Duke.");
                    break;

                case "mark":
                    //System.out.println("Nice! I've marked this task as done:");
                    int taskNo = Integer.parseInt(input[1]);
                    taskNo--;
                    taskstorage[taskNo].markasDone();
                    break;

                case "unmark":
                    int taskNoUnmark = Integer.parseInt(input[1]);
                    taskNoUnmark--;
                    taskstorage[taskNoUnmark].markasUnDone();
                    break;
                default:
                    System.out.println("added: " + inp);
                    Task t = new Task(inp);
                    //System.out.println(t);
                    taskstorage[ind] = t;
                    ind++;
            }
            System.out.println(line);
            if (inp.equals("bye")) {
                break;
            }

        }




    }
}
