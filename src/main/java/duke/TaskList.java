package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of the list containing tasks
 */
public class TaskList {
    private ArrayList<Task> list;
    private int length;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");


    /**
     * Constructor for the TaskList class
     * @param tasks
     */
    public TaskList(ArrayList<String> tasks) throws DukeException {
        this.list = new ArrayList<>();
        for (String s: tasks) {
            length++;
            String[] array = s.split("\\|");

            switch (array[0]) {
            case "T": {
                this.addTask(new Todo(array[2], Boolean.parseBoolean(array[1])));
                break;
            }
            case "D": {
                LocalDateTime byDate = LocalDateTime.parse(array[3], storageFormatter);
                this.addTask(new Deadline(array[2], byDate, Boolean.parseBoolean(array[1])));
                break;
            }
            case "E": {
                LocalDateTime fromDate = LocalDateTime.parse(array[3], storageFormatter);
                LocalDateTime toDate = LocalDateTime.parse(array[4], storageFormatter);
                this.addTask(new Event(array[2], fromDate, toDate, Boolean.parseBoolean(array[1])));
                break;
            }
            default:
                throw new DukeException("Error Task Type!!");
            }
        }

    }

    public TaskList() {
        list = new ArrayList<>();
    }


    /**
     * return size of the array
     */
    public int getNumTask() {
        return length;
    }


    /**
     * Adds a task to the list
     * @param t task to be added to the list
     */
    public String addTask(Task t) {
        list.add(t);
        length += 1;
        return ("Got it. I've added this task: \n" + t + "\nNow you have " + length + " tasks in the list.");
    }


    /**
     * Loads a task to the list (silent addTask)
     * @param t task to be added to the list
     */
    public void loadTask(Task t) {
        list.add(t);
        length += 1;
    }


    /**
     * Deletes a task from the list
     * @param index index of the task to be deleted
     */
    public String deleteTask(int index) {
        Task t = list.get(index);
        list.remove(index);
        length -= 1;
        return ("Noted. I've removed this task: \n" + t + "\nNow you have " + length + " tasks in the list.");
    }


    /**
     * Finds all the task with keyword
     */
     public String find(String keyword) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list: \n");
        for(int i = 0; i < list.size(); i++) {
            int index = i + 1;
            if (list.get(i).getTaskDesc().contains(keyword)) {
                sb.append( index + ". " + list.get(i) + "\n");
            }
        }
        if (sb.length() == 0) {
            return "There are no matching tasks.";
        }
        return (sb.toString());
    }

    // /**
    //  * Checks that the description provided by the user is not empty
    //  * @param length
    //  * @param error_length
    //  * @param task
    //  * @throws DukeException
    //  */
    // static void validate(Integer length, Integer error_length, String task) throws DukeException {
    //     if (length == error_length) {
    //         throw new DukeException("☹ OOPS!!! The description of a " + task + " cannot be empty.");
    //     }
    // }

    // /**
    //  * Method that parses the user input
    //  * @param userInput input given by the user
    //  * @throws DukeException
    //  */
    // public void parser(String userInput) throws DukeException {
    //     if (userInput.equals("list")) {
    //         System.out.println(" Here are the tasks in your list:");
    //         for (int i = 0; i < list.size(); i++) {
    //             System.out.println(String.valueOf(i + 1) + "." + list.get(i).toString());
    //         }
    //     }
    //     else if (userInput.startsWith("mark")) {
    //         Matcher matcher = Pattern.compile("\\d+$").matcher(userInput);
    //         if (matcher.find()) {
    //             int lastInteger = Integer.parseInt(matcher.group()) - 1;
    //             this.list.get(lastInteger).setDone();
    //             System.out.println("I've marked this task as done: \n" + list.get(lastInteger));
    //         }
    //     }
    //     else if (userInput.startsWith("unmark")) {
    //         Matcher matcher = Pattern.compile("\\d+$").matcher(userInput);
    //         if (matcher.find()) {
    //             int lastInteger = Integer.parseInt(matcher.group()) - 1;
    //             list.get(lastInteger).setUndone();
    //             System.out.println("OK, I've marked this task as not done yet:");
    //             System.out.println(list.get(lastInteger).toString());

    //         }
    //     }
    //     else if (userInput.startsWith("delete")) {
    //         Matcher matcher = Pattern.compile("\\d+$").matcher(userInput);
    //         if (matcher.find()) {
    //             int lastInteger = Integer.parseInt(matcher.group()) - 1;
    //             System.out.println("Noted. I've removed this task:");
    //             System.out.println(list.get(lastInteger).toString());
    //             System.out.println("Now you have " + String.valueOf(list.size() - 1) + " tasks in the list.");
    //             list.remove(lastInteger);
    //         }
    //     }
    //     else if (userInput.startsWith("find")) {
    //         validate(userInput.length(), 4, "find");
    //         int idx = userInput.indexOf(" ");
    //         Integer count = 1;
    //         String findName = userInput.substring(idx + 1);
    //         System.out.println("Here are the matching tasks in your list:");
    //         for (Task t : list) {
    //             if (t.description.contains(findName)) {
    //                 System.out.println(count.toString() + "." + t);
    //                 count++;
    //             }
    //         }
    //     }
    //     else {
    //         Task task;
    //         if (userInput.startsWith("todo")) {
    //             validate(userInput.length(), 4, "todo");
    //             int idx = userInput.indexOf(" ");
    //             String taskName = userInput.substring(idx + 1);
    //             task = new Todo(taskName, false);
    //         }
    //         else if (userInput.startsWith("deadline")) {
    //             validate(userInput.length(), 8, "deadline");
    //             int idx = userInput.indexOf(" ");
    //             int by_idx = userInput.indexOf("/by");
    //             String taskName = userInput.substring(idx + 1, by_idx);
    //             String modifier = userInput.substring(by_idx + 4);
    //             LocalDateTime byDate = LocalDateTime.parse(modifier, inputFormatter);
    //             task = new Deadline(taskName, byDate, false);
    //         }
    //         else if (userInput.startsWith("event")) {
    //             validate(userInput.length(), 5, "event");
    //             int idx = userInput.indexOf(" ");
    //             int from_idx = userInput.indexOf("/from");
    //             int to_idx = userInput.indexOf("/to");
    //             String taskName = userInput.substring(idx + 1, from_idx);
    //             String fromDate = userInput.substring(from_idx + 6, to_idx - 1);
    //             String toDate = userInput.substring(to_idx + 4);
    //             LocalDateTime startDate = LocalDateTime.parse(fromDate, inputFormatter);
    //             LocalDateTime endDate = LocalDateTime.parse(toDate, inputFormatter);
    //             task = new Event(taskName, startDate, endDate, false);
    //         }
    //         else {
    //             throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    //         }
    //         addTask(task);
    //         System.out.println("Got it. I've added this task:");
    //         System.out.println(task.toString());
    //         System.out.println("Now you have " + String.valueOf(list.size()) + " tasks in the list.");
    //     }
    // }

    /**
     * Prints out all the tasks in the list
     * @return all the tasks in the list
     */
    public String printList() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");

        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            sb.append(index + ". " + list.get(i) + "\n");
        }
        return (sb.toString());
    }

    /**
     * returns the ArrayList stored
     * @return ArrayList
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Set a specific task at an index to be done
     * @param i the index of the task
     * @return String indicating that the task has been set to done
     */
    public String setDone(int i) {
        this.list.get(i).setDone();
        return ("I've marked this task as done: \n" + list.get(i));
    }

}
