import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.lang.StringBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TaskList {
    private ArrayList<Task> list;
    private int length;

    public TaskList(ArrayList<String> tasks) {
        this.list = new ArrayList<>();
        for (String s: tasks) {
            length++;
            String[] array = s.split("\\|");

            switch (array[0]) {
                case "T": {
                    this.addTask(new ToDos(array[2], Boolean.parseBoolean(array[1])));
                    break;
                }
                case "D": {
                    this.addTask(new Deadline(array[2], array[3], Boolean.parseBoolean(array[1])));
                    break;
                }
                case "E": {
                    this.addTask(new Events(array[2], array[3], array[4], Boolean.parseBoolean(array[1])));
                    break;
                }

            }
        }

    }

    /**
     * return size of the array
     */
    public int getLength() {
        return length;
    }


    /**
     * Adds a task to the list
     * @param t task to be added to the list
     */
    public void addTask(Task t) {
        list.add(t);
        length += 1;
    }

    /**
     * Deletes a task at specified index
     * @param i index of task to be deleted
     * @return String showing which task has been removed
     */
    public String deleteTask(int i) {
        length -= 1;
        return ("Noted. I have removed this task: \n" + list.remove(i));
    }

    static void validate(Integer length, Integer error_length, String task) throws DukeException {
        if (length == error_length) {
            throw new DukeException("☹ OOPS!!! The description of a " + task + " cannot be empty.");
        }
    }

    public void parser(String userInput) throws DukeException {
        if (userInput.equals("list")) {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(String.valueOf(i + 1) + "." + list.get(i).toString());
            }
        }
        else if (userInput.startsWith("mark")) {
            Matcher matcher = Pattern.compile("\\d+$").matcher(userInput);
            if (matcher.find()) {
                int lastInteger = Integer.parseInt(matcher.group()) - 1;
                this.list.get(lastInteger).setDone();
                System.out.println("I've marked this task as done: \n" + list.get(lastInteger));
            }
        }
        else if (userInput.startsWith("unmark")) {
            Matcher matcher = Pattern.compile("\\d+$").matcher(userInput);
            if (matcher.find()) {
                int lastInteger = Integer.parseInt(matcher.group()) - 1;
                list.get(lastInteger).setUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(lastInteger).toString());

            }
        }
        else if (userInput.startsWith("delete")) {
            Matcher matcher = Pattern.compile("\\d+$").matcher(userInput);
            if (matcher.find()) {
                int lastInteger = Integer.parseInt(matcher.group()) - 1;
                System.out.println("Noted. I've removed this task:");
                System.out.println(list.get(lastInteger).toString());
                System.out.println("Now you have " + String.valueOf(list.size() - 1) + " tasks in the list.");
                list.remove(lastInteger);
            }
        }
        else {
            Task task;
            if (userInput.startsWith("todo")) {
                validate(userInput.length(), 4, "todo");
                int idx = userInput.indexOf(" ");
                String taskName = userInput.substring(idx + 1);
                task = new ToDos(taskName, false);
            }
            else if (userInput.startsWith("deadline")) {
                validate(userInput.length(), 8, "deadline");
                int idx = userInput.indexOf(" ");
                int by_idx = userInput.indexOf("/by");
                String taskName = userInput.substring(idx + 1, by_idx);
                String modifier = userInput.substring(by_idx + 3);
                task = new Deadline(taskName, modifier, false);
            }
            else if (userInput.startsWith("event")) {
                validate(userInput.length(), 5, "event");
                int idx = userInput.indexOf(" ");
                int from_idx = userInput.indexOf("/from");
                int to_idx = userInput.indexOf("/to");
                String taskName = userInput.substring(idx + 1, from_idx);
                String fromDate = userInput.substring(from_idx + 5, to_idx);
                String toDate = userInput.substring(to_idx + 3);
                task = new Events(taskName, fromDate, toDate, false);
            }
            else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            addTask(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + String.valueOf(list.size()) + " tasks in the list.");
        }
    }

    /**
     * Prints out all the tasks in the list
     * @return all the tasks in the list
     */
    public String printList() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");

        for(int i = 0; i < list.size(); i++) {
            int index = i + 1;
            sb.append( index + ". " + list.get(i) + "\n");
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
