package duke;
import duke.tasks.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
public class Ui {
    final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected final static String SEPARATOR = "\t____________________________________________________________";
    private Scanner sc;

    public Ui(){
        this.sc = new Scanner(System.in);
    }
    public void printSeparator() {
        System.out.println(SEPARATOR);
    }
    public String readCommand() {
        return sc.nextLine();
    }

    public void welcomeMessage() {
        System.out.println(logo
                + "\n"
                + SEPARATOR
                + "\n\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + SEPARATOR);
    }
    public void byeMessage() {
        System.out.println(SEPARATOR
                + "\n\t Bye. Hope to see you again soon!\n"
                + SEPARATOR);
    }
    public void showMark(Task task) {
        System.out.println(SEPARATOR + "\n\tNice! I've marked this task as done:\n\t  " + task.toString()
                + "\n" + SEPARATOR);
    }

    public void showUnmark(Task task) {
        System.out.println(SEPARATOR + "\n\tOK, I've marked this task as not done yet:\n\t  " + task.toString()
                + "\n" + SEPARATOR);
    }

    public void showAdd(Task task, int size) {
        System.out.println(SEPARATOR + "\n\tGot it. I've added this task:\n\t  "
                + task.toString() + "\n\tNow you have "
                + size + " task(s) in the list.\n"
                + SEPARATOR);
    }

    public void showDelete(Task task, int size) {
        System.out.println(SEPARATOR + "\n\tNoted. I've removed this task:\n\t  "
                + task.toString() + "\n\tNow you have " +
                size + " task(s) in the list.\n"
                + SEPARATOR);
    }

    public void showList(TaskList tasklist) {
        System.out.print(SEPARATOR);
        tasklist.printList();
        System.out.println(SEPARATOR);
    }
    public void showListFromStorage (Storage storage) {
        String filepath = storage.getFilepath();
        if (!Files.exists(Path.of(filepath))) {
            new File(filepath).getParentFile().mkdirs();
        } else {
            File file = new File(filepath);
            try {
                Scanner sc = new Scanner(file);
                System.out.println("\tThese is your current TaskList");
                while (sc.hasNextLine()) {
                    String text = sc.nextLine();
                    System.out.println("\t" + text.trim());
                }
                System.out.println(SEPARATOR);
            } catch (FileNotFoundException e){
                throw new RuntimeException(e);
            }
        }
    }
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
