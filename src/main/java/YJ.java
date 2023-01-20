import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class YJ {
    public static void main(String[] args) {

        System.out.println("Hello! I'm YJ. What can I do for you?");

        // ArrayList of String
        List<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + "." + task.toString());
                }
            } else if (input.startsWith("mark")) {
                Integer taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber != null && tasks.get(taskNumber - 1) != null) {
                    Task task = tasks.get(taskNumber - 1);
                    task.markAsDone();
                    System.out.println("Niceoooo you're done wit this: ");
                    System.out.println(task.toString());
                }
            } else if (input.startsWith("unmark")) {
                Integer taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber != null && tasks.get(taskNumber - 1) != null) {
                    Task task = tasks.get(taskNumber - 1);
                    task.markAsNotDone();
                    System.out.println("Ok lah you haven't finish this yet");
                    System.out.println(task.toString());
                }
            } else if (input.startsWith("todo")) {
                ToDo newToDo = new ToDo(input.replaceAll("todo ", "").trim());
                tasks.add(newToDo);
                System.out.println("Ok! I've added this todo! " + newToDo.toString());
                System.out.println("You now have this many tasks: " + tasks.size());
            } else if (input.startsWith("deadline")) {
                String by = input.split("/by")[1].trim();
                Deadline newDeadline = new Deadline(input.split("/by")[0].trim(), by);
                tasks.add(newDeadline);
                System.out.println("Ok! I've added this deadline!" + newDeadline.toString());
                System.out.println("You now have this many tasks: " + tasks.size());
            } else if (input.startsWith("event")) {
                // Given this string: "event project meeting /from Mon 2pm /to 4pm" extract the string after /from and after /to
                String from = input.split("/from")[1].split("/to")[0].trim();
                String to = input.split("/to")[1].trim();
                Event newEvent = new Event(input.split("/from")[0].trim(), from, to);
                tasks.add(newEvent);
                System.out.println("Ok! I've added this event!" + newEvent.toString());
                System.out.println("You now have this many tasks: " + tasks.size());
            } else {
                System.out.println("HUH??? YJ CONFUSED LIAO");
            }

            input = sc.nextLine();
        }

        System.out.println("Byebye, YJ will miss you :(");
        sc.close();
    }
}

