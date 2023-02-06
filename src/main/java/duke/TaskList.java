package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(String taskDescription, String type) {

        if (type.equals("todo")) {
            try {
                Todo todo = new Todo(taskDescription);
                tasks.add(todo);
                System.out.println("Got it. I've added this task:\n" + todo.toString() + "\nNow you have "
                        + tasks.size() + " tasks in the list.");
            } catch (MissingDescriptionException e) {
                System.out.print(e.toString());
            }
        } else if (type.equals("deadline")) {
            try {
                String[] descriptionDate = taskDescription.split("/by ");
                String description = descriptionDate[0];
                String date = descriptionDate[1];
                LocalDate dateString = LocalDate.parse(date);
                Deadline deadline = new Deadline(description, dateString);
                tasks.add(deadline);
                System.out.println("Got it. I've added this task:\n" + deadline.toString() + "\nNow you have "
                        + tasks.size() + " tasks in the list.");
            } catch (MissingDescriptionException e) {
                System.out.print(e.toString());
            } catch (DateTimeParseException e) {
                System.out.println("Please input date in YYYY-MM-DD format!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please fill in all details (task description and date)!");
            }

        } else if (type.equals("event")) {
            try {
                String[] input = taskDescription.split("/from");
                String description = input[0];
                String[] remainder = input[1].split("/to");
                String from = remainder[0];
                String to = remainder[1];
                Event event = new Event(description, from, to);
                tasks.add(event);
                System.out.println("Got it. I've added this task:\n" + event.toString() + "\nNow you have "
                        + tasks.size() + " tasks in the list.");

            } catch (MissingDescriptionException e) {
                System.out.print(e.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please fill in all details (task description, start and end time)!");
            }
        }
    }

    public void markTask(int index) throws IndexOutOfBoundsException {
        try {
            tasks.get(index - 1).mark();
            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such task in the list!");
        }
    }

    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        try {
            tasks.get(index - 1).unmark();
            System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such task in the list!");
        }
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        try {

            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n" + task.toString() + "\nNow you have "
                    + tasks.size() + " tasks in the list.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such task in the list!");
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public String toStorageData() {
        String data = "";
        for (Task task : tasks) {
            data += task.toStorageData();
            data += "\n";
        }

        return data.trim();
    }

    @Override
    public String toString() {
        String taskToText = "";
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            String line = Integer.toString(i) + ". " + task.toString();
            taskToText += line;
            taskToText += "\n";
        }
        return taskToText.trim();

    }

}
