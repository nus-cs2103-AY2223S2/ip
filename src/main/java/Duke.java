import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = bufferedReader.readLine();
        ArrayList<Task> store = new ArrayList<>();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                int number = 1;
                for(Task stored : store) {
                    if (stored instanceof Todo) {
                        String storedString = stored.getStr();
                        boolean checked = stored.isChecked();
                        if (checked) {
                            System.out.println(number + ". [T][X] " + storedString);
                        } else {
                            System.out.println(number + ". [T][ ] " + storedString);
                        }
                    } else if (stored instanceof Deadline) {
                        String storedString = stored.getStr();
                        boolean checked = stored.isChecked();
                        String storedDateTime = ((Deadline) stored).getDateTime();
                        if (checked) {
                            System.out.println(number + ". [D][X] " + storedString + " (by: " + storedDateTime + ")");
                        } else {
                            System.out.println(number + ". [D][ ] " + storedString+ " (by: " + storedDateTime + ")");
                        }
                    } else if (stored instanceof Event) {
                        String storedString = stored.getStr();
                        boolean checked = stored.isChecked();
                        String storedStart = ((Event) stored).getStart();
                        String storedEnd = ((Event) stored).getEnd();
                        if (checked) {
                            System.out.println(number + ". [E][X] " + storedString + " (from: " + storedStart +
                                    " to: " + storedEnd + ")");
                        } else {
                            System.out.println(number + ". [E][ ] " + storedString + " (from: " + storedStart +
                                    " to: " + storedEnd + ")");
                        }
                    }


                    number++;
                }
            } else if (str.startsWith("mark ") && isNumber(str.replace("mark ", ""))) {
                int index = Integer.parseInt(str.replace("mark ", ""));
                Task task = store.get(index - 1);
                task.setChecked(true);
                System.out.println("Nice! I've marked this task as done: \n" + "[x] " + task.getStr());
            } else if (str.startsWith("unmark ") && isNumber(str.replace("unmark ", ""))) {
                int index = Integer.parseInt(str.replace("unmark ", ""));
                Task task = store.get(index - 1);
                task.setChecked(false);
                System.out.println("OK, I've marked this task as not done yet: \n" + "[ ] " + task.getStr());
            } else if(str.startsWith("todo ")) {
                String toPrint = str.replace("todo ", "");
                Todo todo = new Todo(toPrint, false);
                store.add(todo);

                System.out.println("Got it. I've added this task:\n  [T][ ] " +
                        toPrint + "\n Now you have " + store.size() + " tasks in the list");
            } else if(str.startsWith("deadline ")) {
                String toPrint = str.replace("deadline ", "");
                String[] toPrintSplit = toPrint.split(" /by ");
                Deadline deadline = new Deadline(toPrintSplit[0], false);
                deadline.setDateTime(toPrintSplit[1]);
                store.add(deadline);

                System.out.println("Got it. I've added this task:\n  [D][ ] " +
                        toPrintSplit[0] + " (by: " + toPrintSplit[1] + ")\n Now you have " +
                        store.size() + " tasks in the list");
            } else if(str.startsWith("event ")) {
                String toPrint = str.replace("event ", "");
                String[] toPrintSplit = toPrint.split(" /from ");
                String[] dateTime = toPrintSplit[1].split(" /to ");
                Event event = new Event(toPrintSplit[0], false);
                event.setStartEnd(dateTime[0], dateTime[1]);
                store.add(event);

                System.out.println("Got it. I've added this task:\n  [E][ ] " +
                        toPrintSplit[0] + " (from: " + dateTime[0] + " to: " + dateTime[1] +
                        ")\n Now you have " + store.size() + " tasks in the list");
            } else {
                Task task = new Task(str, false);
                store.add(task);
                System.out.println(str);
            }
            str = bufferedReader.readLine();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++)
            if (!Character.isDigit(s.charAt(i)))
                return false;

        return true;
    }

}
