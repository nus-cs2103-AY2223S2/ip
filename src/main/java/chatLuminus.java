import java.util.Scanner;
import java.util.ArrayList;
public class chatLuminus {
    private ArrayList<Task> list;
    chatLuminus() {
        list = new ArrayList<>(100);
    }

    public static void main(String[] args) {
        chatLuminus a = new chatLuminus();
        System.out.println(a.greeting());
        a.echo();
    }

    private String greeting() {
        return "Hello! I'm Luminus\nWhat can I do for you?";
    }

    private void echo() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= this.list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                continue;
            }
            if (input.substring(0,4).equals("mark")) {
                int index = Integer.parseInt(input.substring(5));
                this.list.get(index - 1).markAsDone();
                continue;
            }
            if (input.substring(0,6).equals("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                this.list.get(index - 1).markAsNotDone();
                continue;
            }
            if (input.substring(0,4).equals("todo")) {
                String description = input.substring(5);
                ToDo temp = new ToDo((input));
                list.add(temp);
                System.out.println("Got it. I've added this task:");
                System.out.println(temp);
            }
            if (input.substring(0,8).equals("deadline")) {
                int index = input.indexOf("/");
                String description = input.substring(9, index - 1);
                String by = input.substring(index + 4);
                Deadline temp = new Deadline(description,by);
                list.add(temp);
                System.out.println("Got it. I've added this task:");
                System.out.println(temp);
            }
            if (input.substring(0,5).equals("event")) {
                int index1 = input.indexOf("/");
                int index2 = input.lastIndexOf("/");
                String description = input.substring(6,index1 - 1);
                String from = input.substring(index1 + 6, index2 - 1);
                String to = input.substring(index2 + 4);
                Event temp = new Event(description,from,to);
                list.add(temp);
                System.out.println("Got it. I've added this task:");
                System.out.println(temp);
            }
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }
}
