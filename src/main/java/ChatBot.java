import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;



public class ChatBot {
    private List<Task> list;

    ChatBot() {
        this.list = new ArrayList<Task>();
    }

    public void processInput(String input) {
        List<String> tokens = Arrays.asList(input.split(" "));
        String command = tokens.get(0);
        List<String> args = new ArrayList<String>();
        if (tokens.size() > 1) {
            args = tokens.subList(1, tokens.size());
        }
        String output = "";
        switch (command) {
            case "list":
                output = this.listItems();
                break;
            case "mark":
                output = this.markAsDone(args.get(0));
                break;
            case "unmark":
                output = this.unmarkDone(args.get(0));
                break;
            default:
                output = this.addItem(input);
        }
        this.reply(output);
    }

    private String listItems() {
        if (this.list.isEmpty()) {
            return ("You don't have anything listed right now.");
        } else {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < this.list.size(); i++) {
                output.append(i+1);
                output.append(": " + list.get(i));
                output.append("\n");
            }
            return output.toString();
        }
    }

    private String addItem(String s) {
        this.list.add(new Task(s));
        return ("added: " + s);
    }

    private String markAsDone(String index) {
        int i = Integer.valueOf(index);
        Task task = this.list.get(i-1);
        try {
            task.markAsDone();
        } catch (Exception e) {
            return "You've already marked " + task.description + " as done!\n" +task.toString();
        }
        return ("Coolio. Marked " + task.description + " as done!\n" + task.toString());
    }

    private String unmarkDone(String index) {
        int i = Integer.valueOf(index);
        Task task = this.list.get(i-1);
        try {
            task.unmarkDone();
        } catch (Exception e) {
            return "The task " + task.description + " is already unmarked.\n" + task.toString();
        }
        return ("Okay. Unmarked " + task.description + " for you.\n" + task.toString());
    }

    public void reply(String s) {
        if (s.charAt(s.length() - 1) != '\n') {
            s += '\n';
        }
        String LINE_SEPARATOR = "----------------------------------------\n";
        String output = LINE_SEPARATOR + s + LINE_SEPARATOR;
        for (String line : output.split("\n")) {
            System.out.println(indent(line));
        }
    }

    private static String indent(String s) {
        return "\t" + s;
    }

    public void close() {
        this.reply("Alright, goodbye to you too!");
    }
}
