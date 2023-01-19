import Exceptions.EmptyDeadlineException;

import java.util.ArrayList;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static void createDeadline(ArrayList<Task> taskList, String desc) {
        String[] s = desc.split("/");
        Format.line();
        System.out.println("Got it. I've added this task:");
        Deadline deadline = new Deadline(s[0], s[1].substring(2));
        taskList.add(deadline);
        Format.indent("" + deadline);
    }

    public static void runDeadline(ArrayList<Task> taskList, String description) {
        try {
            if (description.length() == 0) {
                throw new EmptyDeadlineException("");
            }
            Deadline.createDeadline(taskList, description);
            Format.checkList(taskList);
        } catch (EmptyDeadlineException e) {
            Format.line();
            System.out.println(e.getMessage());
            Format.line();
        } catch (ArrayIndexOutOfBoundsException e) {
            Format.line();
            System.out.println("Hey! The description of a deadline cannot be empty!");
            Format.line();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
