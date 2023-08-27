package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The class containing the list of Tasks
 */
class TaskList {
    private Ui userInterface = new Ui();

    /**
     * A method that print the bye message
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Display all the tasks containing in the tasks arraylist
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
     * A method to Mark the task element icon
     * @param taskArrayList an arraylist containing tasks type element
     * @param index         the task index
     * @throws TaskNotExist throws an error if the index overflow or when the task does not exists
     */
    public String mark(ArrayList<Task> taskArrayList, int index) throws TaskNotExist {
        index -= 1;
        if (index >= taskArrayList.size() || index <= -1) {
            throw new TaskNotExist();
        }
        assert index >= 0 : "Invalid Index";
        assert index < taskArrayList.size() : "Invalid index";
        taskArrayList.get(index).mark();
        return userInterface.setMarkAsDone() + taskArrayList.get(index).toString();
    }

    /**
     * A method to Unmark the task element icon
     * @param taskArrayList an arraylist containing tasks type element
     * @param index         the task index
     * @throws TaskNotExist throws an error if the index overflow or when the task does not exists
     */
    public String unMark(ArrayList<Task> taskArrayList, int index) throws TaskNotExist {
        index -= 1;
        if (index >= taskArrayList.size() || index <= -1) {
            throw new TaskNotExist();
        }
        assert index >= 0 : "Invalid Index";
        assert index < taskArrayList.size() : "Invalid index";
        taskArrayList.get(index).unmark();
        return userInterface.setUnMarkTask() + taskArrayList.get(index).toString();
    }

    /**
     * A method to handle the todo task
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
     * A method to handle the deadline task
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the task
     * @throws MissingDescription throws an error when the task given does not contain description
     */
    public String deadline(ArrayList<Task> taskArrayList, String description) throws MissingDescription {
        TimeDate converter = new TimeDate();
        if (!description.contains(" ")) {
            throw new MissingDescription();
        }
        Deadline deadline = converter.deadlineWithDateTime(taskArrayList, description);
        return userInterface.setAddedTask() + deadline + "\n" + "Now you have "
                    + taskArrayList.size() + " task(s) in the list.";
    }

    /**
     * A method to handle the event type task
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
     * A method to delete a task given by the user input
     * @param taskArrayList an arraylist containing tasks type element
     * @param description   description of the task
     */
    public String delete(ArrayList<Task> taskArrayList, String description) throws DukeException {
        try {
            Storage storage = new Storage("./userData/duke.txt");
            if (!description.contains(" ")) {
                throw new DukeException("OOPS!! Please indicate the task index to delete!");
            }

            String[] index = description.split(" ");
            if (index[1].equalsIgnoreCase("all")) {
                storage.deleteAll(taskArrayList);
                return "Noted: I've removed all tasks";
            } else {
                int deleteIndex = Integer.parseInt(index[1]);
                if (deleteIndex > taskArrayList.size() || deleteIndex <= 0) {
                    throw new TaskNotExist();
                }
                assert deleteIndex >= 0 && deleteIndex <= taskArrayList.size() : "Invalid Index";
                String taskDesciption = taskArrayList.get(deleteIndex - 1).toString();
                storage.deleteOne(taskArrayList, deleteIndex);

                return "Noted: I've remove this task\n" + taskDesciption;
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
     * @return
     */
    public String deadlineChecker(ArrayList<Task> taskArrayList, String description) {
        try {
            String[] index = description.split("/");
            TimeDate converter = new TimeDate();
            LocalDate deadline = converter.convertDateInput(index[1].trim());
            ArrayList<Deadline> deadlineTasks = converter.checkDeadlineTask(taskArrayList, deadline);
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
     * A method to find the matching task according to the user input
     * @param key the input value to search for
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
}
