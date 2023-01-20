import java.util.*;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm tyy\nWhat can I do for you?");

        ArrayList<Task> toDoList = new ArrayList<Task>();
        Scanner scan = new Scanner(System.in);
        String input = scan.next();

        while (!input.equals("bye")) {
            if (input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < toDoList.size(); i++) {
                    int num = i + 1;
                    System.out.print("    " + num + ". " + toDoList.get(i) + "\n");
                }
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5));
                Task task = toDoList.get(index - 1);
                task.mark();
                System.out.println("Nice! I've marked this task as done: " + "\n" + task);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                Task task = toDoList.get(index - 1);
                task.unmark();
                System.out.println("Ok, I've marked this task as not done yet: " + "\n" + task);
            } else {
                Task task = new Task(input);
                toDoList.add(task);
                System.out.println("added: " + task);
            }
            input = scan.nextLine();
        }
        System.out.println(" " + "Ciao. Hope to see you again soon!");
    }
}