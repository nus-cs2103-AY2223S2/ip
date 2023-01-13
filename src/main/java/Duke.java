import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<Task> tList;

    Duke() {
        tList = new ArrayList<Task>();
    }

    private String greeting(){
        return "Hello from\n"
            +  " ____       _          \n"
            +  "|  _ \\ _  _| | ____ _   \n"
            +  "| | | | | |  | |/ / _ \\ \n"
            +  "| |_| | |_|  |   <  __/  \n"
            +  "|____/ \\__,_|_|\\_\\___|\n"
            +  "What can I do for you?\n";
    }

    private String bye() {
        return "Bye. Hope to see you again soon!";
    }

    private String listTask() {
        if(tList.isEmpty()) {
            return "No task found, use:\n" 
                + "\t<todo     [title]> \n"
                + "\t<deadline [title] \\by   [date]> \n"
                + "\t<event    [title] \\from [date] \\to [date]> \n"
                + "commands to create task.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tList.size(); i++) {
            sb.append(String.format("\t%d. %s\n", i + 1, tList.get(i)));
        }
        return sb.toString();
    }

    private String markTask(int idx) {
        if(idx < 1 || idx > tList.size()) {
            return "Invalid choice";
        }

        Task tk = tList.get(idx - 1);
        tk.markAsDone();

        return "Nice! I've marked this task as done:\n"
            + "\t" + tk;
    }

    private String unMarkTask(int idx) {
        if(idx < 1 || idx > tList.size()) {
            return "Invalid choice";
        }

        Task tk = tList.get(idx - 1);
        tk.unmarkDone();

        return "OK, I've marked this task as not done yet:\n"
            + "\t" + tk;
    }

    private String addTask(Task tk) {
        tList.add(tk);
        return "Got it. I've added this task:\n"
            + "\t" + tk + "\n"
            + "Now you have " + tList.size() + " tasks in the list.";
    }

    private String addTodo(String title) {
        return addTask(new Todo(title));
    }

    private String addEvent(String title, String from, String to) {
        return addTask(new Event(title, from, to));
    }

    private String addDeadline(String title, String by) {
        return addTask(new Deadline(title, by));
    }

    private void say(String text) {
        System.out.println("_".repeat(40));
        System.out.println(text);
        System.out.println("‾".repeat(40));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.greeting());

        Scanner sc = new Scanner(System.in);
        convo: while (true) {
            String[] input = sc.nextLine().toLowerCase().split(" ", 2);
            String action = input[0];
            String[] options;

            switch (action) {
                case "bye":
                    duke.say(duke.bye());
                    break convo;
                case "list":
                    duke.say(duke.listTask());
                    break;
                case "mark":
                    duke.say(duke.markTask(Integer.parseInt(input[1])));
                    break;
                case "unmark":
                    duke.say(duke.unMarkTask(Integer.parseInt(input[1])));
                    break;
                case "todo":
                    duke.say(duke.addTodo(input[1]));
                    break;
                case "event":
                    options = input[1].split(" /[a-z]*[^ ] ");
                    duke.say(duke.addEvent(options[0], options[1], options[2]));
                    break;
                case "deadline":
                    options = input[1].split(" /[a-z]*[^ ] ");
                    duke.say(duke.addDeadline(options[0], options[1]));
                    break;
                default:
                    duke.say("Invalid command.");
                    break;
            }
        }

        sc.close();
    }

}
