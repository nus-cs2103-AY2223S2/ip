import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public final class Duke {
    private final static String joiner(String[] args, int from, int to) {
        return String.join(" ", Arrays.copyOfRange(args, from, to));
    }

    private final static List<Task> loadTasks() {
        if (Files.exists(Paths.get("data,dat"))) {
            try (
                final FileInputStream ifstream = new FileInputStream("data.dat");
                final ObjectInputStream inStream = new ObjectInputStream(ifstream);
            ) {
                @SuppressWarnings("unchecked")
                List<Task> tasks = (List<Task>) inStream.readObject();
                return tasks;
            } catch (IOException e) {
                System.out.println("Oh no an IOException occurred while we were trying to load your tasks!");
                System.out.print(e);
            } catch (ClassNotFoundException e) {
                System.out.println("Oh no your data file does not contain tasks!");
                System.out.print(e);
            }
        }

        return new ArrayList<>();
    }

    public final static void main(String[] vargs) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final List<Task> tasks = loadTasks();
        System.out.format("You currently have %d tasks!\n", tasks.size());

        final Consumer<Task> addTask = (task) -> {
            tasks.add(task);
            System.out.format("Added %s to the list!\nYou now have %d tasks\n", task.toString(), tasks.size());
        };

        final Function<String, Integer> intParser = (value) -> {
            int index = Integer.parseInt(value);
            if (index < 1 || index > tasks.size()) {
                throw new NumberFormatException();
            }
            return index;
        };

        final Map<String, Consumer<String[]>> funcMap = Map.of(
        "bye", (args) -> {
            System.out.println("Ok bye bye!");
            System.exit(0);
        },
        "list", (args) -> {
            if (tasks.size() == 0) {
                System.out.println("No stored tasks!");
            } else {
                System.out.format("A total of %d tasks\n", tasks.size());
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.format("%d. %s\n", i + 1, tasks.get(i).toString());
                }
            }
        },
        "mark", (args) -> {
            try {
                if (args.length == 1) {
                    System.out.println("Needed an index for mark");
                    return;
                }

                int index = intParser.apply(args[1]);
                tasks.get(index - 1).setDone(true);
                System.out.format("Marked this as done!\n%s\n", tasks.get(index - 1).toString());
            } catch (NumberFormatException e) {
                System.out.println("Invalid index!\n");
            }
        },
        "unmark", (args) -> {
            if (args.length == 1) {
                System.out.println("Needed an index for unmark");
                return;
            }
            try {
                int index = intParser.apply(args[1]);
                tasks.get(index - 1).setDone(false);
                System.out.format("Marked this as not done!\n%s\n", tasks.get(index - 1).toString());
            } catch (NumberFormatException e) {
                System.out.println("Invalid index!");
            }
        },
        "todo", (args) -> {
            if (args.length == 1) {
                System.out.println("Expected a task!");
                return;
            }

            String taskStr = joiner(args, 1, args.length);
            Task task = new ToDo(taskStr);
            addTask.accept(task);
        },
        "deadline", (args) -> {
            int index = -1;
            for(int i = 1; i < args.length; i++) {
                if (args[i].equalsIgnoreCase("/by")) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Expected a /by directive!");
                return;
            } else if (index == 1) {
                System.out.println("Expected a task!");
                return;
            } else if (index == args.length - 1) {
                System.out.println("Expected a time after /by!");
                return;
            }

            String taskStr = joiner(args, 1, index);
            String time = joiner(args, index + 1, args.length);
            Task task = new Deadline(taskStr, time);
            addTask.accept(task);
        },
        "event", (args) -> {
            int fromIndex = -1, toIndex = -1;
            for(int i = 1; i < args.length; i++) {
                if (args[i].equalsIgnoreCase("/from")) {
                    fromIndex = i;
                } else if (args[i].equalsIgnoreCase("/to")) {
                    toIndex = i;
                }

                if (fromIndex != -1 && toIndex != -1) break;
            }

            if (fromIndex == -1) {
                // No /from provided
                System.out.println("Expected a /from directive!");
                return;
            } else if (toIndex == -1) {
                // No /to provided
                System.out.println("Expected a /to directive!");
                return;
            } else if (fromIndex == 1) {
                // From was the first keyword after event
                System.out.println("Expected a task!");
                return;
            } else if (fromIndex > toIndex) {
                System.out.println("Expected /from to come before /to!");
                return;
            } else if (toIndex == args.length - 1) {
                System.out.println("Expected a time after /to!");
                return;
            } else if (toIndex - fromIndex == 1) {
                System.out.println("Expected a time after /from!");
                return;
            }

            String taskStr = joiner(args, 1, fromIndex);
            String fromStr = joiner(args, fromIndex + 1, toIndex);
            String toStr = joiner(args, toIndex + 1, args.length);
        
            Task task = new Event(taskStr, fromStr, toStr);
            addTask.accept(task);
        },
        "delete", (args) -> {
          try {
                if (args.length == 1) {
                    System.out.println("Needed an index for delete");
                    return;
                }

                int index = intParser.apply(args[1]);
                Task removed = tasks.remove(index - 1);
                System.out.format("Removed this task!\n%s\n", removed.toString());
            } catch (NumberFormatException e) {
                System.out.println("Invalid index!");
            }
        }, 
        "save", (args) -> {
            if (tasks.size() < 1) return;

            try (
                final FileOutputStream ofstream = new FileOutputStream("data.dat");
                final ObjectOutputStream outStream = new ObjectOutputStream(ofstream);
            ) {
                outStream.writeObject(tasks);
            } catch (IOException e) {
                System.out.println("Oh no an IOException occurred while we were trying to save your data!");
                System.out.print(e);
            }
        });

        while (true) {
            String input = reader.readLine();
            String[] tokens = input.split(" ");

            if (funcMap.containsKey(tokens[0])) {
                funcMap.get(tokens[0]).accept(tokens);
            } else {
                System.out.format("Unknown command '%s'\n", input);
            }
            
        }
    }
}
