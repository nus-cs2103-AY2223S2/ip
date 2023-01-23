import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class Duke {

    public enum Keyword {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");
        private String word;
        Keyword(String word) {
            this.word = word;
        }
        @Override
        public String toString() {
            return this.word;
        }
    }
    public static final String dashes = "--------------------------------------";

    public String formatString(String input) {
        return dashes + "\n" + input + "\n" + dashes + "\n";
    }

    public void echo() {
        System.out.println(this.formatString("Hello! I'm Gerty\nWhat can I do for you?"));
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                System.out.println(this.formatString("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(this.formatString(line));
        }
    }

    public String formatList(List userTasks) {
        String formattedList = "";
        for (Object t : userTasks) {
            int pos = userTasks.indexOf(t) + 1;
            formattedList += pos + ". " + t + "\n";
        }
        return formattedList.trim();
    }

    public String[] deadlineSplitter(String message) throws DukeException  {
        String[] resultArr = new String[2];
        Pattern p = Pattern.compile("/by");
        String temp[] = p.split(message);
        if (temp.length < 2) {
            throw new DukeException("Invalid format for Deadline.\nUsage: deadline <task> /by <date/time>\n");
        }
        String description = temp[0].trim();
        String by = temp[1].trim();
        resultArr[0] = description;
        resultArr[1] = by;
        return resultArr;
    }

    public String[] eventSplitter(String message) throws DukeException {
        String[] resultArr = new String[3];
        Pattern p1 = Pattern.compile("/from");
        String temp1[] = p1.split(message);
        if (temp1.length < 2) {
            throw new DukeException("Invalid format for Event.\nUsage: <task> /from <date/time> /to <date/time>\n");
        }
        String description = temp1[0].trim();
        Pattern p2 = Pattern.compile("/to");
        String[] temp2 = p2.split(temp1[1]);
        if (temp2.length < 2) {
            throw new DukeException("Invalid format for Event.\nUsage: <task> /from <date/time> /to <date/time>\n");
        }
        String from = temp2[0].trim();
        String to = temp2[1].trim();
        resultArr[0] = description;
        resultArr[1] = from;
        resultArr[2] = to;
        return resultArr;
    }

    public void addMarkList(){
        List<Task> userTasks = new ArrayList<>();

        System.out.println(this.formatString("Hello! I'm Gerty\nWhat can I do for you?"));

        boolean doLoop = true;

        Scanner input = new Scanner(System.in);

        while (true) {
            String line = input.nextLine();
            String arr[] = line.split(" ", 2);
            String keyword = arr[0].toLowerCase();
            try {
                if (keyword.equals(Keyword.BYE.toString())) {
                    System.out.println(this.formatString("Bye. Hope to see you again soon!"));
                    break;
                } else if (keyword.equals(Keyword.LIST.toString())) {
                    System.out.println(this.formatString("Here are the tasks in your list:\n" + this.formatList(userTasks)));
                } else if (keyword.equals(Keyword.MARK.toString())) {
                    String i = arr[1];
                    int index = Integer.parseInt(i) - 1;
                    Task t = userTasks.get(index);
                    t.markAsDone();
                    System.out.println(this.formatString("Nice! I've marked this task as done:\n" + t));
                } else if (keyword.equals(Keyword.UNMARK.toString())) {
                    String i = arr[1];
                    int index = Integer.parseInt(i) - 1;
                    Task t = userTasks.get(index);
                    t.unmarkAsDone();
                    System.out.println(this.formatString("OK, I've marked this task as not done yet:\n" + t));
                } else if (keyword.equals(Keyword.DELETE.toString())) {
                    String i = arr[1];
                    int index = Integer.parseInt(i) - 1;
                    Task t = userTasks.remove(index);
                    String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                    System.out.println(this.formatString("Noted. I've removed this task:\n" + t + "\n" + numTasks));
                } else if (keyword.equals(Keyword.TODO.toString())) {
                    //Only keyword todo present, no description
                    if (arr.length == 1) {
                        throw new DukeException("Invalid format for ToDo.\nUsage: todo <task>\n");
                    }
                    String info = arr[1];
                    Task t = new ToDo(info);
                    userTasks.add(t);
                    String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                    System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                } else if (keyword.equals(Keyword.DEADLINE.toString())) {
                    if (arr.length == 1) {
                        throw new DukeException("Invalid format for Deadline.\nUsage: deadline <task> /by <date/time>\n");
                    }
                    String info = arr[1];
                    String[] params = deadlineSplitter(info);
                    Task t = new Deadline(params[0], params[1]);
                    userTasks.add(t);
                    String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                    System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                } else if (keyword.equals(Keyword.EVENT.toString())) {
                    if (arr.length == 1) {
                        throw new DukeException("Invalid format for Event.\nUsage: <task> /from <date/time> /to <date/time>\n");
                    }
                    String info = arr[1];
                    String[] params = eventSplitter(info);
                    Task t = new Event(params[0], params[1], params[2]);
                    userTasks.add(t);
                    String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                    System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke gerty = new Duke();
        gerty.addMarkList();
    }
}
