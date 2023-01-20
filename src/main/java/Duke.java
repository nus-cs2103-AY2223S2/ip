import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String separator = "____________________________________________________________";
        HashMap<Integer, Task> todoList = new HashMap<>(); // <number of item, task name>
        int numOfTasks = 0;

        String openingMessage = separator + "\nHello! I'm Duke\n" + "What can I do for you?\n" + separator;
        System.out.println(openingMessage);

        while(s.hasNext()) {
            String input = s.nextLine();
            String[] split = input.split(" ");

            if(split[0].equals("bye")) {
                String closingMessage = separator + "\nBye. Hope to see you again soon!\n" + separator;
                System.out.println(closingMessage);
                break;

            } else if(split[0].equals("list")) {
                System.out.println(separator + "\nHere are the tasks in your list:");
                for (int i = 1; i <= numOfTasks; i++) {
                    Task thisTask = todoList.get(i);

                    if(thisTask.isDone()) {
                        System.out.println(i + ".[X] " + thisTask.getTask());
                    } else {
                        System.out.println(i + ".[ ] " + thisTask.getTask());
                    }
                }
                System.out.println(separator);

            } else if(split[0].equals("mark")) {
                Task thisTask = todoList.get(Integer.parseInt(split[1]));
                thisTask.markDone(); // have to see if this updates in arraylist or not
                System.out.println(separator + "\nNice! I've marked this task as done:" + "\n[X] " + thisTask.getTask() + separator);

            } else if(split[0].equals("unmark")) {
                Task thisTask = todoList.get(Integer.parseInt(split[1]));
                thisTask.unmarkDone(); // have to see if this updates in arraylist or not
                System.out.println(separator + "\nOK, I've marked this task as not done yet:" + "\n[ ] " + thisTask.getTask() + separator);

            } else {
                numOfTasks += 1;
                todoList.put(numOfTasks, new Task(input));
                String message = separator + "\nadded: " + input + "\n" + separator;
                System.out.println(message);
            }
        }
        s.close();
    }
}

class Task {
    private String task;
    private boolean status; // false meaning not done yet
    public Task(String task) {
        this.task = task;
        this.status = false;
    }

    public void markDone() {
        this.status = true;
    }

    public void unmarkDone() {
        this.status = false;
    }

    public String getTask() {
        return this.task;
    }

    public boolean isDone() {
        return this.status;
    }
}