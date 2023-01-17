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
            String[] arr = str.split(" ", 2);
            try {
                switch (arr[0]) {
                    case "list":
                        int number = 1;
                        for (Task stored : store) {
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
                                    System.out.println(number + ". [D][ ] " + storedString + " (by: " + storedDateTime + ")");
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
                        break;
                    case "mark":
                        if (arr.length < 2) {
                            throw new DukeException("Invalid format, please give numbers");
                        }
                        marking(true, store, arr);
                        break;
                    case "unmark":
                        if (arr.length < 2) {
                            throw new DukeException("Invalid format, please give numbers");
                        }
                        marking(false, store, arr);
                        break;
                    case "todo":
                        if (arr.length < 2) {
                            throw new DukeException("Missing description");
                        }
                        Todo todo = new Todo(arr[1], false);
                        store.add(todo);

                        System.out.println("Got it. I've added this task:\n  [T][ ] " +
                                arr[1] + "\n Now you have " + store.size() + " tasks in the list");
                        break;
                    case "deadline":
                        if (arr.length < 2) {
                            throw new DukeException("Missing description");
                        }
                        String[] toPrintSplit = arr[1].split(" /by ", 2);
                        if (toPrintSplit.length < 2) {
                            throw new DukeException("Missing deadline");
                        }
                        Deadline deadline = new Deadline(toPrintSplit[0], false);
                        deadline.setDateTime(toPrintSplit[1]);
                        store.add(deadline);

                        System.out.println("Got it. I've added this task:\n  [D][ ] " +
                                toPrintSplit[0] + " (by: " + toPrintSplit[1] + ")\n Now you have " +
                                store.size() + " tasks in the list");
                        break;
                    case "event":
                        if (arr.length < 2) {
                            throw new DukeException("Missing description");
                        }
                        String[] startEndTime = arr[1].split(" /from ");
                        if (startEndTime.length < 2) {
                            throw new DukeException("Missing Start Time");
                        }
                        String[] dateTime = startEndTime[1].split(" /to ");
                        if (dateTime.length < 2) {
                            throw new DukeException("Missing End Time");
                        }
                        Event event = new Event(startEndTime[0], false);
                        event.setStartEnd(dateTime[0], dateTime[1]);
                        store.add(event);

                        System.out.println("Got it. I've added this task:\n  [E][ ] " +
                                startEndTime[0] + " (from: " + dateTime[0] + " to: " + dateTime[1] +
                                ")\n Now you have " + store.size() + " tasks in the list");
                        break;
                    default:
                        throw new DukeException("Unknown format");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Unknown error");
            }
            str = bufferedReader.readLine();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void marking(boolean b, ArrayList<? extends Task> store, String[] arr) {
        int index = Integer.parseInt(arr[1]);
        Task task = store.get(index - 1);
        task.setChecked(b);
        if (b) {
            System.out.println("Nice! I've marked this task as done: \n" + "[x] " + task.getStr());
        } else {
            System.out.println("OK, I've marked this task as not done yet: \n" + "[ ] " + task.getStr());
        }

    }

}
