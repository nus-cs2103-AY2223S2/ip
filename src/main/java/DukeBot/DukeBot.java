package DukeBot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


public class DukeBot {

    private final ArrayList<Task> list;
    private int lengthOfList;
    private final String frame = "    ____________________________________________________________\n";
    private final Scanner scanner;
    private boolean isActive;


    public DukeBot(Scanner scanner) {
        this.scanner = scanner;
        this.isActive = true;
        this.list = new ArrayList<>();
        this.lengthOfList = 0;
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


        if (Objects.equals(line, "list")) {
            return this.list();
        } else if (Objects.equals(line, "bye")) {
            return this.bye();
        }


        String[] words = line.split(" ", 2);

        if (words.length < 2) {
            return this.frame +
                    "     Error! I dont understand what that command is :(" + "\n" +
                    this.frame;
        }

        String command = words[0];
        String parameters = words[1];
        switch (command) {
            case "mark":
                try {
                    return this.mark(parameters);
                } catch (NumberFormatException e) {
                    return this.frame +
                            "     ERROR: If you want me to mark a task, then use the correct format!! " + "\n" +
                            "     Do \"mark N\" where N is a number" + "\n" +
                            this.frame;
                }
            case "unmark":
                try {
                    return this.unmark(parameters);
                } catch (NumberFormatException e) {
                    return this.frame +
                            "ERROR: If you want me to mark a task, then use the correct format!! " + "\n" +
                            "Do \"unmark N\" where N is a number" + "\n" +
                            this.frame;
                }
            case "todo":
                return this.addToDo(parameters);
            case "deadline":
                return this.addDeadline(parameters);
            case "event":
                return this.addEvent(parameters);
            case "delete":
        }

        return "Unknown command";

    }

    private String unmark(String taskNumberString) {
        int taskNumber = Integer.parseInt(taskNumberString);
        if (taskNumber > this.list.size() || taskNumber <= 0) {
            return this.frame +
                    "I don't think there's a task with that number!" + "\n" +
                    this.frame;
        }
        Task task = list.get(taskNumber - 1);
        task.incomplete();
        return this.frame +
                "     OK, I've marked this task as not done yet:\n" +
                "       [ ] " + task.details + "\n" +
                this.frame;
    }

    private String mark(String taskNumberString) {
        int taskNumber = Integer.parseInt(taskNumberString);
        if (taskNumber > this.list.size() || taskNumber <= 0) {
            return this.frame +
                    "I don't think there's a task with that number!" + "\n" +
                    this.frame;
        }

        Task task = list.get(taskNumber - 1);
        task.complete();
        return this.frame +
                "     Nice! I've marked this task as done:\n" +
                "       [X] " + task.details + "\n" +
                this.frame;
    }

    private String list() {
        StringBuilder res = new StringBuilder(this.frame);
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            res.append("     ").append(i + 1).append(". ").
                    append(task.status()).append("\n");
        }
        return res.append(this.frame).toString();
    }


    public String addToDo(String parameters) {

        ToDo newToDo = new ToDo(parameters);
        this.list.add(newToDo);
        this.lengthOfList += 1;
        return this.frame + "\n" +
                "     Got it. I've added this task:" + "\n" +
                "     " + newToDo.status() + "\n" +
                "     Now you have " + this.lengthOfList + " tasks in the list" + "\n" +
                this.frame;
    }

    public String addDeadline(String parameters) {


        // Extract deadline date and task item.
        String[] lines = parameters.split(" ");
        boolean by = false;
        StringBuilder task = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        for (String line : lines) {
            if (Objects.equals(line, "/by")) {
                by = true;
            } else if (!by) {
                task.append(" ").append(line);
            } else {
                deadline.append(" ").append(line);
            }
        }

        if (!by) {
            return this.frame +
                    "     Error! Did you include the \"/by\" in your command?"  + "\n" +
                    this.frame;
        }

        Deadline newDeadline = new Deadline(task.toString(), deadline.toString());
        this.list.add(newDeadline);
        this.lengthOfList += 1;
        return this.frame + "\n" +
                "     Got it. I've added this task:" + "\n" +
                "     " + newDeadline.status() + "\n" +
                "     Now you have " + this.lengthOfList + " tasks in the list" + "\n" +
                this.frame;
    }

    public String addEvent(String parameters) {

        // Extract event's start date and end date
        String[] lines = parameters.split(" ");
        // State = 0 if extracting task item
        // State = 1 if extracting start date
        // State = 2 if extracting end date
        int state = 0;
        StringBuilder task = new StringBuilder();
        StringBuilder startDate = new StringBuilder();
        StringBuilder endDate = new StringBuilder();

        for (String line : lines) {
            if (Objects.equals(line, "/from") && state == 0) {
                state = 1;
            } else if (Objects.equals(line, "/to") && state == 1) {
                state = 2;
            } else switch (state) {
                case 0:
                    task.append(" ").append(line);
                    break;
                case 1:
                    startDate.append(" ").append(line);
                    break;
                case 2:
                    endDate.append(" ").append(line);
                    break;
            }
        }

        if (state != 2) {
            return this.frame +
                    "     Error: Did you remember to use \"/from\" and \"/to\"?" + "\n" +
                    this.frame;
        }

        Event newEvent = new Event(task.toString(), startDate.toString(), endDate.toString());

        this.list.add(newEvent);
        this.lengthOfList += 1;
        return this.frame + "\n" +
                "     Got it. I've added this task:" + "\n" +
                "     " + newEvent.status() + "\n" +
                "     Now you have " + this.lengthOfList + " tasks in the list" + "\n" +
                this.frame;
    }


    public String bye() {
        this.isActive = false;
        return this.frame +
                "     Bye. Hope to see you again soon!\n" +
                this.frame;
    }





}
