import java.util.ArrayList;

public class ToDoList {
    ArrayList<Task> list = new ArrayList<>();

    public ToDoList() {}

    public String add(TaskType type, String s) {
        try {
            String output = "\t Got it. I've added this task:\n";
            switch (type) {
                case ToDos:
                    if (s.isBlank()) {
                        throw new DukeException("\t OOPS!!! The description of a todo cannot be empty.");
                    }

                    ToDos todo = new ToDos(s);

                    list.add(todo);
                    output += "\t   " + todo;
                    break;

                case Deadlines: {
                    if (s.isBlank()) {
                        throw new DukeException("\t OOPS!!! Please provide a description and a date/time");
                    }
                    int index = s.indexOf(" /by ");
                    if (index == -1 || s.substring(index + 5)
                            .isBlank()) {
                        throw new DukeException("\t OOPS!!! The date/time of a deadline cannot be empty.");
                    }
                    String desc = s.substring(0, index).strip();
                    if (index == 0 || desc.isEmpty()) {
                        throw new DukeException("\t OOPS!!! The description of a deadline cannot be empty.");
                    }


                    Deadlines deadline = new Deadlines(desc,
                            s.substring(index + 5).strip());
                    list.add(deadline);
                    output += "\t   " + deadline;
                    break;
                }

                case Events: {
                    if (s.isBlank()) {
                        throw new DukeException("\t OOPS!!! Please provide a description and a duration");
                    }
                    int from = s.indexOf(" /from ");
                    int to = s.indexOf(" /to ");
                    if (from == -1 ||
                            to == -1 ||
                            to < from + 7 ||
                            s.substring(from + 7, to).isBlank() ||
                            s.substring(to + 5).isBlank()) {
                        throw new DukeException("\t OOPS!!! The start/end date of an event cannot be empty.");
                    }
                    String desc = s.substring(0, from).strip();

                    if (from == 0 || to == 0 || desc.isEmpty()) {
                        throw new DukeException("\t OOPS!!! The description of an event cannot be empty.");
                    }

                    Events event = new Events(s.substring(0, from)
                            .strip(),
                            s.substring(from + 7, to).strip(),
                            s.substring(to + 5).strip());
                    list.add(event);
                    output += "\t   " + event;
                    break;
                }
            }
            return String.format("%s\n\t Now you have %d tasks in the list.", output, list.size());
        } catch (DukeException dukeException) {
            return dukeException.getMessage();
        }

    }

    public String mark(int num) {
        try {
            if (num < -1 || num >= list.size()) {
                throw new DukeException("\t OOPS!!! Task number out of range.");
            }
            return list.get(num).mark();
        } catch (DukeException dukeException) {
            return dukeException.getMessage();
        }
    }

    public String unMark(int num) {
        try {
            if (num < -1 || num >= list.size()) {
                throw new DukeException("\t OOPS!!! Task number out of range.");
            }
            return list.get(num).unMark();
        } catch (DukeException dukeException) {
            return dukeException.getMessage();
        }
    }

    public String delete(int num) {
        try {
            String output = "\t Noted. I've removed this task:\n";
            if (num < -1 || num >= list.size()) {
                throw new DukeException("\t OOPS!!! Task number out of range.");
            }
            Task removed = list.remove(num);
            return String.format("%s\t   %s\n\t Now you have %d tasks in the list.", output, removed, list.size());
        } catch (DukeException dukeException) {
            return dukeException.getMessage();
        }
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
