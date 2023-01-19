import java.util.ArrayList;
public class ToDoList {
    private ArrayList<Task> list;

    public ToDoList() {
        this.list = new ArrayList<>();
    }

    public String add(String item) {
        list.add(new Task(item));
        String reply = "Added: " + item;
        return reply;
    }

    public String listItems() {
        StringBuilder str = new StringBuilder();
        int count = 1;
        for (Task t : list) {
            str.append(String.format("%d. %s\n", count, t.toString()));
            count++;
        }
        str.deleteCharAt(str.length() - 1);
        return String.format("Here are the tasks in your list:\n%s", str.toString());
    }

    public String mark(int index) {
        int size = list.size();
        if (index >= size || index < 1) {
            return "Hey just to let you know, you gave me an invalid number to mark!\nType list to see what's in the list.";
        }
        return list.get(index - 1).markAsDone();
    }

    public String unmark(int index) {
        int size = list.size();
        if (index >= size || index < 1) {
            return "Hey just to let you know, you gave me an invalid number to unmark!\nType list to see what's in the list.";
        }
        return list.get(index - 1).markNotDone();
    }
}
