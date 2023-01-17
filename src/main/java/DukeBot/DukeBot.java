package DukeBot;
import DukeBot.Exception.BlankFieldExceptions.BlankFieldDeadlineException;
import DukeBot.Exception.BlankFieldExceptions.BlankFieldEventException;
import DukeBot.Exception.BlankFieldExceptions.BlankFieldException;
import DukeBot.Exception.BlankFieldExceptions.BlankFieldTodoException;
import DukeBot.Exception.IncludeExceptions.IncludeByException;
import DukeBot.Exception.IncludeExceptions.IncludeException;
import DukeBot.Exception.IncludeExceptions.IncludeToAndFromException;
import DukeBot.Exception.TaskNumberNotFoundException;
import DukeBot.Exception.UnknownCommandError;

import java.util.ArrayList;
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
            try {
                System.out.println(this.text(line));
            } catch (BlankFieldException | UnknownCommandError | TaskNumberNotFoundException | IncludeException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    public String text(String line) throws BlankFieldException, UnknownCommandError, TaskNumberNotFoundException, IncludeException {


        if (Objects.equals(line, "list")) {
            return this.list();
        } else if (Objects.equals(line, "bye")) {
            return this.bye();
        }


        String[] words = line.split(" ", 2);



        String command = words[0];
        switch (command) {
            case "mark":
                try {
                    if (words.length == 1 || words[1].trim().isEmpty()) {
                        throw new TaskNumberNotFoundException();
                    }
                    int taskNumber = Integer.parseInt(words[1]);
                    if (taskNumber > this.lengthOfList || taskNumber <= 0) {
                        throw new TaskNumberNotFoundException();
                    }
                    return this.mark(taskNumber);
                } catch (NumberFormatException e) {
                    throw new TaskNumberNotFoundException();
                }
            case "unmark":
                try {
                    if (words.length == 1 || words[1].trim().isEmpty()) {
                        throw new TaskNumberNotFoundException();
                    }
                    int taskNumber = Integer.parseInt(words[1]);
                    if (taskNumber > this.lengthOfList || taskNumber <= 0) {
                        throw new TaskNumberNotFoundException();
                    }
                    return this.unmark(taskNumber);
                } catch (NumberFormatException e) {
                    throw new TaskNumberNotFoundException();
                }

            case "todo":
                if (words.length == 1 || words[1].trim().isEmpty()) {
                    throw new BlankFieldTodoException();
                }
                return this.addToDo(words[1]);
            case "deadline":
                if (words.length == 1 || words[1].trim().isEmpty()) {
                    throw new BlankFieldDeadlineException();
                }
                return this.addDeadline(words[1]);
            case "event":
                if (words.length == 1 || words[1].trim().isEmpty()) {
                    throw new BlankFieldEventException();
                }
                return this.addEvent(words[1]);
            case "delete":
                try {
                    if (words.length == 1 || words[1].trim().isEmpty()) {
                        throw new TaskNumberNotFoundException();
                    }
                    int taskNumber = Integer.parseInt(words[1]);
                    if (taskNumber > this.lengthOfList || taskNumber <= 0) {
                        throw new TaskNumberNotFoundException();
                    }
                    return this.delete(taskNumber);
                } catch (NumberFormatException e) {
                    throw new TaskNumberNotFoundException();
                }
        }

        throw new UnknownCommandError("\n" + this.frame + "\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n" +
                this.frame);

    }

    private String delete(int taskNumber) {
        String task = this.list.get(taskNumber - 1).status();
        this.list.remove(taskNumber - 1);
        this.lengthOfList -= 1;
        return this.frame +
                " Noted. I've removed this task:\n" +
                "       " + task +
                "     Now you have " + this.lengthOfList +" tasks in the list." + "\n"
                + this.frame;
    }

    private String unmark(int taskNumber) {

        Task task = this.list.get(taskNumber - 1);
        task.incomplete();
        return this.frame +
                "     OK, I've marked this task as not done yet:\n" +
                "       [ ] " + task.details + "\n" +
                this.frame;
    }

    private String mark(int taskNumber) {

        Task task = this.list.get(taskNumber - 1);
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

    public String addDeadline(String parameters) throws IncludeByException, BlankFieldDeadlineException {


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
            throw new IncludeByException();
        }
        if (task.toString().trim().isEmpty() || deadline.toString().trim().isEmpty()) {
            throw new BlankFieldDeadlineException();
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

    public String addEvent(String parameters) throws IncludeToAndFromException, BlankFieldEventException {

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
            throw new IncludeToAndFromException();
        }

        if (task.toString().trim().isEmpty() ||
                startDate.toString().trim().isEmpty() ||
                endDate.toString().trim().isEmpty()) {
            throw new BlankFieldEventException();
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
