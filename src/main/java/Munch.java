import AddTasks.Task;
import Exceptions.IncompleteInputException;
import Exceptions.InvalidInputException;
import Exceptions.MunchException;
import munch.Storage;
import munch.TaskList;
import munch.Ui;
import java.util.ArrayList;
import java.util.*;


public class Munch {

    private static Ui ui;
    private Storage storage;
    static ArrayList<Task> tasks = new ArrayList<>();

    public Munch(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = Storage.load(tasks, filePath);
    }

    public static void main(String[] args) throws MunchException {

        ui.welcomeMessage();
        String filePath = "src/main/java/data/SavedTaskList.txt";
        new Munch(filePath);

        Boolean exit = true;
        Scanner text = new Scanner(System.in);
        while (exit) {
            try {
                String word = text.nextLine();
                String[] words = word.split(" ");

                if (word.equals("bye")) {
                    Ui.exitMessage();
                    Storage.save(tasks, filePath);
                    exit = false;

                } else if (word.equals("list")) {
                    ui.listMessage();
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                } else if (words[0].equals("mark") || words[0].equals("unmark")) {
                    int i = Integer.parseInt(words[1]) - 1;
                    tasks.get(i).wording(words[0]);

                } else if (words[0].equals("delete")) {
                    int i = Integer.parseInt(words[1]) - 1;
                    TaskList.deleteTask(tasks, i);

                } else if (words[0].equals("todo")) {
                    TaskList.addTodoTask(tasks, word);

                } else if (words[0].equals("deadline")) {
                    TaskList.addDeadlineTask(tasks, word);

                } else if (words[0].equals("event")) {
                    TaskList.addEventTask(tasks, word);

                } else {
                    throw new InvalidInputException();
                }
                ui.divider();
                Storage.save(tasks, filePath);
            } catch (IncompleteInputException | InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        text.close();
    }


}
