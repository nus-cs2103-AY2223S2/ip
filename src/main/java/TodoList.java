import java.util.ArrayList;

public class TodoList {
    ArrayList<String> todo_list;
    ArrayList<String> mark_list;

    public TodoList() {
        this.todo_list = new ArrayList<>(100);
        this.mark_list = new ArrayList<>(100);
    }

    public void add(String task) {
        todo_list.add(task);
        mark_list.add(" ");
    }

    public void mark(int index) {
        int todo_list_length = todo_list.size();
        if (index < 0 || index >= todo_list_length) {
            return;
        }
        mark_list.set(index - 1, "X");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("[X] %s\n", todo_list.get(index-1)));
    }

    public void unmark(int index) {
        int todo_list_length = todo_list.size();
        if (index < 0 || index >= todo_list_length) {
            return;
        }
        mark_list.set(index - 1, " ");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format("[ ] %s\n", todo_list.get(index-1)));
    }

    @Override
    public String toString() {
        String shown_list = "";
        int todo_list_length = todo_list.size();
        for (int i = 1; i <= todo_list_length; i++) {
            String marked = mark_list.get(i - 1);
            shown_list += String.format("%d.[" + marked +"] %s\n", i, todo_list.get(i-1));
        }
        return shown_list;
    }
}
