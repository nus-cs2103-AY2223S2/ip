import task.Task;
import task.TaskList;

public class Command {

    public String str;

    public Command(String str) {
        this.str = str;
    }

    public int countSlash() {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '/') {
                count ++;
            }
        }
        return count;
    }

    public void markCommand(TaskList taskList, Task t) throws DukeException {
        if (t.isMarked()) {
            throw new DukeException("This task has already been marked as done.");
        } else {
            t.mark();
            System.out.println("Great job on completing this task! I've marked it as done:");
            System.out.println(t);
        }
        if (taskList.isAllCompleted()) {
            System.out.println("Yay! You have completed all your tasks :-)");
        }
    }

    public void unmarkCommand(TaskList taskList, Task t) throws DukeException {
        if (!t.isMarked()) {
            throw new DukeException("Oops! This task has not been marked as done before.");
        } else {
            t.unMark();
            System.out.println("Alright, I've marked this task as not done yet:");
            System.out.println(t);
        }
    }

    public void listCommand(TaskList taskList) {
        if (taskList.getNumTasks() == 0) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskList);
        }
    }

    public void byeCommand() {
        System.out.println("Bye. Hope to see you again soon! :-p");
    }

    public void todoCommand(TaskList tasklist, String desc) {
        System.out.println("Got it, I've added this task:");
        tasklist.addTodo(desc);
    }
    public void deadlineCommand(TaskList taskList, String desc, String date) {
        System.out.println("Got it, I've added this task:");
        taskList.addDeadline(date, desc);
    }

    public void eventCommand(TaskList taskList, String start, String end, String desc) {
        System.out.println("Got it, I've added this task:");
        taskList.addEvent(start, end, desc);
    }

    public void nextCommand() {
        System.out.println("What else can I do for you?");
    }

}
