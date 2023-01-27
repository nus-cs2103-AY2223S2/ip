package duke.tasks;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> taskList;

    public Tasks() {
        this.taskList = new ArrayList<Task>(100);
    }

    public void printList() {
        if (taskList.size() == 0) {
            System.out.println("You haven't added anything 0_0?");
        } else {
            int count = 1;
            for (Task task : taskList) {
                System.out.println(count + ". " + task.printTask());
                count++;
            }
        }
    }

    public void addToList(Task tasks, boolean silent) {
        taskList.add(tasks);
        if (!silent) {
            System.out.printf("Added to list: %s\n", tasks.printTask());
            System.out.printf("Now you've got %d task(s) in your bag, CHOP CHOP GET THEM DONE.\n", taskList.size());
        }
    }

    public void markTaskDone(int num, boolean silent) {
        if (withinRange(num)) {
            this.taskList.get(num).markTaskDone(silent);
        } else {
            System.out.println("Hey HEY HEY, that's not within range >:[");
        }
    }

    public void markTaskUndone(int num) {
        if (withinRange(num)) {
            this.taskList.get(num).markTaskUndone();
        } else {
            System.out.println("Hey HEY HEY, that's not within range >:[");
        }
    }

    public void deleteTask(int num) {
        if (withinRange(num)) {
            System.out.println("Into the bin it goes! This is now deleted!\n" + this.taskList.get(num).printTask());
            this.taskList.remove(num);
            System.out.printf("%d task(s) left to go :/\n", this.taskList.size());
        } else {
            System.out.println("Hey HEY HEY, that's not within range >:[");
        }
    }

    public boolean withinRange(int num) {
        return this.taskList.size() > num && num >= 0;
    }

    public String formatForFile() {
        if (taskList.size() == 0) {
            return "";
        } else {
            StringBuilder output = new StringBuilder();
            for (Task task : taskList) {
                output.append(task.formatForFile()).append("\n");
            }
            return output.toString();
        }
    }

    public void filterByDate(String dateOnly) {
        if (taskList.size() == 0) {
            System.out.println("Nothing~");
        } else {
            MyDate date = new MyDate(dateOnly);
            System.out.println("Deadlines due or events ongoing on " + date.printDateTime());
            int count = 1;
            for (Task task : taskList) {
                if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    if (d.isDeadLine(date)) {
                        System.out.println(count + ". " + task.printTask());
                        count++;
                    }
                } else if (task instanceof Event) {
                    Event e = (Event) task;
                    if (e.liesBetween(date)) {
                        System.out.println(count + ". " + task.printTask());
                        count++;
                    }
                }
            }
        }
    }
}
