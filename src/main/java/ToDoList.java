import java.util.ArrayList;
public class ToDoList {
    private ArrayList<String> list = new ArrayList<>();

    public ToDoList() {}
    public String add(String item) {
        list.add(item);
        String reply = "Added: " + item;
        return reply;
    }

    public String listItems() {
        StringBuilder str = new StringBuilder();
        int count = 1;
        for (String item: list) {
            str.append(String.format("%d. %s\n", count, item));
            count++;
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

}
