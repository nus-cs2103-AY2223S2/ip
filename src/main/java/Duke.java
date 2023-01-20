import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public void markDone() {
            this.isDone = true;
        }

        public void markUndone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return ("[" + this.getStatusIcon() + "] " + this.description);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        ArrayList<Task> listOfThings = new ArrayList<Task>();
        boolean loop = true;
        while (loop) {
            Scanner echoScanner = new Scanner(System.in);
            String msg = echoScanner.nextLine();
            String firstWord = "";
            Integer secondInt = 0;
            String bye = "bye";
            String showList = "list";
            String mark = "mark";
            String unmark = "unmark";
            if (msg.contains(" ")) {
                firstWord = msg.substring(0, msg.indexOf(" "));
                try {
                    secondInt = Integer
                            .parseInt(msg.substring(msg.indexOf(" ") + 1, msg.length()));
                } catch (Exception ex) {

                }
            }
            if (bye.equalsIgnoreCase(msg)) {
                System.out.println("Bye. Hope to see Byou again soon!");
                loop = false;
            } else if (showList.equalsIgnoreCase(msg)) {
                for (int i = 0; i < listOfThings.size(); i++) {
                    System.out.println(i + 1 + ". " + listOfThings.get(i));
                }
            } else if (mark.equalsIgnoreCase(firstWord) && secondInt != 0) {
                Task thisTask = listOfThings.get(secondInt - 1);
                thisTask.markDone();
                listOfThings.set(secondInt - 1, thisTask);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(thisTask);
            } else if (unmark.equalsIgnoreCase(firstWord) && secondInt != 0) {
                Task thisTask = listOfThings.get(secondInt - 1);
                thisTask.markUndone();
                listOfThings.set(secondInt - 1, thisTask);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(thisTask);
            } else {
                Task newTask = new Task(msg);
                listOfThings.add(newTask);
                System.out.println("added " + msg);
            }
        }
    }
}
