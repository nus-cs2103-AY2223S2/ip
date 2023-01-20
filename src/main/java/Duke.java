import java.util.*;
public class Duke {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Task> todoList = new ArrayList<>();

        String separator = "\u200E✽ ✾ \u200E✽ ✾ \u200E✽ ✾ \u200E✽ ✾";
        String openingMessage = "｡ﾟﾟ･｡･ﾟﾟ｡\n" + "。 welcome to tigerlily to-do\n" + "　ﾟ･｡･ﾟ\n" + "✎ . . . . write a command";
        System.out.println(openingMessage);

        while(s.hasNext()) {
            String input = s.nextLine();

            if (input.equals("bye")) {
                String closingMessage = "(\\\\ (\\\\ \n" + "(„• ֊ •„)\n" + "━━O━O━━━━━━━━━━━━━━━\n" +
                        "bye, see you again soon!\n" + "━━━━━━━━━━━━━━━━━━━━\n";
                System.out.println(closingMessage);
                break;

            } else if (input.equals("list")) {
                System.out.println(separator + "\nhere's what's in your list:");
                for (int i = 0; i < todoList.size(); i++) {
                    Task thisTask = todoList.get(i);
                    System.out.println((i + 1) + "." + thisTask.toString());
                }
                System.out.println(separator);

            } else if (input.startsWith("todo")) {
                String description = input.replaceAll("todo", "");
                ToDo newToDo = new ToDo(description.trim());
                todoList.add(newToDo);

                System.out.println(separator + "\nperf, your task has been added:\n" +
                        newToDo.toString() +
                        "\nthere are now " + todoList.size() + " tasks in the list\n" + separator);

            } else if(input.startsWith("deadline")) {
                String s1 = input.replaceAll("deadline", "");
                String[] s2 = s1.split("/by");

                Deadline newDeadline = new Deadline(s2[0].trim(), s2[1].trim());
                todoList.add(newDeadline);

                System.out.println(separator + "\nperf, your task has been added:\n" +
                        newDeadline.toString() +
                        "\nthere are now  " + todoList.size() + " tasks in the list\n" + separator);

            } else if(input.startsWith("event")) {
                String s1 = input.replaceAll("event", "");
                String[] s2 = s1.split("/from");
                String[] s3 = s2[1].split("/to");

                Event newEvent = new Event(s2[0].trim(), s3[0].trim(), s3[1].trim());
                todoList.add(newEvent);

                System.out.println(separator + "\nperf, your task has been added:\n" +
                        newEvent.toString() +
                        "\nthere are now  " + todoList.size() + " tasks in the list\n" + separator);

            } else if(input.startsWith("mark")) {
                String temp = input.replaceAll("mark", "");
                Task thisTask = todoList.get(Integer.parseInt(temp.trim()) - 1);
                thisTask.markDone();
                System.out.println(separator + "\nwell done! you've completed this task: " + thisTask.toString() + "\n" +  separator);

            } else if(input.startsWith(("unmark"))) {
                String temp = input.replaceAll("unmark", "");
                Task thisTask = todoList.get(Integer.parseInt(temp.trim()) - 1);
                thisTask.unmarkDone();
                System.out.println(separator + "\noops...this task is now marked as not done yet: " + thisTask.toString() + "\n" + separator);

            }
        }
        s.close();
    }
}