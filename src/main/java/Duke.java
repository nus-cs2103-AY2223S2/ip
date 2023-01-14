import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---\n hi i'm Duke! what's up? \n---");

        List<Task> list = new ArrayList<>();
        while(sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] split = command.split("\\s");
            if (split[0].equals("mark")) {
                int index = Integer.parseInt(split[1]) - 1;
                list.set(index, list.get(index).markAsDone());
                System.out.println("---\n nice! i've marked this task as done:\n" + list.get(index) + "\n---");
            } else if (split[0].equals("unmark")) {
                int index = Integer.parseInt(split[1]) - 1;
                list.set(index, list.get(index).unmarkAsDone());
                System.out.println("---\n ok, i've marked this task as not done yet:\n" + list.get(index) + "\n---");
            } else if (command.equals("bye")) {
                System.out.println("---\n bye! see u soon! :-) \n---");
                System.exit(0);
            } else if (command.equals("list")) {
                int num = 1;
                System.out.println("---");
                for (Task a : list) {
                    System.out.println(num + ". " + a);
                    num++;
                }
                System.out.println("---");
            } else {
                list.add(new Task(command));
                System.out.println("---\n added: " + command + "\n---");
            }
        }
    }
}