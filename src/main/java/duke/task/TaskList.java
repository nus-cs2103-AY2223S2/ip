package duke.task;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidPriorityException;
import duke.exceptions.TaskDoesNotExistException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getTaskList() {
        return this.list;
    }

    public int getListSize() {
        return this.list.size();
    }
    public String getList() {
        int counter = 1;
        StringBuilder str = new StringBuilder();
        for (Object task : this.list) {
            str.append(counter + ". " + task.toString());
            str.append(System.getProperty("line.separator"));
            counter++;
        }
        return str.toString();
    }

    public String markTask(String[] parts) throws TaskDoesNotExistException {
        //converting the index from String to Int
        int indexInt = Integer.parseInt(parts[1]) - 1;

        //in case the duke.task.Task index to mark exceeds current number of Tasks or neg number
        if (indexInt + 1 > list.size() || indexInt < 0) {
            throw new TaskDoesNotExistException();
        }

        Task t = list.get(indexInt);
        t.markDone();
        String str = "Nice! I've marked this task as done:\n" + t.toString();
        return str;
    }

    public String unmarkTask(String[] parts) throws TaskDoesNotExistException {
        //converting the index from String to Int
        int indexInt = Integer.parseInt(parts[1]) - 1;

        //in case the duke.task.Task index to mark exceeds current number of Tasks or neg number
        if (indexInt + 1 > this.getListSize() || indexInt < 0) {
            throw new TaskDoesNotExistException();
        }

        Task t = list.get(indexInt);
        t.markUndone();
        String str = "Okay.. I've unmarked this task:\n" + t.toString();
        return str;
    }

    public String changePriority(String[] parts) throws TaskDoesNotExistException, InvalidPriorityException {
        //split into task index and priority
        String indexPriorityArr[] = parts[1].split(" ", 2);
        int priority = Integer.parseInt(indexPriorityArr[1]);

        //converting the index from String to Int
        int indexInt = Integer.parseInt(indexPriorityArr[0]) - 1;

        //in case the task index to mark exceeds current number of Tasks or neg number
        if (indexInt + 1 > this.getListSize() || indexInt < 0) {
            throw new TaskDoesNotExistException();
        }

        if (priority < 0 || priority > 3) {
            throw new InvalidPriorityException();
        }

        Task t = list.get(indexInt);
        t.setPriority(priority);
        String str = "I've changed the priority of this task:\n" + t.toString();
        return str;
    }

    public String deleteTask(String[] parts) throws TaskDoesNotExistException {
        int indexInt = Integer.parseInt(parts[1]) - 1;
        //in case the duke.task.Task index to mark exceeds current number of Tasks or neg number
        if (indexInt + 1 > list.size() || indexInt < 0) {
            throw new TaskDoesNotExistException();
        }

        String task = list.get(indexInt).toString();
        list.remove(indexInt);
        String str = "Noted. I've removed this duke.task: \n"
                + task
                + "\nNow you have " + list.size() + " tasks in the list.";
        return str;
    }

    public String find(String[] parts) throws EmptyDescriptionException {
        if (parts.length == 1) {
            throw new EmptyDescriptionException();
        }

        StringBuilder newList = new StringBuilder();
        int counter = 1;
        for (Task task: list) {
            String description = task.getDescription().toLowerCase();
            String word = parts[1].trim();

            if (description.contains(word.toLowerCase())) {
                newList.append(counter + ". "
                        + task.toString() + "\n");

                counter++;
            }

        }
        String str = "Here are the matching tasks in your list: \n" + newList;
        return str;
    }

    public String createTask(String[] parts) throws EmptyDescriptionException {

        String taskType = parts[0];

        if (parts.length == 1) {
            throw new EmptyDescriptionException();
        }

        switch(taskType) {

            case "todo":
                String todoDescription = parts[1];
                Task t = new Todo(todoDescription);
                this.list.add(t);
                String todoStr = "New todo added: " + todoDescription;
                return todoStr;

            case "deadline":
                //split into descrpition and time
                String part2[] = parts[1].split("/by:", 2);

                String deadlineDescription = part2[0].trim();
                String time = part2[1].trim();

                //turn String date into LocalDate object date
                LocalDate date = LocalDate.parse(time);
                Task d = new Deadline(deadlineDescription, date);
                list.add(d);
                String deadlineStr = "New deadline added: " + deadlineDescription;
                return deadlineStr;

            case "event":
                //split into descrpition and time
                String part3[] = parts[1].split("/from:", 2);

                String eventDescription = part3[0].trim();

                String timearr[] = part3[1].split("/to:", 2);
                String from = timearr[0].trim();
                String to = timearr[1].trim();

                LocalDate fromdate = LocalDate.parse(from);
                LocalDate todate = LocalDate.parse(to);

                Task e  = new Event(eventDescription, fromdate, todate);
                this.list.add(e);
                String eventStr = "New event added: " + eventDescription;
                return eventStr;

        }
        return "error";
    }
}
