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
        String[] taskstorage = new String[100];
        /**
         * Simply echoes commands entered by the user,
         * and exits when the user types "bye".
         */
        int ind = 0;
        while (true) {
            inp = br.readLine();
            System.out.println(line);
            switch(inp) {
                case "list":
                    for (int i = 0; i < ind; i++) {
                        System.out.println(i + 1 + ". " + taskstorage[i]);
                    }
                    break;

                case "bye":
                    System.out.println("Byeee! Hope to see you again! Signing off, Duke.");
                    System.out.println(line);
                    break;

                default:
                    System.out.println("added: " + inp);
                    taskstorage[ind] = inp;
                    ind++;
            }
            if (inp.equals("bye")) {
                break;
            }
            //System.out.println(inp);
            System.out.println(line);
        }




    }
}
