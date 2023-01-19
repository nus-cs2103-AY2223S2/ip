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
        System.out.printf("Added to list: %s\n", s.printDescription());
    }

    public void markTaskDone(int num) {
        if (this.l.size() > num) {
            this.l.get(num).markTaskDone();
        } else {
            System.out.println("Hey HEY HEY, you going too far >:[");
        }

    }

    public void markTaskUndone(int num) {
        if (this.l.size() > num) {
            this.l.get(num).markTaskUndone();
        } else {
            System.out.println("Hey HEY HEY, you going too far >:[");
        }
    }
}


