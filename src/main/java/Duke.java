import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws IOException {

        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(System.in);
        String line = "init";

        taskList.loadFromFile();

        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        while (!line.equals("bye")) {
            if (!line.equals("init")) {
                CommandInput command = CommandInput.getCommandInput(line);
                switch (command) {
                case LIST:
                    new ListCmd(taskList).execute();;
                    break;
                case MARK:  
                    int listIndexMark = Integer.parseInt(line.split(" ")[1]) - 1;
                    new MarkCmd(taskList, listIndexMark).execute();
                    break;
                case UNMARK:
                    int listIndexUnmark = Integer.parseInt(line.split(" ")[1]) - 1;
                    new UnmarkCmd(taskList, listIndexUnmark).execute();;
                    break;
                case DELETE:
                    Task removedTask = taskList.removeTask(line);
                    Ui.displayMsg("Noted. I've removed this task:\n" + Ui.indentString(removedTask.toString(), 1) + "\n" + taskList.countTasks());
                    break;
                case EVENT:
                    new EventCmd(taskList, line).execute();
                    break;
                case DEADLINE:
                    new DeadlineCmd(taskList, line).execute();
                    break;
                case TODO:
                    new ToDoCmd(taskList, line).execute();
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
        taskList.saveToFile();

        Ui.displayMsg("Bye. Hope to see you again soon!");
    }
}
