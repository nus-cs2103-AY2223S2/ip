import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list = new ArrayList<>();

    public TaskList() {}

    public String add(TaskType type, String s) throws DukeException {

        String output = "\t Got it. I've added this task:\n";
        switch (type) {
            case ToDos:
                if (s.isBlank()) {
                    throw new DukeMissingDescriptionException();
                }
                ToDos todo = new ToDos(s);
                list.add(todo);
                output += "\t   " + todo;
                break;

            case Deadlines: {
                if (s.isBlank()) {
                    throw new DukeMissingDescriptionException();
                }
                int index = s.indexOf(" /by ");
                if (index == -1 || s.substring(index + 5)
                        .isBlank()) {
                    throw new DukeMissingDeadlineException();
                }
                String desc = s.substring(0, index).strip();
                if (index == 0 || desc.isEmpty()) {
                    throw new DukeMissingDescriptionException();
                }

                Deadlines deadline = new Deadlines(desc,
                        s.substring(index + 5).strip());
                list.add(deadline);
                output += "\t   " + deadline;
                break;
            }

            case Events: {
                if (s.isBlank()) {
                    throw new DukeMissingDescriptionException();
                }
                int from = s.indexOf(" /from ");
                int to = s.indexOf(" /to ");
                if (from == -1 ||
                        to == -1 ||
                        to < from + 7 ||
                        s.substring(from + 7, to).isBlank() ||
                        s.substring(to + 5).isBlank()) {
                    throw new DukeMissingEventDateException();
                }
                String desc = s.substring(0, from).strip();

                if (from == 0 || to == 0 || desc.isEmpty()) {
                    throw new DukeMissingDescriptionException();
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
    }

    public String mark(String s) throws DukeException {
        int num = TaskList.stringToInt(s);

        if (num < -1 || num >= list.size()) {
            throw new DukeTaskNumberOutOfRangeException();
        }
        return list.get(num).mark();
    }

    public String unMark(String s) throws DukeException {
        int num = TaskList.stringToInt(s);

        if (num < -1 || num >= list.size()) {
            throw new DukeTaskNumberOutOfRangeException();
        }
        return list.get(num).unMark();
    }

    public String delete(String s) throws DukeException {
        int num = TaskList.stringToInt(s);

        String output = "\t Noted. I've removed this task:\n";
        if (num < -1 || num >= list.size()) {
            throw new DukeTaskNumberOutOfRangeException();
        }
        Task removed = list.remove(num);
        return String.format("%s\t   %s\n\t Now you have %d tasks in the list.", output, removed, list.size());
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

    private static int stringToInt(String s) throws DukeInvalidTaskNumberException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new DukeInvalidTaskNumberException();
        }
    }
}
