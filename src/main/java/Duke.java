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
        ArrayList<String> storage = new ArrayList<String>();
        while (true) {  // Echoing
            text = br.readLine();
            if (text.equalsIgnoreCase("bye")) { // Termination of program
                sb.append("    ____________________________________________________________\n")
                                .append("    Bye. Hope to see you again soon!\n")
                                        .append("    ____________________________________________________________\n");
                pw.println(sb.toString());
                pw.flush();
                sb.setLength(0);
                break;
            }
            if (text.equalsIgnoreCase("list")) {    // Displays the texts that are stored
                sb.append("    ____________________________________________________________\n");
                for (int i = 0; i < storage.size(); i++) {
                    sb.append("    ").append(i + 1).append(". ").append(storage.get(i)).append("\n");
                }
                sb.append("    ____________________________________________________________\n");
                pw.println(sb.toString());
                pw.flush();
                sb.setLength(0);
                continue;
            }
            storage.add(text);
            sb.append("    ____________________________________________________________\n")
                            .append("    added: ").append(text).append("\n")
                                    .append("    ____________________________________________________________\n");
            pw.println(sb.toString());
            pw.flush();
            sb.setLength(0);
        }
        br.close();
        pw.close();
    }
}
