import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> dukeList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
                
        Scanner sc = new Scanner(System.in);
        String line = "init";
        // System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        while (!line.equals("bye")) {
            if (!line.equals("init")) {
                Command command = Command.getCommand(line);
                switch (command) {
                case LIST:
                    displayMsg(outputList());
                    break;
                case MARK:  
                case UNMARK:
                    int listIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                    Task targetTask = dukeList.get(listIndex);
                    String output; 
                    if (command == Command.MARK){
                        targetTask.markDone();
                        output = "Nice! I've marked this task as done:";
                    } else {
                        targetTask.unmarkDone();
                        output = "Ok, I've marked this task as not done yet:";
                    }
                    displayMsg(output + "\n" + indentString(targetTask.toString(), 1));
                    break;
                case DELETE:
                    Task removedTask = removeTask(line);
                    displayMsg("Noted. I've removed this task:\n" + indentString(removedTask.toString(), 1) + "\n" + countTasks());
                    break;

                case EVENT:
                case DEADLINE:
                case TODO:
                    Task newTask;
                    try {
                        if (line.startsWith("event")) {
                            newTask = Event.create(line);
                        } else if (line.startsWith("deadline")) {
                            newTask = Deadline.create(line);
                        } else {
                            newTask = ToDo.create(line);
                        } 
                        dukeList.add(newTask);
                        StringBuilder output2 = new StringBuilder();
                        output2.append("Got it. I've added this task:\n" + indentString(newTask.toString(), 1) + "\n" + countTasks());
                        displayMsg(output2.toString());
                    } catch (TaskInitError e) {
                        displayMsg("OOPS!!! " + e.getMessage());
                    }
                    break;
                case UNRECOGNIZED_CMD:
                    displayMsg("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
                }
            }
            line = sc.nextLine();
        }
        sc.close();
        displayMsg("Bye. Hope to see you again soon!");
    }

    public static String outputList() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        for (int index = 0; index < dukeList.size(); index++) {
            result.append("\n" + (index + 1) + ". " + dukeList.get(index).toString());
        }
        return result.toString();
    }

    public static void displayMsg(String msg) {
        System.out.println(indentString(wrapMessageBorder(msg), 1));
    }

    public static String wrapMessageBorder(String msg) {
        String border = "____________________________________________________________";
        return border + "\n" + msg + "\n" + border;
    }

    public static String indentString(String msg, int indendationLevel) {
        String indent = "  " .repeat(indendationLevel);
        String[] lines = msg.split("\n");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            result.append(indent + lines[i] + (i + 1 < lines.length ? "\n" : ""));
        }
        return result.toString();
    }

    public static Task removeTask(String command) {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        return dukeList.remove(index);
    }

    public static String countTasks() {
        return "Now you have " + dukeList.size() + " task" + (dukeList.size() == 1 ? "" : "s") + " in the list.";
    }
}
