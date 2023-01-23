import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            String by = s.substring(index + 5).strip();
            if (index == 0 || desc.isEmpty()) {
                throw new DukeMissingDescriptionException();
            }

            Deadlines deadline = null;

            try {
                LocalDateTime localBy = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                deadline = new Deadlines(desc, localBy);
            } catch (DateTimeParseException dateTimeParseException) {
                System.out.println(dateTimeParseException.getMessage());
                deadline = new Deadlines(desc, by);
            } finally {
                list.add(deadline);
                output += "\t   " + deadline;
            }

            break;
        }

        case Events: {
            if (s.isBlank()) {
                throw new DukeMissingDescriptionException();
            }
            int fromIndex = s.indexOf(" /from ");
            int toIndex = s.indexOf(" /to ");
            if (fromIndex == -1 ||
                    toIndex == -1 ||
                    toIndex < fromIndex + 7 ||
                    s.substring(fromIndex + 7, toIndex).isBlank() ||
                    s.substring(toIndex + 5).isBlank()) {
                throw new DukeMissingEventDateException();
            }
            String desc = s.substring(0, fromIndex).strip();
            String from = s.substring(fromIndex + 7, toIndex).strip();
            String to = s.substring(toIndex + 5).strip();

            if (fromIndex == 0 || toIndex == 0 || desc.isEmpty()) {
                throw new DukeMissingDescriptionException();
            }

            Events event = null;
            try {
                LocalDateTime localFrom = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                LocalDateTime localTo = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                event = new Events(desc, localFrom, localTo);
            } catch (DateTimeParseException dateTimeParseException) {
                event = new Events(desc, from, to);
            } finally {
                list.add(event);
                output += "\t   " + event;
            }
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
