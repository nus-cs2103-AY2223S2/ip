package classes;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Ui {

    private PrintWriter pw;
    private StringBuilder sb;
    private BufferedReader br;

    public Ui() {
        this.pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        this.sb = new StringBuilder();
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void showWelcome() {
        sb.append("Hello from\n")
                .append(" ____        _        \n")
                .append("|  _ \\ _   _| | _____ \n")
                .append("| | | | | | | |/ / _ \\\n")
                .append("| |_| | |_| |   <  __/\n")
                .append("|____/ \\__,_|_|\\_\\___|\n")
                .append("    ____________________________________________________________\n")
                .append("    Hello! I'm Duke.\n")
                .append("    What can I do for you?\n")
                .append("    ____________________________________________________________\n");
        pw.println(sb.toString());
        pw.flush(); // Flush the message out and print to user
        sb.setLength(0);    // Clear string stored in StringBuilder
    }

    public void showFarewellMessage() throws IOException {
        sb.append("    ____________________________________________________________\n")
                .append("    Bye. Hope to see you again soon!\n")
                .append("    ____________________________________________________________\n");
        pw.println(sb.toString());
        pw.flush();
        sb.setLength(0);
        pw.close();
        br.close();
    }

    public void printCommand(String message) {
        pw.println(message);
        pw.flush();
        sb.setLength(0);
    }

    public void showLoadingError(Exception e) {
        pw.println(e);
    }

    public String readCommand() throws IOException {
        return br.readLine();
    }
}
