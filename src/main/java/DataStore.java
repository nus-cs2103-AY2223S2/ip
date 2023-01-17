import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DataStore {
    private final List<Task> tasks;
    private static final String FILENAME = "tasks.txt";

    public DataStore(List<Task> tasks) {
        this.tasks = tasks;
    }

    public DataStore() throws IOException, ClassNotFoundException, ClassCastException {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = DataStore.load();
        } catch (FileNotFoundException e) {
            DataStore.save(tasks);
        }
        this.tasks = tasks;
    }

    public String[] stringify() {
        if (this.tasks.size() == 0) return new String[]{"No tasks found."};
        String[] outputs = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            outputs[i] = this.tasks.get(i).toString(i+1);
        }
        return outputs;
    }

    public String[] add(String[] argument) {
        Task newTask;
        switch (argument[0]) {
            case "/todo":
                newTask = new ToDo(argument[1]);
                break;
            case "/deadline":
                String[] deadlineArgs = argument[1].split(" /by ",2);
                newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                break;
            case "/event":
                String[] eventArgs = Duke.split(argument[1], new String[]{" /from ", " /to "});
                newTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                break;
            default:
                throw new IllegalArgumentException("Invalid task type: " + argument[0]);
        }
        this.tasks.add(newTask);
        this.save();
        return new String[]{"added: " + newTask};
    }

    public String[] mark(String[] strings) {
        List<Integer> indexes = new ArrayList<>();
        Consumer<Integer> mark = ind -> {
            this.tasks.get(ind).mark();
            indexes.add(ind);
        };
        this.find(strings, mark);
        this.save();
        if (indexes.size() == 0) {
            throw new IllegalArgumentException("No tasks found.");
        } else if (indexes.size() == 1) {
            Task task = this.tasks.get(indexes.get(0));
            return new String[]{(task.isDone ? "marked: " : "unmarked: ") + task.toString(indexes.get(0) + 1)};
        } else {
            List<String> outputs = indexes.stream()
                    .map(i -> "\t" + this.tasks.get(i).toString(i+1))
                    .collect(Collectors.toList());
            outputs.add(0, "marked:");
            return outputs.toArray(new String[0]);
        }
    }

    public String[] delete(String[] strings) {
        List<Integer> indexes = new ArrayList<>();
        Consumer<Integer> delete = ind -> {
            if (this.tasks.size() <= ind) {
                throw new IllegalArgumentException("Task not found: " + ind);
            }
            indexes.add(ind);
        };
        this.find(strings, delete);
        if (indexes.size() == 0) {
            throw new IllegalArgumentException("No tasks found.");
        } else if (indexes.size() == 1) {
            Task rmTask = this.tasks.remove((int) indexes.get(0));
            this.save();
            return new String[]{"deleted: " + rmTask.toString(indexes.get(0) + 1)};
        } else {
            indexes.sort(Collections.reverseOrder());
            List<String> outputs = new ArrayList<>();
            for (int i : indexes) {
                outputs.add("\t" + this.tasks.remove(i).toString(i+1));
            }
            this.save();
            Collections.reverse(outputs);
            outputs.add(0, "deleted:");
            return outputs.toArray(new String[0]);
        }
    }

    private void find(String[] argument, Consumer<Integer> consumer) {
        try{
            String[] inds = argument[0].split("\\s");
            for (String s : inds) {
                int ind = Integer.parseInt(s) - 1;
                consumer.accept(ind);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid index.");
        }
    }

    public void save() {
        DataStore.save(this.tasks);
    }

    public static void save(List<Task> tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(DataStore.FILENAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            System.out.println("\t[ERROR] While saving, the following error occurred: \n\t" + e);
        }
    }

    public static List<Task> load() throws IOException, ClassNotFoundException, ClassCastException {
        FileInputStream fin = new FileInputStream(DataStore.FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fin);
        @SuppressWarnings("unchecked")
        List<Task> tasks = (ArrayList<Task>) ois.readObject();
        fin.close();
        return tasks;
    }
}