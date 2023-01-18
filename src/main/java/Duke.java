import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String start_message = "Hello! I'm Duke\n" +  "What can I do for you?";
        System.out.println(start_message);
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<Task>();
        String s = scanner.nextLine();
        int i = 1;

        while (!s.equals("bye")) {
            Integer len = s.length();
            if (s.equals("list")){
                System.out.println("Here are the tasks in your list:");
                i = 1;
                for (Task task: list){
                    System.out.println(String.valueOf(i) + ". " + task.getStatusIcon() + " " +task.getDes());
                    i++;
                }
            } else if (len >= 6 && s.substring(0,4).equals("mark")){
                Integer num = Integer.parseInt(s.substring(5));
                System.out.println("Nice! I've marked this task as done");
                Task curr_task =  list.get(num - 1);
                curr_task.markAsDone();
                System.out.println(curr_task.getStatusIcon() + curr_task.getDes());
            } else if (len >= 8 && s.substring(0, 6).equals("unmark")){
                Integer num = Integer.parseInt(s.substring(7));
                System.out.println("OK, I've marked this task as not done yet");
                Task curr_task = list.get(num - 1);
                curr_task.unMark();
                System.out.println(curr_task.getStatusIcon() + curr_task.getDes());
            }else {
                Task task = new Task(s);
                list.add(task);
                System.out.println("added: " + task.getDes());
            }
            s = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}