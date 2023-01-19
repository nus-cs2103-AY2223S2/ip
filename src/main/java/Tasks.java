import java.util.ArrayList;
import java.util.List;

public class Tasks {
    List<Task> l;

    public Tasks() {
        this.l = new ArrayList<Task>(100);
    }

    public void printList() {
        if (l.size() == 0) {
            System.out.println("You haven't added anything 0_0?");
        } else {
            int count = 1;
            for (Task task : l) {
                System.out.println(count + ". " + task.printTask());
                count++;
            }
        }
    }

    public void addToList(Task s) {
        l.add(s);
        System.out.printf("Added to list: %s\n", s.printTask());
        System.out.printf("Now you've got %d task(s) in your bag, CHOP CHOP GET THEM DONE.\n", l.size());
    }

    public void markTaskDone(int num) {
        if (withinRange(num)) {
            this.l.get(num).markTaskDone();
        } else {
            System.out.println("Hey HEY HEY, that's not within range >:[");
        }
    }

    public void markTaskUndone(int num) {
        if (withinRange(num)) {
            this.l.get(num).markTaskUndone();
        } else {
            System.out.println("Hey HEY HEY, that's not within range >:[");
        }
    }

    public void deleteTask(int num) {
        if (withinRange(num)) {
            System.out.println("Into the bin it goes! This is now deleted!\n" + this.l.get(num).printTask());
            this.l.remove(num);
            System.out.printf("%d task(s) left to go :/\n", this.l.size());
        } else {
            System.out.println("Hey HEY HEY, that's not within range >:[");
        }
    }

    public boolean withinRange(int num) {
        return this.l.size() > num && num >= 0;
    }
}


