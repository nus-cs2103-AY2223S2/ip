import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskList {
    private LinkedList<Task> tasks;
    private File dataFile;

    public TaskList(File dataFile) {
        this.dataFile = dataFile;
        this.tasks = new LinkedList<>();
    }

    public Task interpretLine(String str) {
        String[] directives = str.split("\\|");
        return TaskFactory.parseLine(directives);
    }

    public void readFile(Scanner sc) {
        while (sc.hasNextLine()) {
            this.tasks.add(interpretLine(sc.nextLine()));
        }
    }

    public void initialize() {
        try {
            this.dataFile.createNewFile();
            Scanner sc = new Scanner(this.dataFile);
            readFile(sc);
        } catch (IOException e) {
            Responses.printMessage("Error detected, proceeding to generate new save file");
        }
    }

    private void updateFile() {
        try {
            FileWriter writer = new FileWriter(String.valueOf(this.dataFile));
            writer.write("");
            for (int i = 0; i < this.tasks.size(); i++) {
                writer.append(this.tasks.get(i).dataFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Responses.printMessage("ALERT! Unable to overwrite data, input is not saved!");
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        updateFile();
        String message = "I have added " + task.getTaskName() + " to my memory\n";
        message = message + "          " + task.toString() + "\n";
        message = message + "        You have " + tasks.size() + " tasks in the list";
        Responses.printMessage(message);
    }

    public void deleteTask(String number) throws InvalidTaskException {
        if (number.equals("all")) {
            Responses.printMessage("All tasks on the list have been cleared");
            this.tasks.clear();
        } else {
            try {
                int value = Integer.parseInt(number);
                if (number.length() < 1 || value > this.tasks.size() || value <= 0) {
                    throw new InvalidTaskException();
                } else {
                    Task task = this.tasks.remove(value - 1);
                    String message = "I have removed " + task.getTaskName() + " from my memory\n";
                    message = message + "          " + task.toString() + "\n";
                    message = message + "        You have " + tasks.size() + " tasks in the list";
                    Responses.printMessage(message);
                }
            } catch (NumberFormatException e){
                throw new InvalidTaskException();
            }
        }
        updateFile();
    }

    public void markDone(int number) {
        this.tasks.get(number - 1).mark();
        updateFile();
        Responses.printMessage("Understood, I have marked the task as done:\n"
                + "        "
                + this.tasks.get(number - 1).toString());
    }
    public void markUndone(int number) {
        this.tasks.get(number - 1).unmark();
        updateFile();
        Responses.printMessage("Understood, I have marked the task as undone:\n"
                + "        "
                + this.tasks.get(number - 1).toString());
    }

    public void getList() {
        System.out.println(Responses.LINE);
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("        "
                    + (i + 1)
                    + "."
                    + this.tasks.get(i).toString());
        }
        System.out.println("        I have " + this.tasks.size() + " tasks in my memory");
    }
}
