import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    private final static String FILE_PATH = "src/data/tasks.txt";
    private final static String DIRECTORY_PATH = "src/data";
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        Storage storage = new Storage(FILE_PATH, DIRECTORY_PATH);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = bufferedReader.readLine();
        ArrayList<Task> store = storage.readData();
        while (!str.equals("bye")) {
            String[] arr = str.split(" ", 2);
            try {
                switch (arr[0]) {
                    case "list":
                        if (arr.length > 1) {
                            throw new DukeException("Invalid format");
                        }
                        listTask(store);
                        break;
                    case "mark":
                        if (arr.length < 2) {
                            throw new DukeException("Invalid format, please give numbers");
                        }
                        marking(true, store, arr);
                        storage.writeData(store);
                        break;
                    case "unmark":
                        if (arr.length < 2) {
                            throw new DukeException("Invalid format, please give numbers");
                        }
                        marking(false, store, arr);
                        storage.writeData(store);
                        break;
                    case "todo":
                        if (arr.length < 2) {
                            throw new DukeException("Missing description");
                        }
                        ToDo todo = new ToDo(arr[1], false);
                        store.add(todo);
                        storage.writeData(store);

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
                        DeadLine deadline = new DeadLine(toPrintSplit[0], false);
                        deadline.setDateTime(toPrintSplit[1]);
                        store.add(deadline);
                        storage.writeData(store);

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
                        storage.writeData(store);

                        System.out.println("Got it. I've added this task:\n  [E][ ] " +
                                startEndTime[0] + " (from: " + dateTime[0] + " to: " + dateTime[1] +
                                ")\n Now you have " + store.size() + " tasks in the list");
                        break;
                    case "delete":
                        if (arr.length < 2) {
                            throw new DukeException("Invalid format, please give numbers");
                        }
                        deleteTask(store, arr[1]);
                        storage.writeData(store);

                        break;
                    default:
                        throw new DukeException("Unknown format");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please provide numbers");
            } catch (Exception e) {
                System.out.println("Unknown error");
            }
            str = bufferedReader.readLine();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listTask(ArrayList<Task> store) {
        int number = 1;
        for (Task stored : store) {
            System.out.println(number + ". " + stored.toString());
            number++;
        }
    }
    public static void marking(boolean b, ArrayList<Task> store, String[] arr) throws DukeException {
        int index = Integer.parseInt(arr[1]) - 1;
        int size = store.size();
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = store.get(index);
        task.setChecked(b);
        if (b) {
            System.out.println("Nice! I've marked this task as done: \n" + "[x] " + task.getStr());
        } else {
            System.out.println("OK, I've marked this task as not done yet: \n" + "[ ] " + task.getStr());
        }
    }

    public static void deleteTask(ArrayList<Task> store, String num) throws DukeException {
        int index = Integer.parseInt(num) - 1;
        int size = store.size();
        int dsize = size - 1;
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = store.get(index);
        System.out.println("Noted. I've removed this task:\n  " +
                task.toString() +
                "\nNow you have " + dsize + " tasks in the list.");
        store.remove(index);
    }


}
