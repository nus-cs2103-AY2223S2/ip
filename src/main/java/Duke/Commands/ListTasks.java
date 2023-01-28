package Duke.Commands;

import Duke.Commands.Tasks.Task;

import java.util.ArrayList;

public class ListTasks extends Command {
    public ListTasks(String message) {
        super(message);
    }

    private static String printList(ArrayList<Task> toDoList) {
        String res = "";
        res += "here are your tasks\n";
        for (int i = 0; i < toDoList.size(); i++) {
            res += String.format("%d.%s\n", i + 1, toDoList.get(i));
        }
        return res;
    }

    @Override
    public void execute(ArrayList<Task> toDoList) {
        System.out.println(printList(toDoList));
    }
}
