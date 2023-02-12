import java.util.*;

public class Duke {
    public static void main(String[] args) throws DukeException {
        System.out.println("Duke: Yoooz it's your boy Duke! What's up?");
        try (Scanner sc = new Scanner(System.in)) {
            DataSaver dataSaver = new DataSaver();
            TaskArrayList tasks = new TaskArrayList(dataSaver.load());
            while (true) {
                System.out.print("You: ");
                String str = sc.nextLine();
                if (str.equals("bye")) {
                    System.out.println("Duke: Good talk man, catch you again some other time!");
                    dataSaver.save(tasks);
                    break;
                } else if (str.equals("list")) {
                    System.out.println("Duke: Here you go!");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                } else if (str.split(" ")[0].equals("mark")) {
                    int index = Integer.parseInt(str.split(" ")[1]) - 1;
                    System.out.println("Duke: Good job man!");
                    System.out.println(tasks.get(index).mark());
                } else if (str.split(" ")[0].equals("unmark")) {
                    int index = Integer.parseInt(str.split(" ")[1]) - 1;
                    System.out.println("Duke: Did you forget something?");
                    System.out.println(tasks.get(index).unmark());
                } else if (str.split(" ")[0].equals("delete")) {
                    int index = Integer.parseInt(str.split(" ")[1]) - 1;
                    System.out.println("Duke: Noted. I've removed this task:");
                    Task toDel = tasks.get(index);
                    tasks.remove(index);
                    System.out.println(toDel);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (str.split(" ")[0].equals("todo")) {
                    String[] spaceSplit = str.split(" ", 2);
                    if (spaceSplit.length < 2) {
                        throw new DukeException("Duke: The description of a todo cannot be empty.");
                    }
                    tasks.add(new ToDo(spaceSplit[1]));
                    System.out.println("Duke: New to-do added:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (str.split(" ")[0].equals("deadline")) {
                    String[] spaceSplit = str.split(" ", 2);
                    String[] bySplit = spaceSplit[1].split("/by", 2);
                    tasks.add(new Deadline(bySplit[0], bySplit[1]));
                    System.out.println("Duke: New deadline added:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (str.split(" ")[0].equals("event")) {
                    String[] spaceSplit = str.split(" ", 2);
                    String[] fromSplit = spaceSplit[1].split("/from", 2);
                    String[] toSplit = fromSplit[1].split("/to", 2);
                    tasks.add(new Event(fromSplit[0], toSplit[0], toSplit[1]));
                    System.out.println("Duke: New event added:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new DukeException("Duke: That doesn't mean anything.");
                }
            }
        } catch (NumberFormatException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }
}
