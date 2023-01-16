import java.util.ArrayList;

public class ToDoList {
    ArrayList<Task> list = new ArrayList<>();

    public ToDoList() {}

    public String add(String s) {
        list.add(new Task(s));
        return String.format("\t added: %s", s);
    }

    public String mark(int num) {
        return list.get(num).mark();
    }

    public String unMark(int num) {
        return list.get(num).unMark();
    }

    public String list() {
        StringBuilder output = new StringBuilder();
        output.append("\t Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : list) {
            output.append(String.format("\t %d.%s\n", index, task.toString()));
            index++;
        }

        return output.substring(0, output.length() - 1);
    }
}
