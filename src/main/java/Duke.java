import java.util.*;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Scanner input = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are your tasks in your list:");
                for(int i = 1; i < list.size() + 1; i++) {
                    System.out.println(i + ". " + "[" + list.get(i-1).getStatusIcon() + "]" + list.get(i-1).description);
                }
            } else if (command.substring(0, 4).equals("mark")){
                int index = Character.getNumericValue(command.charAt(5));
                list.get(index-1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n[" + list.get(index-1).getStatusIcon()+"]" + list.get(index-1).description);
            } else if(command.substring(0,6).equals("unmark")) {
                int index = Character.getNumericValue(command.charAt(7));
                list.get(index-1).markAsUndone();
                System.out.println("Ok, I've marked this task as not done yet:\n[" + list.get(index-1).getStatusIcon()+"]" + list.get(index-1).description);
            } else {
                Task newTask = new Task(command);
                list.add(newTask);
                System.out.println("added: " + command);
            }
        }
        input.close();
    }
}
