import java.util.Scanner;
public class Duke {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int idx = 0;
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        while(true) {
            String input = scanner.nextLine();
            // Exit
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (Task task: tasks) {
                    if (task == null) {
                        break;
                    }
                    System.out.println(task);
                }
            } else if (input.contains("unmark")) {
                int chosenId = Integer.parseInt(input.split(" ")[1]);
                Task chosenTask = tasks[chosenId - 1];
                System.out.println(chosenTask.unmark());
            } else if(input.contains("mark")) {
                int chosenId = Integer.parseInt(input.split(" ")[1]);
                Task chosenTask = tasks[chosenId - 1];
                System.out.println(chosenTask.mark());
            } else {
                tasks[idx] = new Task(idx + 1, input);
                idx++;
                System.out.println("\tadded: " + input);
            }
        }
        System.out.println("\tBye. Hope to see you again soon!");
    }

}
