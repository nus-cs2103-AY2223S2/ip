package duke.task;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.TaskDoesNotExistException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return this.list;
    }

    public int getListSize() {
        return this.list.size();
    }
    public void getList() {
        int counter = 1;
        for (Object task : this.list) {
            System.out.println(counter + ". " + task.toString());
            counter++;
        }
    }

    public void markTask(String[] parts) throws TaskDoesNotExistException {
        //converting the index from String to Int
        int indexInt = Integer.parseInt(parts[1]) - 1;

        //in case the duke.task.Task index to mark exceeds current number of Tasks or neg number
        if (indexInt + 1 > list.size() || indexInt < 0) {
            throw new TaskDoesNotExistException();
        }

        Task t = list.get(indexInt);
        t.markDone();
        System.out.println("Nice! I've marked this duke.task as done:\n" + t.toString());
    }

    public void unmarkTask(String[] parts) throws TaskDoesNotExistException {
        //converting the index from String to Int
        int indexInt = Integer.parseInt(parts[1]) - 1;

        //in case the duke.task.Task index to mark exceeds current number of Tasks or neg number
        if (indexInt + 1 > this.getListSize() || indexInt < 0) {
            throw new TaskDoesNotExistException();
        }

        Task t = list.get(indexInt);
        t.markUndone();
        System.out.println("Okay.. I've unmarked this duke.task:\n" + t.toString());
    }

    public void deleteTask(String[] parts) throws TaskDoesNotExistException {
        int indexInt = Integer.parseInt(parts[1]) - 1;
        //in case the duke.task.Task index to mark exceeds current number of Tasks or neg number
        if (indexInt + 1 > list.size() || indexInt < 0) {
            throw new TaskDoesNotExistException();
        }

        String task = list.get(indexInt).toString();
        list.remove(indexInt);
        System.out.println("Noted. I've removed this duke.task: \n"
                + task
                + "\nNow you have " + list.size() + " tasks in the list.");

    }

    public void createTask(String[] parts) throws EmptyDescriptionException {

        String taskType = parts[0];

        if (parts.length == 1) {
            throw new EmptyDescriptionException();
        }

        switch(taskType) {

            case "todo":
                String todoDescription = parts[1];
                Task t = new Todo(todoDescription);
                this.list.add(t);
                System.out.println("New todo added: " + todoDescription);
                break;

            case "deadline":
                //split into descrpition and time
                String part2[] = parts[1].split("/by:", 2);

                String deadlineDescription = part2[0];
                String time = part2[1].trim();

                //turn String date into LocalDate object date
                LocalDate date = LocalDate.parse(time);
                Task d = new Deadline(deadlineDescription, date);
                list.add(d);
                System.out.println("New deadline added: " + deadlineDescription);
                break;

            case "event":
                //split into descrpition and time
                String part3[] = parts[1].split("/from:", 2);

                String eventDescription = part3[0];

                String timearr[] = part3[1].split("/to:", 2);
                String from = timearr[0].trim();
                String to = timearr[1].trim();

                LocalDate fromdate = LocalDate.parse(from);
                LocalDate todate = LocalDate.parse(to);

                Task e  = new Event(eventDescription, fromdate, todate);
                this.list.add(e);
                System.out.println("New event added: " + eventDescription);
                break;

        }
    }
}
