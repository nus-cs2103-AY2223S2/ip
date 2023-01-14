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
            writer.append('\n').flush();
            if (shouldExit)
                break;
        }

        sc.close();
    }
}

class Chungus {
    private Writer out;
    private ArrayList<String> tasks;

    public Chungus(Writer _out) throws IOException {
        out = _out;
        tasks = new ArrayList<>();

        this.info("Hello, I'm Chungus.");
        this.info("What can I do for you?");
    }

    public boolean handleMessage(String msg) throws IOException {
        if (msg.equals("bye")) {
            this.info("Bye!");
            return true;
        }
        if (msg.equals("list")) {
            for (int i = 1; i <= this.tasks.size(); i++) {
                this.info().append(String.valueOf(i)).append(". ").append(this.tasks.get(i - 1)).append('\n');
            }
            this.flush();
            return false;
        }

        this.tasks.add(msg);
        this.info().append("added: ").append(msg).append('\n');
        this.flush();

        return false;
    }

    private Writer info() throws IOException {
        out.append("\u001b[36m");
        return out;
    }

    private void info(String msg) throws IOException {
        out.append("\u001b[36m").append(msg).append('\n').append("\u001b[0m").flush();
    }

    private void flush() throws IOException {
        out.append("\u001b[0m").flush();
    }
}
