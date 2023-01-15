import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---\n hi i'm Duke! what's up? \n---");

        List<Task> list = new ArrayList<>();
        while(sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] split = command.split(" ", 2);
            if (split[0].equals("mark")) {
                int index = Integer.parseInt(split[1]) - 1;
                list.set(index, list.get(index).markAsDone());
                System.out.println("---\nnice! i've marked this task as done:\n" + list.get(index) + "\n---");
            } else if (split[0].equals("unmark")) {
                int index = Integer.parseInt(split[1]) - 1;
                list.set(index, list.get(index).unmarkAsDone());
                System.out.println("---\nok, i've marked this task as not done yet:\n" + list.get(index) + "\n---");
            } else if (command.equals("bye")) {
                System.out.println("---\nbye! see u soon! :-)\n---");
                System.exit(0);
            } else if (command.equals("list")) {
                int num = 1;
                System.out.println("---");
                for (Task a : list) {
                    System.out.println(num + ". " + a);
                    num++;
                }
                System.out.println("---");
            } else if (split[0].equals("todo")) {
                Todo newTodo = new Todo(split[1]);
                list.add(newTodo);
                System.out.println("---\ni've added this task:\n" + newTodo);
                if (list.size() == 1) {
                    System.out.println("you have 1 task in the list\n---");
                } else {
                    System.out.println("you have " + list.size() + " tasks in the list\n---");
                }
            } else if (split[0].equals("deadline")) {
                String[] splitby = split[1].split(" /by ");
                Deadline newDeadline = new Deadline(splitby[0], splitby[1]);
                list.add(newDeadline);
                System.out.println("---\ni've added this task with deadline:\n" + newDeadline);
                if (list.size() == 1) {
                    System.out.println("you have 1 task in the list\n---");
                } else {
                    System.out.println("you have " + list.size() + " tasks in the list\n---");
                }
            } else if (split[0].equals("event")) {
                String[] splitdesc = split[1].split(" /from ");
                String[] splitfromto = splitdesc[1].split(" /to ");
                Event newEvent = new Event(splitdesc[0], splitfromto[0], splitfromto[1]);
                list.add(newEvent);
                System.out.println("---\ni've added this event:\n" + newEvent);
                if (list.size() == 1) {
                    System.out.println("you have 1 task in the list\n---");
                } else {
                    System.out.println("you have " + list.size() + " tasks in the list\n---");
                }
            } else {
                System.out.println("---\ninvalid command\n---");
            }
        }
    }
}