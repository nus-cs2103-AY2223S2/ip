import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;


public class Duke {
    public static void main(String[] args) throws IOException, DukeException {

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Task> lst = new ArrayList<>();
        int count = 0;
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
                        Task t = new Deadline(tempWord[0].strip(), tempWord[1].strip());
                        lst.add(t);
                        count++;
                        System.out.println("Added new deadline:\n  " + t + "\nNumber of tasks: " + count);
                        word = br.readLine().strip().split(" ",2);
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
                        Task t = new Event(tempWord[0].strip(), from[1].strip(), to[1].strip());
                        lst.add(t);
                        count++;
                        System.out.println("Added new event:\n  " + t + "\nNumber of tasks: " + count);
                        word = br.readLine().strip().split(" ",2);
                    } else {
                        throw new DukeException("Sorry I do not understand the command");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    word = br.readLine().strip().split(" ",2);
                }
            }
        System.out.println("Duke: Goodbye");
    }
}



