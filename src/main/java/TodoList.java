import java.util.ArrayList;

public class TodoList {
    ArrayList<String> todo_list;
    public TodoList() {
        this.todo_list = new ArrayList<>(100);
    }

    public void add(String task) {
        todo_list.add(task);
    }

    @Override
    public String toString() {
        String shown_list = "";
        int todo_list_length = todo_list.size();
        for (int i = 1; i <= todo_list_length; i++) {
            shown_list += String.format("%d. %s\n", i, todo_list.get(i-1));
        }
        return shown_list;
    }
}
