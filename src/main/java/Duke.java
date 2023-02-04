import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {

    private static final String SAVE_PATH = "./taskList.txt";

    private static ArrayList<Task> loadData(String filePath) throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (file.createNewFile()){
            return taskList;
        }
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            Task task;
            String[] line = sc.nextLine().split("\\|");
            if (line[0].equals("T")) {
                task = new Todo(line[2].strip());
            } else if (line[0].equals("D")) {
                task = new Deadline(line[2], line[3]);
            } else {
                task = new Event(line[2], line[3], line[4]);
            }

            if (line[1] == "1") {
                task.mark();
            }

            taskList.add(task);
        }
        sc.close();
        return taskList;
    }

    public static void storeData(ArrayList<Task> t) throws IOException {
        File file = new File(SAVE_PATH);
        if (file.createNewFile()) {
            System.out.println("Created new file taskList.txt");
        } 
        FileWriter fw = new FileWriter(file);
        String taskString = "";
        for (int i = 0; i < t.size(); i++){
            taskString += t.get(i).getFileDesc() + "\n";
        }
        fw.write(taskString);
        fw.close();
    }
    public static void main(String[] args) throws IOException, DukeException {

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            ArrayList<Task> lst = loadData(SAVE_PATH);
            int count = 0 + lst.size();
            String[] word = br.readLine().strip().split(" ",2);

            while (!word[0].equals("bye")) {
                try {
                    if (word[0].equals("list")) {
                        int curr = 1;
                        Iterator<Task> iter = lst.iterator();
                        while (iter.hasNext()) {
                            System.out.println(curr + " " + iter.next());
                            curr++;
                        }
                        word = br.readLine().strip().split(" ",2);
                    } else if (word[0].equals("mark")) {
                        if (word.length == 1) {
                            throw new DukeException("Mark needs a number.");
                        }
                        if (Integer.parseInt(word[1]) > count) {
                            throw new DukeException("Invalid task.");
                        }
                        Task t = lst.get(Integer.parseInt(word[1]) - 1);
                        t.isDone = true;
                        System.out.println("Task has been marked as done:\n " + t);
                        word = br.readLine().split(" ",2);
                    } else if (word[0].equals("unmark")) {
                        if (word.length == 1) {
                            throw new DukeException("Unmark needs a number.");
                        }
                        if (Integer.parseInt(word[1]) > count) {
                            throw new DukeException("Invalid task.");
                        }
                        Task t = lst.get(Integer.parseInt(word[1]) - 1);
                        t.isDone = false;
                        System.out.println("Task has been marked as not done:\n " + t);
                        word = br.readLine().split(" ",2);
                    } else if (word[0].equals("todo")) {
                        if (word.length == 1) {
                            throw new DukeException("todo needs a description");
                        }
                        Task t = new Todo(word[1].strip());
                        lst.add(t);
                        count++;
                        System.out.println("Added new todo:\n  " + t + "\nNumber of tasks: " + count);
                        word = br.readLine().strip().split(" ",2);
                    } else if (word[0].equals("deadline")) {
                        if (word.length == 1 || !word[1].contains("/by")) {
                            throw new DukeException("Deadline needs a /by.");
                        }
                        String[] tempWord = word[1].strip().split("/by ");
                        if (tempWord.length == 1) {
                            throw new DukeException("/by needs a date/time.");
                        }
                        try {
                            Task t = new Deadline(tempWord[0].strip(), tempWord[1].strip());
                            lst.add(t);
                            count++;
                            System.out.println("Added new deadline:\n  " + t + "\nNumber of tasks: " + count);
                            word = br.readLine().strip().split(" ",2);
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Date after /by needs to be in format yyyy-mm-dd");
                        }
                    } else if (word[0].equals("event")) {
                        if (word.length == 1 || !word[1].contains("/from") || !word[1].contains("/to") ) {
                            throw new DukeException("Event needs a /from and /to.");
                        }
                        String[] tempWord = word[1].split("/");
                        String[] from = tempWord[1].split(" ",2);
                        String[] to = tempWord[2].split(" ",2);
                        if (from.length == 1 || to.length == 1) {
                            throw new DukeException("/from and /to needs a date/time.");
                        }
                        try {
                            Task t = new Event(tempWord[0].strip(), from[1].strip(), to[1].strip());
                            lst.add(t);
                            count++;
                            System.out.println("Added new event:\n  " + t + "\nNumber of tasks: " + count);
                            word = br.readLine().strip().split(" ",2);
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Date after /from and /to needs to be in format yyyy-mm-dd");
                        }
                    } else if (word[0].equals("delete")) {
                        if (word.length == 1) {
                            throw new DukeException("Delete needs a number.");
                        }
                        if (Integer.parseInt(word[1]) > count) {
                            throw new DukeException("Invalid task.");
                        }
                        Task t = lst.remove(Integer.parseInt(word[1]) - 1);
                        count--;
                        System.out.println("Deleted task:\n  " + t + "\nNumber of tasks: " + count);
                        word = br.readLine().strip().split(" ",2);
                    } else {
                        throw new DukeException("Sorry I do not understand the command");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    word = br.readLine().strip().split(" ",2);
                }
            }
            storeData(lst);
            System.out.println("Duke: Goodbye");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}