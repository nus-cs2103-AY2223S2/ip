import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(System.in);
        String line = "init";

        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        while (!line.equals("bye")) {
            if (!line.equals("init")) {
                CommandInput command = CommandInput.getCommandInput(line);
                switch (command) {
                case LIST:
                    Ui.listReply(taskList);
                    break;
                case MARK:  
                case UNMARK:
                    int listIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                    Task targetTask = taskList.get(listIndex);
                    String output; 
                    if (command == CommandInput.MARK){
                        targetTask.markDone();
                        output = "Nice! I've marked this task as done:";
                    } else {
                        targetTask.unmarkDone();
                        output = "Ok, I've marked this task as not done yet:";
                    }
                    Ui.displayMsg(output + "\n" + Ui.indentString(targetTask.toString(), 1));
                    break;
                case DELETE:
                    Task removedTask = taskList.removeTask(line);
                    Ui.displayMsg("Noted. I've removed this task:\n" + Ui.indentString(removedTask.toString(), 1) + "\n" + taskList.countTasks());
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
                        output2.append("Got it. I've added this task:\n" + Ui.indentString(newTask.toString(), 1) + "\n" + taskList.countTasks());
                        Ui.displayMsg(output2.toString());
                    } catch (TaskInitError e) {
                        Ui.displayMsg("OOPS!!! " + e.getMessage());
                    }
                    break;
                case UNRECOGNIZED_CMD:
                    Ui.displayMsg("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
                }
                System.out.println("");
            }
            line = sc.nextLine();
        }
        sc.close();
        Ui.displayMsg("Bye. Hope to see you again soon!");
    }
}
