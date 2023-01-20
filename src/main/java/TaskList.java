import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>();
    private final String savePath = "data/Tasks.txt";
    private final File saveFile = new File(savePath);

    public TaskList() throws DukeException {
        this.loadSaveFile();
    }

    public String add(TaskType type, String s) throws DukeException {

        String output = "\t Got it. I've added this task:\n";
        switch (type) {
            case ToDos :
                if (s.isBlank()) {
                    throw new DukeMissingDescriptionException();
                }
                ToDos todo = new ToDos(s, false);
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

                Deadlines deadline = new Deadlines(desc, false, by);
                list.add(deadline);
                output += "\t   " + deadline;
                }
                break;
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

                Events event = new Events(s
                            .substring(0, fromIndex)
                            .strip(),
                        false, from, to);
                list.add(event);
                output += "\t   " + event;
                }
            break;
        }
        return String.format("%s\n\t Now you have %d tasks in the list.", output, list.size());
    }

    public String mark(String s) throws DukeInvalidTaskNumberException, DukeTaskNumberOutOfRangeException {
        int num = TaskList.stringToInt(s);

        if (num < -1 || num >= list.size()) {
            throw new DukeTaskNumberOutOfRangeException();
        }
        return list.get(num - 1).mark();
    }

    public String unMark(String s) throws DukeInvalidTaskNumberException, DukeTaskNumberOutOfRangeException {
        int num = TaskList.stringToInt(s);

        if (num < -1 || num >= list.size()) {
            throw new DukeTaskNumberOutOfRangeException();
        }
        return list.get(num - 1).unMark();
    }

    public String delete(String s) throws DukeInvalidTaskNumberException, DukeTaskNumberOutOfRangeException {
        int num = TaskList.stringToInt(s);

        String output = "\t Noted. I've removed this task:\n";
        if (num < -1 || num >= list.size()) {
            throw new DukeTaskNumberOutOfRangeException();
        }
        Task removed = list.remove(num - 1);
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

    public void writeToFile() throws DukeWriteException, DukeFileCreationException {
        saveFile.delete();
        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new DukeFileCreationException();
        }
        for (Task task : list) {
            task.writeToString(savePath);
        }
    }

    private static int stringToInt(String s) throws DukeInvalidTaskNumberException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new DukeInvalidTaskNumberException();
        }
    }

    private void loadSaveFile() throws DukeFileCreationException, DukeReadException {
        Scanner scanner;

        try {
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeFileCreationException();
        }

        try {
            scanner = new Scanner(saveFile);
        } catch (FileNotFoundException ignored) {
            throw new DukeReadException();
        }

        while (scanner.hasNext()) {
            String fn = scanner.next();
            String[] details = scanner.nextLine()
                    .strip()
                    .split("-");
            switch (fn) {
                case "todo":
                    list.add(new ToDos(details[0],
                        Boolean.parseBoolean(details[1])
                    ));
                    break;
                case "deadline":
                    list.add(new Deadlines(details[0],
                        Boolean.parseBoolean(details[1]),
                        details[2]
                    ));
                    break;
                case "event":
                    list.add(new Events(details[0],
                        Boolean.parseBoolean(details[1]),
                        details[2],
                        details[3]
                    ));
                    break;
            }
        }
    }

}
