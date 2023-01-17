import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void reply(String s) {
        String linebreak = "_________________________________________________________";
        System.out.println(linebreak);
        System.out.println(s);
        System.out.println(linebreak);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String currentInput = "";
        ArrayList<Task> taskList = new ArrayList<>();

        //Introduction
        reply("Hello! I'm Duke\n What can I do for you?");

        currentInput = sc.nextLine();
        while (!currentInput.equalsIgnoreCase("bye")) {
            StringBuilder response = new StringBuilder();
            if (currentInput.equalsIgnoreCase("list")) {
                if (taskList.size() == 0) {
                    response.append("List empty, add tasks!");
                } else {
                    for (int i = 0; i < taskList.size(); i++) {
                        Task curTask = taskList.get(i);
                        response.append((i+ 1)).append(". [").append(curTask.getStatusIcon()).append("] ").append(curTask.getDescription());
                        if (i < taskList.size() - 1) {
                            response.append("\n");
                        }
                    }
                }
            } else if (currentInput.matches("mark \\d+") || currentInput.matches("unmark \\d+")) {
                boolean mark = currentInput.matches("mark \\d+");
                int index = Integer.parseInt(mark ? currentInput.substring(5) : currentInput.substring(7)) - 1;
                if (index >= taskList.size() || index < 0) {
                    response.append("index out of bounds");
                } else {
                    Task curTask = taskList.get(index);
                    curTask.setCompleted(mark);
                    if (mark) {
                        response.append("Nice! I've marked this task as done:\n");
                    } else {
                        response.append("OK, I've marked this task as not done yet:\n");
                    }
                    response.append("  [").append(curTask.getStatusIcon()).append("] ").append(curTask.getDescription());
                }
            } else {
                taskList.add(new Task(currentInput));

                response.append("Added: ").append(currentInput);
            }
            reply(response.toString());
            currentInput = sc.nextLine();
        }
        //Signing off
        reply("Bye. Hope to see you again!");
    }
}
