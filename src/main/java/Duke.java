import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Duke {
    public static void main(String[] args) throws IOException {

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] word = br.readLine().split(" ");
        ArrayList<Task> lst = new ArrayList<>();

        while (!word[0].equals("bye")) {
            if (word[0].equals("list")) {
                int curr = 1;
                Iterator<Task> iter = lst.iterator();
                while (iter.hasNext()) {
                    System.out.println(curr + " " + iter.next());
                    curr++;
                }
                word = br.readLine().split(" ");
            } else if (word[0].equals("mark")) {
                Task t = lst.get(Integer.parseInt(word[1]) - 1);
                t.isDone = true;
                System.out.println("Task has been marked as done:\n " + t);
                word = br.readLine().split(" ");
            } else if (word[0].equals("unmark")) {
                Task t = lst.get(Integer.parseInt(word[1]) - 1);
                t.isDone = false;
                System.out.println("Task has been marked as not done:\n " + t);
                word = br.readLine().split(" ");
            } else {
                System.out.println("Added task: " + word[0]);
                lst.add(new Task(word[0]));
                word = br.readLine().split(" ");
            }
        }
        System.out.println("Duke: Goodbye");
    }
}

class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return isDone
                ? "[X] " + this.name
                : "[ ] " + this.name;
    }
}
