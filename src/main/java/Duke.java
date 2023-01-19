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
        StringBuilder sb = new StringBuilder();
        String text = "";
        sb.append("    ____________________________________________________________\n")
                .append("    Hello! I'm Duke\n")
                .append("    What can I do for you?\n")
                .append("    ____________________________________________________________\n");
        pw.println(sb.toString());  // Welcome Message bye Duke
        pw.flush();
        sb.setLength(0);

        while (true) {  // Echoing
            text = br.readLine();
            if (text.equalsIgnoreCase("bye")) { // Regardless of Uppercase & Lowercase "bye"
                sb.append("    ____________________________________________________________\n")
                                .append("    Bye. Hope to see you again soon!\n")
                                        .append("    ____________________________________________________________\n");
                pw.println(sb.toString());
                pw.flush();
                sb.setLength(0);
                break;
            }
            sb.append("    ____________________________________________________________\n")
                            .append("    ").append(text).append("\n")
                                    .append("    ____________________________________________________________\n");
            pw.println(sb.toString());
            pw.flush();
            sb.setLength(0);
        }
        br.close();
        pw.close();
    }
}
