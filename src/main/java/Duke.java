import java.util.ArrayList;
import java.util.*;

public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
    }
    public static void main(String[] args) {

        System.out.println("Hello! I am Munchmunch hehe :)");
        System.out.println("How may I help you?");
        System.out.println("__________________________");
        ArrayList<Task> tasks = new ArrayList<>();
        Boolean exit = true;
        while (exit) {

            Scanner text = new Scanner(System.in);
            String word = text.nextLine();
            // check for specific words : "mark" and "unmark"
            String[] words = word.split(" ");


            if (word.equals("bye")) {
                System.out.println("See ya champ! Enjoy your day!");
                exit = false;
            } else if (word.equals("list")) {
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println("Here are the tasks in your list boy:");
                    System.out.println((i+1) + "." + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description);
                }
            } else if (words[0].equals("mark")) {
                // converting string to integer
                int i = Integer.parseInt(words[1]) - 1;
                tasks.get(i).isDone = true;
                System.out.println("Dubzzzz! I've marked this task as done:");
                System.out.println("  " + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description);
            } else if (words[0].equals("unmark")) {
                // converting string to integer
                int i = Integer.parseInt(words[1]) - 1;
                tasks.get(i).isDone = false;
                System.out.println("Aites cool, I've marked this task as not done yet:");
                System.out.println("  " + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description);
            } else {
                Task str = new Task(word);
                tasks.add(str);
                System.out.println("Added: " + str.description);
            }
            System.out.println("__________________________");
        }
    }
}
