import java.util.List;
import java.util.ArrayList;



public class ChatBot {
    private List<String> list;

    ChatBot() {
        this.list = new ArrayList<String>();
    }

    public void processInput(String input) {
        String output = "";
        switch (input) {
            case "list":
                output = this.listItems();
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
        this.list.add(s);
        return ("added: " + s);
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
