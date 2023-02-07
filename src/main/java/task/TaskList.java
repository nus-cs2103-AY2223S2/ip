package task;

import support.DateConverter;

import java.util.ArrayList;

/**
 * Abstraction for data structure and operations used to maintain to do list.
 */
public class TaskList {
    private ArrayList<Task> records = new ArrayList<>();

    // default constructor
    public String add(Task task) {
        records.add(task);
        return "B: " + task.toString() + " has been added";
    }

    public String delete(int x) {
        Task temp = records.remove(x-1);
        return "B: " + temp.toString() + " has been removed";
    }

    public String print() {
        String s = "";
        int n = this.records.size();

        if (n == 0) {
            return "B: There are no missions!";
        }

        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                s = s + (i + 1) + ". " + records.get(i).toString() + "\n";
            } else {
                s = s + (i + 1) + ". " + records.get(i).toString();
            }
        }

        return s;
    }

    public String mark(int x) {
        return records.get(x-1).setComplete();
    }

    public String unmark(int x) {
        return records.get(x-1).setIncomplete();
    }

    public int size() {
        return this.records.size();
    }

    /**
     * Finds and prints out missions that contain the keyword specified by user.
     * @param s keyword that user inputs
     */
    public String find(String s) {
        String ans = "These are what I found:";

        int x = 1;
        int n = this.records.size();

        for (int i = 0; i < n; i++) {
            if (this.records.get(i).contains(s)) {
                ans +="\n" + x + ". " + this.records.get(i).toString();
                x++;
            }
        }

        if (x == 1) {
            ans = "No missions contain this keyword.";
        }

        return ans;
    }

    /**
     * Reads tasks from previous record as string and adds to current list.
     * @param str Output format of task
     */
    public void addFromFile(String str) {
        char tasktype = str.charAt(4);
        char marked = str.charAt(7);
        String name = str.substring(10);

        switch (tasktype) {
            case 'T':
                this.records.add(new Todo(name));
                break;

            case 'D':
                int idx = str.indexOf("(by:");
                String dueDate = DateConverter.dateFormatter(str.substring(idx + 5));
                this.records.add(new Deadline("deadline " + str.substring(10, idx-1), dueDate));
                break;

            case 'E':
                int startIdx = str.indexOf("(from: ");
                int endIdx = str.indexOf("to: ");
                String startDate = DateConverter.dateFormatter(str.substring(startIdx + 7));
                String endDate = DateConverter.dateFormatter(str.substring(endIdx + 4));
                this.records.add(new Event("event " + str.substring(10, startIdx), startDate, endDate));
        }

        if (marked == 'X') {
            int last = this.records.size();
            mark(last);
        }
    }
}
