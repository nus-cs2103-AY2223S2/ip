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

        String[] word = br.readLine().split(" ",2);
        ArrayList<Task> lst = new ArrayList<>();
        int count = 0;

        while (!word[0].equals("bye")) {
            if (word[0].equals("list")) {
                int curr = 1;
                Iterator<Task> iter = lst.iterator();
                while (iter.hasNext()) {
                    System.out.println(curr + " " + iter.next());
                    curr++;
                }
                word = br.readLine().split(" ",2);
            } else if (word[0].equals("mark")) {
                Task t = lst.get(Integer.parseInt(word[1]) - 1);
                t.isDone = true;
                System.out.println("Task has been marked as done:\n " + t);
                word = br.readLine().split(" ",2);
            } else if (word[0].equals("unmark")) {
                Task t = lst.get(Integer.parseInt(word[1]) - 1);
                t.isDone = false;
                System.out.println("Task has been marked as not done:\n " + t);
                word = br.readLine().split(" ",2);
            } else if (word[0].equals("todo")) {
                Task t = new Todo(word[1]);
                lst.add(t);
                count++;
                System.out.println("Added new todo:\n  " + t + "\nNumber of tasks: " + count);
                word = br.readLine().split(" ",2);
            } else if (word[0].equals("deadline")) {
                String[] tempWord = word[1].split("/by ");
                Task t = new Deadline(tempWord[0], tempWord[1].strip());
                lst.add(t);
                count++;
                System.out.println("Added new deadline:\n  " + t + "\nNumber of tasks: " + count);
                word = br.readLine().split(" ",2);
            } else if (word[0].equals("event")) {
                String[] tempWord = word[1].split("/");
                Task t = new Event(tempWord[0], tempWord[1].split(" ",2)[1].strip(), tempWord[2].split(" ")[1].strip());
                lst.add(t);
                count++;
                System.out.println("Added new event:\n  " + t + "\nNumber of tasks: " + count);
                word = br.readLine().split(" ",2);
            } else {
                System.out.println("Error");
                word = br.readLine().split(" ",2);
            }
        }
        System.out.println("Duke: Goodbye");
    }
}



