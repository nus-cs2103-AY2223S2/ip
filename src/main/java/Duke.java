import java.awt.*;
import java.util.*;
import java.util.List;

public class Duke {

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
        String temp[] = message.split("/", 2);
        if (temp.length == 1) {
            throw new DukeException("Invalid format for Deadline.\nUsage: deadline <task> /by <date/time>\n");
        }
        String description = temp[0].trim();
        String[] byTemp= temp[1].split(" ", 2);
        if (byTemp.length == 1) {
            throw new DukeException("Invalid format for Deadline.\nUsage: deadline <task> /by <date/time>\n");
        }
        String by = byTemp[1].trim();
        resultArr[0] = description;
        resultArr[1] = by;
        return resultArr;
    }

    public String[] eventSplitter(String message) throws DukeException {
        String[] resultArr = new String[3];
        String temp[] = message.split("/", 3);
        if (temp.length < 3) {
            throw new DukeException("Invalid format for Event.\nUsage: <task> /from <date/time> /to <date/time>\n");
        }
        String description = temp[0].trim();
        String[] fromTemp = temp[1].split(" ", 2);
        if (fromTemp.length == 1) {
            throw new DukeException("Invalid format for Event.\nUsage: <task> /from <date/time> /to <date/time>\n");
        }
        String from = fromTemp[1].trim();
        String[] toTemp = temp[2].split(" ", 2);
        if (toTemp.length == 1) {
            throw new DukeException("Invalid format for Event.\nUsage: <task> /from <date/time> /to <date/time>\n");
        }
        String to = toTemp[1].trim();
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

        while (doLoop) {
            String line = input.nextLine();

            String arr[] = line.split(" ", 2);
            String keyword = arr[0];
            try {
                switch (keyword.toLowerCase()) {
                    case ("bye"): {
                        doLoop = false;
                        System.out.println(this.formatString("Bye. Hope to see you again soon!"));
                        break;
                    }
                    case ("list"): {
                        System.out.println(this.formatString("Here are the tasks in your list:\n" + this.formatList(userTasks)));
                        break;
                    }
                    case ("mark"): {
                        String i = arr[1];
                        int index = Integer.parseInt(i) - 1;
                        Task t = userTasks.get(index);
                        t.markAsDone();
                        System.out.println(this.formatString("Nice! I've marked this task as done:\n" + t));
                        break;
                    }
                    case ("unmark"): {
                        String i = arr[1];
                        int index = Integer.parseInt(i) - 1;
                        Task t = userTasks.get(index);
                        t.unmarkAsDone();
                        System.out.println(this.formatString("OK, I've marked this task as not done yet:\n" + t));
                        break;
                    }
                    case ("todo"): {
                        //Only keyword todo present, no description
                        if (arr.length == 1) {
                            throw new DukeException("Invalid format for ToDo.\nUsage: todo <task>\n");
                        }
                        String info = arr[1];
                        Task t = new ToDo(info);
                        userTasks.add(t);
                        String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                        System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                        break;
                    }
                    case ("deadline"): {
                        if (arr.length == 1) {
                            throw new DukeException("Invalid format for Deadline.\nUsage: deadline <task> /by <date/time>\n");
                        }
                        String info = arr[1];
                        String[] params = deadlineSplitter(info);
                        Task t = new Deadline(params[0], params[1]);
                        userTasks.add(t);
                        String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                        System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                        break;
                    }
                    case ("event"): {
                        if (arr.length == 1) {
                            throw new DukeException("Invalid format for Event.\nUsage: <task> /from <date/time> /to <date/time>\n");
                        }
                        String info = arr[1];
                        String[] params = eventSplitter(info);
                        Task t = new Event(params[0], params[1], params[2]);
                        userTasks.add(t);
                        String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                        System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                        break;
                    }
                    default: {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    }
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
