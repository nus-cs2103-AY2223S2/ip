import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final ArrayList<Task> storage = new ArrayList<>();

    private final Scanner sc = new Scanner(System.in);

    public void greet() {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\nWhat can I do for you?");
        separator();
    }

    public void exit() {
        separator();
        System.out.println("\tBye. Hope to see you again soon!");
        separator();
        System.exit(0);
    }


    public void list() {
        separator();
        for (int i=0; i < this.storage.size(); i++) {
            int currentNumber = i+1;
            Task task = storage.get(i);

            System.out.println("\t" + currentNumber + "." + task);
        }
        separator();
    }

    public void delete(int index) {
        separator();
        Task task = storage.get(index - 1);
        storage.remove(index - 1);
        System.out.println("Noted. I've removed this task:" + "\n\t" + task);
        System.out.println("Now you have " + storage.size() +" tasks in the list.");
        separator();
    }

    public void addToDo(String taskDetails) {
        ToDo task = new ToDo(taskDetails);
        storage.add(task);
        System.out.println("\t" + task);
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
        separator();
    }

    public void addDeadline(String taskDetails) {
        String[] arr = taskDetails.split("/by", 2);
        try {
            if (arr.length == 1) {
                throw new DukeException("Please insert deadline date after /by");
            }
            Deadline task = new Deadline(arr[0], arr[1]);
            storage.add(task);
            System.out.println("\t" + task);
            System.out.println("Now you have " + storage.size() + " tasks in the list.");
            separator();
        } catch (DukeException e) {
            separator();
            System.out.println("\t" + e);
            separator();
        }
    }

    public void addEvent(String taskDetails) {
        String[] description_others = taskDetails.split("/from", 2);
        String[] from_to = description_others[1].split("/to", 2);
        Event task = new Event(description_others[0], from_to[0], from_to[1]);
        storage.add(task);
        System.out.println("\t" + task);
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
        separator();
    }

    public void setTaskStatus(int index, boolean isDone) {
        Task task = storage.get(index - 1);
        task.setDone(isDone);
        separator();
        System.out.println("\tOk, I have marked this task as " + (isDone ? "done" : "not done yet")  +  ":\n\t\t"
                + task);
        separator();
    }

    public static void separator() {
        System.out.println("---------------------------------------------------------------");
    }


    public static void main(String[] args) {
        Duke addressBook = new Duke();
        addressBook.greet();

        while (addressBook.sc.hasNextLine()) {
            try {

                String str = addressBook.sc.nextLine();
                String[] arr = str.split(" ", 2);
                boolean details = arr.length != 1;
                switch (arr[0]) {
                    case "bye":
                        addressBook.exit();
                        break;
                    case "list":
                        addressBook.list();
                        break;
                    case "mark":
                        if (!details) {
                            throw new DukeException("Please include the task index to mark");
                        } else {
                            addressBook.setTaskStatus(Integer.parseInt(arr[1]), true);
                            break;
                        }
                    case "unmark":
                        if (!details) {
                            throw new DukeException("Please include the task index to unmark.");
                        }
                        addressBook.setTaskStatus(Integer.parseInt(arr[1]), false);
                        break;
                    case "delete":
                        if (!details) {
                            throw new DukeException("Please include the task index to delete.");
                        }
                        addressBook.delete(Integer.parseInt(arr[1]));
                        break;
                    case "todo":
                        if (!details) {
                            throw new DukeException("Please include the todo details.");
                        }
                        addressBook.addToDo(arr[1]);
                        break;
                    case "deadline":
                        if (!details) {
                            throw new DukeException("Please include the deadline details.");
                        }
                        addressBook.addDeadline(arr[1]);
                        break;
                    case "event":
                        if (!details) {
                            throw new DukeException("Please include the event details.");
                        }
                        addressBook.addEvent(arr[1]);
                        break;
                    default:
                        throw new DukeException("Sorry, I don't know what that means.");
                }
            } catch (DukeException e) {
                separator();
                System.out.println("\t" + e);
                separator();
            }
        }
    }
}
