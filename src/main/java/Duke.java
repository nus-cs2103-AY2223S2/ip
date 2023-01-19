import java.util.Scanner;
public class Duke {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int idx = 0;
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        while(true) {
            String input = scanner.nextLine();
            String[] token = input.split(" ", 2);
            if (token[0].equals("bye")) { // Exit
                break;
            } else if (token[0].equals("list")) { // List all tasks
                System.out.println("\tHere are the tasks in your list:");
                int i = 1;
                for (Task task: tasks) {
                    if (task == null) {
                        break;
                    }
                    System.out.println(String.format("\t%d.%s", i, task));
                    i++;
                }
            } else if (token[0].equals("unmark")) { // Unmark a task as not done
                int chosenId = Integer.parseInt(token[1]);
                Task chosenTask = tasks[chosenId - 1];
                System.out.println(chosenTask.unmark());
            } else if (token[0].equals("mark")) { // Mark a task as done
                int chosenId = Integer.parseInt(token[1]);
                Task chosenTask = tasks[chosenId - 1];
                System.out.println(chosenTask.mark());
            } else { // adding task
                if (token[0].equals("todo")) {
                    String desc = token[1];
                    Task created = new Todo(desc);
                    tasks[idx] = created;
                    System.out.println("\tGot it. I've added this task:\n\t  " + created);
                } else if (token[0].equals("deadline")) {
                    String[] separated = token[1].split("/by ");
                    Task created = new Deadline(separated[0], separated[1]);
                    tasks[idx] = created;
                    System.out.println("\tGot it. I've added this task:\n\t  " + created);
                } else {
                    String[] separated = token[1].split("/from |/to ", 3);
                    Task created = new Event(separated[0], separated[1], separated[2]);
                    tasks[idx] = created;
                    System.out.println("\tGot it. I've added this task:\n\t  " + created);
                }
                idx++;
            }
        }
        System.out.println("\tBye. Hope to see you again soon!");
    }

}
