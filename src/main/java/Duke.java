import java.util.*;
import java.io.*;
public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String text = "";
        pw.println("    ____________________________________________________________");
        pw.println("    Hello! I'm Duke");
        pw.println("    What can I do for you?");
        pw.println("    ____________________________________________________________");
        pw.flush();
        while (true) {
            text = br.readLine();
            if (text.equals("bye")) {
                pw.println("    ____________________________________________________________");
                pw.println("    Bye. Hope to see you again soon!");
                pw.println("    ____________________________________________________________");
                break;
            }
            pw.println("    ____________________________________________________________");
            pw.printf("    %s\n", text);
            pw.println("    ____________________________________________________________");
            pw.flush();
        }
        br.close();
        pw.close();
    }
}
