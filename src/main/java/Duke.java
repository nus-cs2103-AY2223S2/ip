import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        Chungus chungus = new Chungus(writer);

        while (true) {
            writer.append("chungus> ").flush();
            String input = sc.nextLine();
            boolean shouldExit = chungus.handleMessage(input);
            if (shouldExit)
                break;
        }

        sc.close();
    }
}

class Chungus {
    private Writer out;

    public Chungus(Writer _out) throws IOException {
        out = _out;

        this.info("Hello, I'm Chungus.");
        this.info("What can I do for you?");
    }

    public boolean handleMessage(String msg) throws IOException {
        if (msg.equals("bye")) {
            this.info("Bye!");
            return true;
        }
        this.info(msg);
        return false;
    }

    private void info(String msg) throws IOException {
        out.append("\u001b[36m").append(msg).append('\n').append("\u001b[0m").flush();
    }
}
