import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ChatBot {
    private enum Tasks { TODO, DEADLINE, EVENT }
    private static final Map<String, Tasks> inputToTask = new HashMap<String,Tasks>();
    private List<Task> list;
    static{
        inputToTask.put("todo", Tasks.TODO);
        inputToTask.put("deadline", Tasks.DEADLINE);
        inputToTask.put("event", Tasks.EVENT);
    }

    ChatBot() {
        this.list = new ArrayList<Task>();
    }

    public void processInput(String input) throws Exception {
        Map<String,String> argValues = InputProcessor.splitArgs(input);
        String command = argValues.get("Command");
        String output = "";
        switch (command) {
            case "list":
                output = this.listItems();
                break;
            case "mark":
                output = this.markAsDone(argValues.get("mark"));
                break;
            case "unmark":
                output = this.unmarkDone(argValues.get("unmark"));
                break;
            default:
                System.out.println(argValues);
                if (this.inputToTask.containsKey(command)) {
                    try {
                        output = addTask(inputToTask.get(command),argValues);
                    } catch (Exception e) {
                        throw e;
                    }
                } else {
                    output = "Hmm, I don't recognise this command. Try todo <task> to add task to the list.";
                }
        }
        this.reply(output);
    }

    private String listItems() {
        if (this.list.isEmpty()) {
            return ("You don't have anything listed right now.");
        } else {
            StringBuilder output = new StringBuilder("Eh this is what you've written down so far:\n");
            for (int i = 0; i < this.list.size(); i++) {
                output.append(i+1);
                output.append(": " + list.get(i));
                output.append("\n");
            }
            return output.toString();
        }
    }

    private String addTask(Tasks t, Map<String,String> args) throws Exception {
        Task item;
        String desc;
        switch (t) {
            case TODO:
                desc = args.get("todo");
                item = new ToDo(desc);
                break;
            case DEADLINE:
                desc = args.get("deadline");
                String by = args.get("by");
                item = new Deadline(desc, by);
                break;
            case EVENT:
                desc = args.get("event");
                String from = args.get("from");
                String to = args.get("to");
                item = new Event(desc, from, to);
                break;
            default:
                throw new Exception("Invalid task type");
        }
        this.list.add(item);
        return ("Item added!\n" + indent(item.toString()) +"\nYou now have " + this.list.size() +" tasks.");
    }

    private String markAsDone(String index) {
        int i;
        try {
            i = Integer.valueOf(index);
        } catch (NumberFormatException e) {
            return "Please specify the task by its index number.";
        }
        Task task = this.list.get(i-1);
        try {
            task.markAsDone();
        } catch (Exception e) {
            return "You've already marked " + task.description + " as done!\n" +task.toString();
        }
        return ("Coolio. Marked " + task.description + " as done!\n" + task.toString());
    }

    private String unmarkDone(String index) {
        int i;
        try {
            i = Integer.valueOf(index);
        } catch (NumberFormatException e) {
            return "Please specify the task by its index number.";
        }
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
