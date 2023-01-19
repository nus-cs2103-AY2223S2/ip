import Exceptions.OutOfBoundsException;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    // Methods for mark
    public static void markDone(ArrayList<Task> taskList, int index) {
        taskList.get(index).markAsDone();
        Format.line();
        System.out.println("OK, marking this task as complete:");
        Format.indent("" + taskList.get(index));
        Format.line();
    }

    public static void alreadyDone() {
        System.out.println("Hey! You've already finished this :)");
    }

    public static void setMark(ArrayList<Task> taskList, String description) {
        try {
            int index = Integer.parseInt(description) - 1;

            if (index >= 0 && index < taskList.size()) {
                if (!taskList.get(index).isDone()) {
                    Task.markDone(taskList, index);
                } else {
                    Format.line();
                    Task.alreadyDone();
                    Format.line();
                }
            } else {
                throw new OutOfBoundsException("");
            }
        } catch (OutOfBoundsException e) {
            Format.line();
            System.out.println(e.getMessage());
            Format.line();
        } catch (NumberFormatException e) {
            Format.line();
            System.out.println("Hey! Please enter a valid number!");
            Format.line();
        }
    }

    // Methods for unmark
    public static void markUndone(ArrayList<Task> taskList, int index) {
        taskList.get(index).markAsUndone();
        Format.line();
        System.out.println("OK, marking this task as not done yet:");
        Format.indent("" + taskList.get(index));
        Format.line();
    }

    public static void alreadyUndone() {
        System.out.println("Hey! You've already unmarked this :)");
    }

    public static void setUnmark(ArrayList<Task> taskList, String description) {
        try {
            int index = Integer.parseInt(description) - 1;

            if (index >= 0 && index < taskList.size()) {
                if (taskList.get(index).isDone()) {
                    Task.markUndone(taskList, index);
                } else {
                    Format.line();
                    Task.alreadyUndone();
                    Format.line();
                }
            } else {
                throw new OutOfBoundsException("");
            }
        } catch (OutOfBoundsException e) {
            Format.line();
            System.out.println(e.getMessage());
            Format.line();
        } catch (NumberFormatException e) {
            Format.line();
            System.out.println("Hey! Please enter a valid number!");
            Format.line();
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
