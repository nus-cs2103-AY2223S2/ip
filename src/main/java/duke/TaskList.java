package duke;

import java.io.IOException;
import java.time.DateTimeException;
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
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * Display all the tasks containing in the tasks arraylist
     *
     * @param tasks an arraylist containing tasks type element
     */
    public void showList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int no = i + 1;
            System.out.println(no + "." + tasks.get(i));
        }
    }

    /**
     * Mark the task element icon
     *
     * @param taskArrayList an arraylist containing tasks type element
     * @param index         the task index
     * @throws TaskNotExist throws an error if the index overflow or when the task does not exists
     */
    public void mark(ArrayList<Task> taskArrayList, int index) throws TaskNotExist {
        index -= 1;
        if (index >= taskArrayList.size() || index <= -1) {
            throw new TaskNotExist();
        }
        userInterface.setMarkAsDone();
        taskArrayList.get(index).mark();
        System.out.println(taskArrayList.get(index).toString());
    }

    /**
     * Unmark the task element icon
     *
     * @param taskArrayList an arraylist containing tasks type element
     * @param index         the task index
     * @throws TaskNotExist throws an error if the index overflow or when the task does not exists
     */
    public void unMark(ArrayList<Task> taskArrayList, int index) throws TaskNotExist {
        index -= 1;
        if (index >= taskArrayList.size()) {
            throw new TaskNotExist();
        }
        userInterface.setUnMarkTask();
        taskArrayList.get(index).unmark();
        System.out.println(taskArrayList.get(index).toString());
    }

    /**
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the task
     * @throws MissingDescription throws an error when the task given does not contain description
     */
    public void toDo(ArrayList<Task> taskArrayList, String description) throws MissingDescription {
        if (description == null) {
            throw new MissingDescription();
        }
        Task newTask = new ToDo(description);
        userInterface.setAddedTask();
        taskArrayList.add(newTask);
        System.out.println(newTask);
        System.out.println("Now you have " + taskArrayList.size() + " task(s) in the list.");
    }

    /**
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the task
     * @throws MissingDescription throws an error when the task given does not contain description
     */
    public void deadline(ArrayList<Task> taskArrayList, String description) throws MissingDescription {
        try {
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
                System.out.println(dead);
            } else {
                String dateInString = deadline[1].trim();
                LocalDate date = converter.convertDateInput(dateInString);
                Deadline dead = new Deadline(deadline[0].trim(), date);
                taskArrayList.add(dead);
                System.out.println(dead);
            }
            userInterface.setAddedTask();
            System.out.println("Now you have " + taskArrayList.size() + " task(s) in the list.");
        } catch (DateTimeException e) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty "
                    + "and please enter in this date format (YYYY-MM-DD OR YYYY/MM/DD) or "
                    + "Day of the week (E.g. Monday, Tuesday, Wednesday).\n"
                    + "If time is provided, please key in this format 1200 after entering the date");
        }
    }

    /**
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the task
     * @throws MissingDescription throws an error when the task given does not contain description
     */
    public void event(ArrayList<Task> taskArrayList, String description) throws MissingDescription {
        if (!description.contains(" ")) {
            throw new MissingDescription();
        }
        String des = description.substring(description.indexOf(" "));
        String[] events = des.split("/");
        Event e = new Event(events[0].trim(), events[1].trim(), events[2].trim());
        taskArrayList.add(e);
        userInterface.setAddedTask();
        System.out.println(e);
        System.out.println("Now you have " + taskArrayList.size() + " task(s) in the list.");

    }

    /**
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the task
     */
    public void delete(ArrayList<Task> taskArrayList, String description) {
        try {
            Storage storage = new Storage("./userRecords/duke.txt");
            if (!description.contains(" ")) {
                throw new DukeException("OOPS!! Please indicate the task index to delete!");
            }

            String[] index = description.split(" ");
            if (index[1].equalsIgnoreCase("all")) {
                storage.deleteAll(taskArrayList);
                System.out.println("Noted: I've removed all tasks");
            } else {
                int deleteIndex = Integer.parseInt(index[1]);
                if (deleteIndex > taskArrayList.size() || deleteIndex <= -1) {
                    throw new DukeException("OOPS!! The index requested to be deleted does not exist!");
                } else {
                    System.out.println("Noted: I've removed this task");
                    Task whichTask = taskArrayList.get(deleteIndex - 1);
                    System.out.println(whichTask);
                    taskArrayList.remove(deleteIndex - 1);
                }
            }
        } catch (DukeException | IOException e) {
            System.out.println("Oh no either file or the task doesn't exist. Please try again");
        }
    }

    /**
     * To return a list of tasks by a given deadline
     *
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the tas
     */
    public void deadlineChecker(ArrayList<Task> taskArrayList, String description) {
        try {
            String[] index = description.split("/");
            DateStringConverter converter = new DateStringConverter();
            LocalDate deadline = converter.convertDateInput(index[1].trim());
            ArrayList<Deadline> deadlineTasks = checkDeadlineTask(taskArrayList, deadline);
            System.out.println("Here is the list before this deadline: " + deadline);
            for (int i = 0; i < deadlineTasks.size(); i++) {
                System.out.println(deadlineTasks.get(i));
            }
        } catch (Exception e) {
            System.out.println("I do not understand what you type >.< !! Enter in by/ YYYY-MM-DD");
        }
    }

    /**
     * @param key           the input value to search for
     * @param taskArrayList an arraylist containing all the tasks
     */
    public void find(String key, ArrayList<Task> taskArrayList) {
        boolean noResult = true;
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i);
            if (task.toString().contains(key)) {
                noResult = false;
                System.out.println(task);
            }
        }

        if (noResult) {
            System.out.println("No Matching Result Found");
        }
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
