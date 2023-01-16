package DukeBot;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class DukeBot {

    private final ArrayList<Task> list;
    private final String frame = "    ____________________________________________________________\n";
    private final Scanner scanner;
    private boolean isActive;


    public DukeBot(Scanner scanner) {
        this.scanner = scanner;
        this.isActive = true;
        this.list = new ArrayList<>();
    }

    public void activate() {
        System.out.println(this.frame +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                this.frame);
        while (this.isActive) {
            String line = this.scanner.nextLine();
            System.out.println(this.text(line));
        }
    }

    public String text(String line) {

        if (Objects.equals(line, "bye")) {
           return this.bye();
        } else if (Objects.equals(line, "list")) {
            return this.list();
        } else if (line.length() > 4 && Objects.equals(line.substring(0, 4), "mark")) {
            try {
                return this.mark(line.substring(4));
            } catch (NumberFormatException e) {
                return this.frame +
                        "ERROR: If you want me to mark a task, then use the correct format!! " + "\n" +
                        "Do \"mark N\" where N is a number" + "\n" +
                        this.frame;
            }
        } else if (line.length() > 6 && Objects.equals(line.substring(0, 6), "unmark")) {
            try {
                return this.unmark(line.substring(6));
            } catch (NumberFormatException e) {
                return this.frame +
                        "ERROR: If you want me to mark a task, then use the correct format!! " + "\n" +
                        "Do \"unmark N\" where N is a number" + "\n" +
                        this.frame;

            }
        }
        else {
            return this.addItem(line);
        }

    }

    private String unmark(String taskNumberString) {
        if (taskNumberString.charAt(0) != ' ') {
            throw new NumberFormatException();
        }
        int taskNumber = Integer.parseInt(taskNumberString.substring(1));
        if (taskNumber > this.list.size() || taskNumber <= 0) {
            return this.frame +
                    "I don't think there's a task with that number!" + "\n" +
                    this.frame;
        }
        Task task = list.get(taskNumber - 1);
        task.incomplete();
        return this.frame +
                "     OK, I've marked this task as not done yet:\n" +
                "       [ ] " + task.task + "\n" +
                this.frame;
    }

    private String mark(String taskNumberString) {
        if (taskNumberString.charAt(0) != ' ') {
            throw new NumberFormatException();
        }
        int taskNumber = Integer.parseInt(taskNumberString.substring(1));
        if (taskNumber > this.list.size() || taskNumber <= 0) {
            return this.frame +
                    "I don't think there's a task with that number!" + "\n" +
                    this.frame;
        }

        Task task = list.get(taskNumber - 1);
        task.complete();
        return this.frame +
                "     Nice! I've marked this task as done:\n" +
                "       [X] " + task.task + "\n" +
                this.frame;
    }

    private String list() {
        StringBuilder res = new StringBuilder(this.frame);
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            res.append("     ").append(i + 1).append(". ").
                    append(task.status()).
                    append(task.task).append("\n");
        }
        return res.append(this.frame).toString();
    }

    public String addItem(String line) {
        this.list.add(new Task(line));
        return this.frame +
                "     added: " + line + "\n" +
                this.frame;
    }

    public String bye() {
        this.isActive = false;
        return this.frame +
                "     Bye. Hope to see you again soon!\n" +
                this.frame;
    }





}
