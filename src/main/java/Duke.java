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

    public void printList(int currentNumber, Task task) {
        System.out.println("\t" + currentNumber + "." + task.getStatusIcon() + task.getDescription());
    }

    public void list() {
        separator();
        for (int i=0; i < this.storage.size(); i++) {
            int currentNumber = i+1;
            Task task = storage.get(i);

            printList(currentNumber, task);
        }
        separator();
    }

    public void add(String str) {
        storage.add(new Task(str));
        separator();
        System.out.println("\tadded: " + str );
        separator();
    }

    public void setTaskStatus(int index, boolean isDone) {
        Task task = storage.get(index - 1);
        task.setDone(isDone);
        separator();
        System.out.println("\tOk, I have marked this task as " + (isDone ? "done" : "not done yet")  +  ":\n\t\t"
                + task.getStatusIcon() + task.getDescription());
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
                    addressBook.add(str);
                    break;
            }
        }
    }

}
