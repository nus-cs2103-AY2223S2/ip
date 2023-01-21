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
        /**
         * Simply echoes commands entered by the user,
         * and exits when the user types "bye".
         */
        while (true) {
            inp = br.readLine();
            System.out.println(line);
            if (inp.equals("bye")) {
                System.out.println("Byeee! Hope to see you again! Signing off, Duke.");
                System.out.println(line);
                break;
            }
            System.out.println(inp);
            System.out.println(line);
        }




    }
}
