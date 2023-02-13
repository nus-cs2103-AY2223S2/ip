package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The class containing the list of Tasks
 */
class TaskList {
    private Ui userInterface = new Ui();

    /**
     * Print the bye message
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Display all the tasks containing in the tasks arraylist
     *
     * @param tasks an arraylist containing tasks type element
     */
    public String showList(ArrayList<Task> tasks) {
        String listOfTasks = "The following is the tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            int no = i + 1;
            listOfTasks = listOfTasks + no + ". " + tasks.get(i) + "\n";
        }
        return listOfTasks;
    }

    /**
     * Mark the task element icon
     *
     * @param taskArrayList an arraylist containing tasks type element
     * @param index         the task index
     * @throws TaskNotExist throws an error if the index overflow or when the task does not exists
     */
    public String mark(ArrayList<Task> taskArrayList, int index) {
        index -= 1;
        assert index >= 0 : "Invalid Index";
        assert index < taskArrayList.size() : "invalid Index";
        taskArrayList.get(index).mark();
        return userInterface.setMarkAsDone() + taskArrayList.get(index).toString();
    }

    /**
     * Unmark the task element icon
     *
     * @param taskArrayList an arraylist containing tasks type element
     * @param index         the task index
     * @throws TaskNotExist throws an error if the index overflow or when the task does not exists
     */
    public String unMark(ArrayList<Task> taskArrayList, int index) {
        index -= 1;
        assert index >= 0 : "Invalid Index";
        assert index < taskArrayList.size() : "invalid Index";
        taskArrayList.get(index).unmark();
        return userInterface.setUnMarkTask() + taskArrayList.get(index).toString();
    }

    /**
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the task
     * @throws MissingDescription throws an error when the task given does not contain description
     */
    public String toDo(ArrayList<Task> taskArrayList, String description) throws MissingDescription {
        if (description == null) {
            throw new MissingDescription();
        }
        Task newTask = new ToDo(description);
        taskArrayList.add(newTask);
        return userInterface.setAddedTask() + newTask + "\n"
                + "Now you have " + taskArrayList.size() + " task(s) in the list.";
    }

    /**
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the task
     * @throws MissingDescription throws an error when the task given does not contain description
     */
    public String deadline(ArrayList<Task> taskArrayList, String description) throws MissingDescription {
        DateStringConverter converter = new DateStringConverter();
        if (!description.contains(" ")) {
            throw new MissingDescription();
        }
        String des = description.substring(description.indexOf(" ")).trim();
        String[] deadline = des.split("/by");
        String[] timeExists = deadline[1].trim().split(" ");
        if (timeExists.length > 1) {
            String dateInString = timeExists[0];
            String timeInString = timeExists[1];
            LocalDate date = converter.convertDateInput(dateInString);
            LocalTime time = converter.convertTimeInput(timeInString);
            Deadline dead = new Deadline(deadline[0].trim(), date, time);
            taskArrayList.add(dead);
            return dead + userInterface.setAddedTask() + "Now you have "
                    + taskArrayList.size() + " task(s) in the list.";
        } else {
            String dateInString = deadline[1].trim();
            LocalDate date = converter.convertDateInput(dateInString);
            Deadline dead = new Deadline(deadline[0].trim(), date);
            taskArrayList.add(dead);
            return dead + userInterface.setAddedTask() + "Now you have "
                    + taskArrayList.size() + " task(s) in the list.";
        }
    }

    /**
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the task
     * @throws MissingDescription throws an error when the task given does not contain description
     */
    public String event(ArrayList<Task> taskArrayList, String description) throws MissingDescription {
        if (!description.contains(" ")) {
            throw new MissingDescription();
        }
        String des = description.substring(description.indexOf(" "));
        String[] events = des.split("/");
        Event e = new Event(events[0].trim(), events[1].trim(), events[2].trim());
        taskArrayList.add(e);
        return userInterface.setAddedTask() + e + "\n" + "Now you have "
                + taskArrayList.size() + " task(s) in the list.";
    }

    /**
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the tas
     * @return A String containing the message that the task is deleted
     * @throws DukeException
     */
    public String delete(ArrayList<Task> taskArrayList, String description) throws DukeException {
        try {
            Storage storage = new Storage("./userRecords/duke.txt");
            if (!description.contains(" ")) {
                throw new DukeException("OOPS!! Please indicate the task index to delete!");
            }

            String[] index = description.split(" ");
            if (index[1].equalsIgnoreCase("all")) {
                storage.deleteAll(taskArrayList);
                return "Noted: I've removed all tasks";
            } else {
                int deleteIndex = Integer.parseInt(index[1]);
                assert deleteIndex >= 0 : "Invalid Index";
                assert deleteIndex < taskArrayList.size() : "invalid Index";
                return "Noted: I've remove this task\n" + taskArrayList.remove(deleteIndex - 1);
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * To return a list of tasks by a given deadline
     *
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the tas
     * @return A String containing the message that the following tasks are before the given deadline
     */
    public String deadlineChecker(ArrayList<Task> taskArrayList, String description) {
        try {
            String[] index = description.split("/");
            DateStringConverter converter = new DateStringConverter();
            LocalDate deadline = converter.convertDateInput(index[1].trim());
            ArrayList<Deadline> deadlineTasks = checkDeadlineTask(taskArrayList, deadline);
            String output = "Here is the list before this deadline: " + deadline + "\n";
            for (int i = 0; i < deadlineTasks.size(); i++) {
                System.out.println(deadlineTasks.get(i));
                output = output + deadlineTasks.get(i) + "\n";
            }
            return output;
        } catch (Exception e) {
            return "I do not understand what you type >.< !! Enter in by/ YYYY-MM-DD";
        }
    }

    /**
     * @param key           the input value to search for
     * @param taskArrayList an arraylist containing all the tasks
     */
    public String find(String key, ArrayList<Task> taskArrayList) {
        boolean noResult = true;
        String matchingTask = "Here is the list of the matching task: \n";
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i);
            if (task.toString().contains(key)) {
                noResult = false;
                matchingTask = matchingTask + task + "\n";
            }
        }

        if (noResult) {
            return "No Matching Result Found";
        }
        return matchingTask;
    }

    /**
     * To return a list of tasks before the deadline given
     *
     * @param tasks an arraylist containing the list of tasks
     * @param date  indicating the deadline date the user wants
     * @return an arraylist of type deadline
     */
    private ArrayList<Deadline> checkDeadlineTask(ArrayList<Task> tasks, LocalDate date) {
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                Deadline singleTask = ((Deadline) tasks.get(i));
                String[] s = singleTask.toString().split(":");
                DateStringConverter converter = new DateStringConverter();
                LocalDate dueDate = converter.convertDateInput(s[1].replace(")", "").trim());
                if (dueDate.isBefore(date)) {
                    deadlineTasks.add(singleTask);
                }
            }
        }
        return deadlineTasks;
    }
}
