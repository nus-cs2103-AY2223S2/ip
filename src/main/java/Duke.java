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

    public void add(String type, String taskDetails) {
        Task task;
        separator();
        switch(type) {
            case "todo":
                task = new ToDo(taskDetails);
                storage.add(task);
                System.out.println("\t" + task);
                break;
            case "deadline":
                String[] arr = taskDetails.split("/by", 2);
                task = new Deadline(arr[0], arr[1]);
                storage.add(task);
                System.out.println("\t" + task);
                break;
            case "event":
                String[] description_others = taskDetails.split("/from", 2);
                String[] from_to = description_others[1].split("/to", 2);
                task = new Event(description_others[0], from_to[0], from_to[1]);
                storage.add(task);
                System.out.println("\t" + task);
                break;
            default:
                System.out.println("Please insert either a todo, deadline or event.");
                break;
        }
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
            String str = addressBook.sc.nextLine();
            String[] arr = str.split(" ", 2);
            switch (arr[0]) {
                case "bye":
                    addressBook.exit();
                    break;
                case "list":
                    addressBook.list();
                    break;
                case "mark":
                    addressBook.setTaskStatus(Integer.parseInt(arr[1]), true);
                    break;
                case "unmark":
                    addressBook.setTaskStatus(Integer.parseInt(arr[1]), false);
                    break;
                default:
                    addressBook.add(arr[0], arr[1]);
                    break;
            }
        }
    }

}
