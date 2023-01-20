import java.lang.reflect.Array;
import java.util.*;
public class Duke {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String separator = "____________________________________________________________";
        ArrayList<Task> todoList = new ArrayList<>();

        String openingMessage = separator + "\nHello! I'm Duke\n" + "What can I do for you?\n" + separator;
        System.out.println(openingMessage);

        while(s.hasNext()) {
            String input = s.nextLine();

            if (input.equals("bye")) {
                String closingMessage = separator + "\nBye. Hope to see you again soon!\n" + separator;
                System.out.println(closingMessage);
                break;

            } else if (input.equals("list")) {
                System.out.println(separator + "\nHere are the tasks in your list:");
                for (int i = 0; i < todoList.size(); i++) {
                    Task thisTask = todoList.get(i);
                    System.out.println(Integer.toString(i + 1) + "." + thisTask.toString());
                }
                System.out.println(separator);

            } else if (input.startsWith("todo")) {
                String description = input.replaceAll("todo", "");
                ToDo newToDo = new ToDo(description.trim());
                todoList.add(newToDo);

                System.out.println(separator + "\nGot it. I've added this task:\n" +
                        newToDo.toString() +
                        "\nNow you have " + todoList.size() + " in the list.\n" + separator);

            } else if(input.startsWith("deadline")) {
                String s1 = input.replaceAll("deadline", "");
                String[] s2 = s1.split("/by");

                Deadline newDeadline = new Deadline(s2[0].trim(), s2[1].trim());
                todoList.add(newDeadline);

                System.out.println(separator + "\nGot it. I've added this task:\n" +
                        newDeadline.toString() +
                        "\nNow you have " + todoList.size() + " in the list.\n" + separator);

            } else if(input.startsWith("event")) {
                String s1 = input.replaceAll("event", "");
                String[] s2 = s1.split("/from");
                String[] s3 = s2[1].split("/to");

                Event newEvent = new Event(s2[0].trim(), s3[0].trim(), s3[1].trim());
                todoList.add(newEvent);

                System.out.println(separator + "\nGot it. I've added this task:\n" +
                        newEvent.toString() +
                        "\nNow you have " + todoList.size() + " in the list.\n" + separator);

            } else if(input.startsWith("mark")) {
                String temp = input.replaceAll("mark", "");
                Task thisTask = todoList.get(Integer.parseInt(temp.trim()) - 1);
                thisTask.markDone();
                System.out.println(separator + "\nNice! I've marked this task as done:" + thisTask.toString() + "\n" +  separator);

            } else if(input.startsWith(("unmark"))) {
                String temp = input.replaceAll("unmark", "");
                Task thisTask = todoList.get(Integer.parseInt(temp.trim()) - 1);
                thisTask.unmarkDone();
                System.out.println(separator + "\nOK, I've marked this task as not done yet:" + thisTask.toString() + "\n" + separator);

            }
        }
        s.close();
    }
}