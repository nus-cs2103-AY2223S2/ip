import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String BOT_REPLY_START = "      ";
        String EXIT_COMMAND = "bye";
        String LIST_COMMAND = "list";
        String MARK_COMMAND = "mark";
        String UNMARK_COMMAND = "unmark";

        List<Task> current_list = new ArrayList<>();

        Scanner user_input = new Scanner(System.in);

        System.out.println("Hello! I'm Anton's Bot");
        System.out.println("What can I do for you?");

        while (true){
            String current_input = user_input.nextLine();
            String[] current_input_array = current_input.split(" ", 2);
            String input_command = current_input_array[0];
            

            if (input_command.equals(EXIT_COMMAND)) {
                System.out.println(String.format("%s Bye. Hope to see you again soon!", BOT_REPLY_START));
                break;
            } else if (input_command.equals(LIST_COMMAND)) {
                System.out.println(String.format("%s Here are the tasks in your list:", BOT_REPLY_START));
                for (int i = 0; i < current_list.size(); i++) {
                    System.out.println(String.format("%s %d.[%s] %s", BOT_REPLY_START, i + 1, current_list.get(i).getStatusIcon(), current_list.get(i).getDescription()));
                }
            } else if (input_command.equals(MARK_COMMAND)) {
                int task_number = Integer.parseInt(current_input_array[1]);
                Task target_task = current_list.get(task_number - 1);
                target_task.markAsDone();
                System.out.println(String.format("%s Nice! I've marked this task as done:", BOT_REPLY_START));
                System.out.println(String.format("%s  [%s] %s", BOT_REPLY_START, target_task.getStatusIcon(), target_task.getDescription()));
                
            } else if (input_command.equals(UNMARK_COMMAND)) {
                int task_number = Integer.parseInt(current_input_array[1]);
                Task target_task = current_list.get(task_number - 1);
                target_task.markAsUndone();
                System.out.println(String.format("%s OK, I've marked this task as not done yet:", BOT_REPLY_START));
                System.out.println(String.format("%s  [%s] %s", BOT_REPLY_START, target_task.getStatusIcon(), target_task.getDescription()));
            } else {
                System.out.println("        added: " + current_input);
                current_list.add(new Task(current_input));
            }
        }
        user_input.close();
    }
}
