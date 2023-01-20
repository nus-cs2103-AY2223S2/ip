import java.util.*;

public class Duke {
    public static void main(String[] args) throws DukeException {
        System.out.println("Leo: Yoooz it's your boy Leo! What's up?");
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;
        while (true) {
            System.out.print("You: ");
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Leo: Good talk man, catch you again some other time!");
                break;
            } else if (str.equals("list")) {
                System.out.println("Leo: Here you go!");
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
            } else if (str.split(" ")[0].equals("mark")) {
                int index = Integer.parseInt(str.split(" ")[1]) - 1;
                System.out.println("Leo: Good job man!");
                System.out.println(tasks[index].mark());
            } else if (str.split(" ")[0].equals("unmark")) {
                int index = Integer.parseInt(str.split(" ")[1]) - 1;
                System.out.println("Leo: Did you forget something?");
                System.out.println(tasks[index].unmark());
            } else if (str.split(" ")[0].equals("todo")) {
                String[] spaceSplit = str.split(" ", 2);
                if (spaceSplit.length < 2) {
                    throw new DukeException("Leo: The description of a todo cannot be empty.");
                }
                tasks[count] = new ToDo(spaceSplit[1]);
                count++;
                System.out.println("Leo: New to-do added:");
                System.out.println(tasks[count-1]);
                System.out.println("Now you have " + count + " tasks in the list.");
            } else if (str.split(" ")[0].equals("deadline")) {
                String[] spaceSplit = str.split(" ", 2);
                String[] bySplit = spaceSplit[1].split("/by", 2);
                tasks[count] = new Deadline(bySplit[0], bySplit[1]);
                count++;
                System.out.println("Leo: New deadline added:");
                System.out.println(tasks[count-1]);
                System.out.println("Now you have " + count + " tasks in the list.");
            } else if (str.split(" ")[0].equals("event")) {
                String[] spaceSplit = str.split(" ", 2);
                String[] fromSplit = spaceSplit[1].split("/from", 2);
                String[] toSplit = fromSplit[1].split("/to", 2);
                tasks[count] = new Event(fromSplit[0], toSplit[0], toSplit[1]);
                count++;
                System.out.println("Leo: New event added:");
                System.out.println(tasks[count-1]);
                System.out.println("Now you have " + count + " tasks in the list.");
            } else {
                throw new DukeException("Leo: That doesn't mean anything.");
            }
        }
    }
}
