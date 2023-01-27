package duke;
import java.util.ArrayList;
//import duke.DukeException;

public class TaskList {
    ArrayList<Task> list = new ArrayList<>();

    public TaskList(ArrayList<String> input) {
        for (String item : input) {
            list.add(Parser.parseFile(item));
        }
    }

    public TaskList() {

    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void Bye() {
        System.out.println("Bye. Hope to see you again soon! ^_^");
        System.exit(0);
    }

    public void printList() {
        if (list.size() == 0) {
            System.out.println("No tasks in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + "." + list.get(i - 1));
            }
        }
    }

    public void mark(int index) throws taskNotExist {
        if (index > list.size() || index < 1) {
            throw new taskNotExist();
        }
        list.get(index - 1).setIsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index - 1));
    }

    public void unmark(int index) throws taskNotExist {
        if (index > list.size() || index < 1) {
            throw new taskNotExist();
        }
        list.get(index - 1).revertIsDone();
        System.out.println("Nice! I've marked this task as not done:");
        System.out.println(list.get(index - 1));
    }

    public void todo(String desc) {
        Todo todo = new Todo(desc);
        list.add(todo);
        System.out.println("Got it. I've added this task:\n" + todo);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void deadline(String desc, TimeConvertor time) {
        Deadline deadline = new Deadline(desc, time);
        list.add(deadline);
        System.out.println("Got it. I've added this task:\n" + deadline);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void event(String desc, TimeConvertor from, TimeConvertor to) {
        Event event = new Event(desc, from, to);
        list.add(event);
        System.out.println("Got it. I've added this task:\n" + event);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void delete(int index) throws taskNotExist {
        if (index > list.size() || index < 1) {
            throw new taskNotExist();
        }
        System.out.println("Got it. I've remove this task:\n" + list.remove(index - 1));
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * To find tasks with specific key word.
     *
     * @param searchKey Search key for finding the task.
     */
    public void find (String searchKey) {
        boolean isFind = false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getTaskDes().indexOf(searchKey.trim()) != -1) {
                System.out.println((i + 1) + ". " + list.get(i));
                isFind = true;
            }
        }
        if(!isFind) {
            System.out.println("No result found.");
        }
    }

    /**
     * Check if there is deadline on a specific date.
     * Need check keyword.
     * Format: check deadline /2019-10-15.
     *
     * @param checkDeadline The date.
     */
    public void check(String checkDeadline) {
        boolean ifDeadlineExist = false;
        for (int i = 0; i < list.size(); i++) {
            Task currT = list.get(i);
            if (currT instanceof Deadline) {
                Deadline dTask = (Deadline) currT;
                if (dTask.getDeadline().equals(checkDeadline)) {
                    ifDeadlineExist = true;
                    System.out.println((i + 1) + "." + dTask);
                }
            }
        }
        if (!ifDeadlineExist) {
            System.out.println("No deadline found on " + checkDeadline);
        }
    }
}
