package task;

import java.util.ArrayList;

import support.DateConverter;

/**
 * Abstraction for data structure and operations used to maintain to do list.
 */
public class TaskList {
    private ArrayList<Task> records = new ArrayList<>();

    // default constructor

    /**
     * Adds a task to the arraylist
     * @param task to be added
     * @return message representing success
     */
    public String add(Task task) {
        records.add(task);
        return task.toString() + " has been added";
    }

    /**
     * Deletes a record from the arraylist
     * @param x index of number to be deleted (1-based)
     * @return string message representing success
     */
    public String delete(int x) {
        Task temp = records.remove(x - 1);
        return temp.toString() + " has been removed";
    }

    /**
     * Prints all tasks in the list
     * @return String representation of tasks to be printed
     */
    public String print() {
        String s = "";
        int n = this.records.size();

        if (n == 0) {
            return "There are no missions!";
        }

        for (int i = 0; i < n; i++) {
            s = appendToOutput(s, i);
        }

        return s;
    }

    /**
     * marks a task as completed
     * @param x index of task to modify
     * @return string representing success
     */
    public String mark(int x) {
        return records.get(x - 1).setComplete();
    }

    /**
     * marks a task as incomplete
     * @param x index of task to modify
     * @return string representing success
     */
    public String unmark(int x) {
        return records.get(x - 1).setIncomplete();
    }

    /**
     * Finds and prints out missions that contain the keyword specified by user.
     * @param s keyword that user inputs
     */
    public String find(String s) {
        String ans = "These are what I found:";

        int idx = 1;
        int n = this.records.size();

        for (int i = 0; i < n; i++) {
            if (containsKeyword(i, s)) {
                ans += appendFoundString(idx, i);
                idx++;
            }
        }

        if (idx == 1) {
            ans = "No missions contain this keyword.";
        }

        return ans;
    }

    /**
     * Reads tasks from previous record as string and adds to current list.
     * @param str Output format of task
     */
    public void addFromFile(String str) {
        char taskType = str.charAt(4);
        char marked = str.charAt(7);

        switch (taskType) {
        case 'T':
            addTodo(str);
            break;

        case 'D':
            addDeadline(str);
            break;

        case 'E':
            addEvent(str);
            break;

        default:
            System.out.println("This should have never happened");
        }

        if (marked == 'X') {
            int last = this.records.size();
            mark(last);
        }
    }

    private boolean containsKeyword(int x, String keyword) {
        if (this.records.get(x).contains(keyword)) {
            return true;
        }
        return false;
    }

    private String appendFoundString(int count, int idx) {
        return "\n" + count + ". " + this.records.get(idx).toString();
    }
    private String appendToOutput(String s, int idx) {
        if (idx == this.records.size() - 1) {
            s = s + (idx + 1) + ". " + records.get(idx).toString();
            return s;
        }
        s = s + (idx + 1) + ". " + records.get(idx).toString() + "\n";
        return s;
    }

    private void addTodo(String str) {
        this.records.add(new Todo("todo " + str.substring(10)));
    }

    private void addDeadline(String str) {
        int idx = str.indexOf("(by:");
        String task = str.substring(10, idx - 1);
        String dueDate = DateConverter.dateFormatter(str.substring(idx + 5));
        this.records.add(new Deadline("deadline " + task, dueDate));
    }

    private void addEvent(String str) {
        int startIdx = str.indexOf("(from: ");
        int endIdx = str.indexOf("to: ");
        String taskName = str.substring(10, startIdx);
        String startDate = DateConverter.dateFormatter(str.substring(startIdx + 7));
        String endDate = DateConverter.dateFormatter(str.substring(endIdx + 4));
        this.records.add(new Event("event " + taskName, startDate, endDate));
    }
}
