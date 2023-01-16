import java.util.ArrayList;

public class ToDoList {
    ArrayList<Task> list = new ArrayList<>();

    public ToDoList() {}

    public String add(TaskType type, String s) {
        String output = "\t Got it. I've added this task:\n";
        switch (type) {
            case ToDos:
                ToDos todo = new ToDos(s);
                list.add(todo);
                output += "\t   " + todo;
                break;
            case Deadlines:
                int index = s.indexOf(" /by ");
                Deadlines deadline = new Deadlines(s.substring(0, index),
                        s.substring(index + 5));
                list.add(deadline);
                output += "\t   " + deadline;
                break;
            case Events:
                int from = s.indexOf(" /from ");
                int to = s.indexOf(" /to ");
                Events event = new Events(s.substring(0, from),
                        s.substring(from + 7, to),
                        s.substring(to + 5));
                list.add(event);
                output += "\t   " + event;
                break;
        }
        return String.format("%s\n\t Now you have %d tasks in the list.", output, list.size());
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
            output.append(String.format("\t %d.%s\n",
                    index,
                    task.toString()));
            index++;
        }

        return output.substring(0, output.length() - 1);
    }
}
