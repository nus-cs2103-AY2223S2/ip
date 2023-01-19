import java.util.ArrayList;
public class ToDoList {
    private ArrayList<Task> list;

    public ToDoList() {
        this.list = new ArrayList<>();
    }

    public String add(Task t) {
        list.add(t);
        int size = list.size();
        String reply = String.format("Sure no problem. I've added this task:\n\t%s\nNow you have %d task%s in the list",
                t.toString(), size, size == 1 ? "" : "s");
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

//    public String mark(int index) {
//        int size = list.size();
//        if (index >= size || index < 1) {
//            return "Hey just to let you know, you gave me an invalid number to mark!\nType list to see what's in the list.";
//        }
//        return list.get(index - 1).markAsDone();
//    }
//
//    public String unmark(int index) {
//        int size = list.size();
//        if (index >= size || index < 1) {
//            return "Hey just to let you know, you gave me an invalid number to unmark!\nType list to see what's in the list.";
//        }
//        return list.get(index - 1).markNotDone();
//    }

    public String changeState(int index, String action) {
        int size = list.size();
        if (index - 1 >= size || index < 1) {
            return String.format("Sorry, you gave me an invalid number to %s!\nType list to see what's in the list", action);
        }
        if (action.equals("mark")) {
            return list.get(index - 1).markAsDone();
        } else if (action.equals("unmark")) {
            return list.get(index - 1).markNotDone();
        } else {
            return "Invalid action was inputted";
        }
    }
}
