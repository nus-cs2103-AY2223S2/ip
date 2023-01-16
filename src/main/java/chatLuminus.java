import java.util.Scanner;
import java.util.ArrayList;
public class chatLuminus {
    private ArrayList<Task> list;
    chatLuminus() {
        list = new ArrayList<Task>(100);
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
                    System.out.println(i + ". " + list.get(i - 1).getStatusIcon()
                            + list.get(i - 1).description);
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
            list.add(new Task(input));
            System.out.println("added: " + input);
        }
    }
}
