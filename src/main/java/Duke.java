import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        TaskList taskList = new TaskList();
                
        Scanner sc = new Scanner(System.in);
        String line = "init";
        // System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        while (!line.equals("bye")) {
            if (!line.equals("init")) {
                Command command = Command.getCommand(line);
                switch (command) {
                case LIST:
                    displayMsg(taskList.outputList());
                    break;
                case MARK:  
                case UNMARK:
                    int listIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                    Task targetTask = taskList.get(listIndex);
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
                    Task removedTask = taskList.removeTask(line);
                    displayMsg("Noted. I've removed this task:\n" + indentString(removedTask.toString(), 1) + "\n" + taskList.countTasks());
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
                        taskList.add(newTask);
                        StringBuilder output2 = new StringBuilder();
                        output2.append("Got it. I've added this task:\n" + indentString(newTask.toString(), 1) + "\n" + taskList.countTasks());
                        displayMsg(output2.toString());
                    } catch (TaskInitError e) {
                        displayMsg("OOPS!!! " + e.getMessage());
                    }
                    break;
                case UNRECOGNIZED_CMD:
                    displayMsg("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
                }
                System.out.println("");
            }
            line = sc.nextLine();
        }
        sc.close();
        displayMsg("Bye. Hope to see you again soon!");
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
}
