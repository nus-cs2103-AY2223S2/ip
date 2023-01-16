import java.util.ArrayList;

public class ToDoList {
    ArrayList<String> list = new ArrayList<>();

    public ToDoList() {}

    public String add(String s) {
        list.add(s);
        return String.format("\t added: %s", s);
    }

    public String list() {
        StringBuilder output = new StringBuilder();
        int index = 1;
        for (String task : list) {
            output.append(String.format("\t%d. %s\n", index, task));
            index++;
        }

        return output.substring(0, output.length() - 1);
    }
}
