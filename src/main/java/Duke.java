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
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("Here are the tasks in your list boy:");
                    System.out.println((i + 1) + "." + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description);
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

//public class Duke {
//
//    public static void main(String[] args) {
//
//        System.out.println("Hello! I am Munchmunch hehe :)");
//        System.out.println("How may I help you?");
//        System.out.println("__________________________");
//        ArrayList<Task> tasks = new ArrayList<>();
//        Boolean exit = true;
//        int count = 0;
//        while (exit) {
//
//            Scanner text = new Scanner(System.in);
//            String word = text.nextLine();
//            // check for specific words : "mark" and "unmark"
//            String[] words = word.split(" ");
//
//            // exit program
//            if (word.equals("bye")) {
//                System.out.println("See ya champ! Enjoy your day!");
//                exit = false;
//
//                // generate list of tasks
//            } else if (word.equals("list")) {
//                System.out.println("Here are the tasks in your list mah dude:");
//                for(int i = 0; i < tasks.size(); i++) {
//                    System.out.println((i+1) + "." + tasks.get(i).toString());
//                }
//
//                // mark a task
//            } else if (words[0].equals("mark")) {
//                // converting string to integer
//                int i = Integer.parseInt(words[1]) - 1;
//                tasks.get(i).markAsDone();
//                System.out.println("Dubzzzz! I've marked this task as done:");
//                System.out.println("  " + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description);
//
//                // unmark a task
//            } else if (words[0].equals("unmark")) {
//                // converting string to integer
//                int i = Integer.parseInt(words[1]) - 1;
//                tasks.get(i).markAsUndone();
//                System.out.println("Aites cool, I've marked this task as not done yet:");
//                System.out.println("  " + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description);
//            } else {
//                if (words[0].equals("todo")) {
//                    String separator = "todo";
//                    int sepPos = word.indexOf(separator);
//                    String str = word.substring(sepPos + separator.length(), word.length());
//                    Todo todos = new Todo(str);
//                    tasks.add(todos);
//                    System.out.println("Aites, I've added this task for you :)");
//                    System.out.println(todos.toString());
//
//                } else if (words[0].equals("deadline")) {
//                    String separator1 = "deadline";
//                    String separator2 ="/by";
//                    int sepPos1 = word.indexOf(separator1);
//                    int sepPos2 = word.indexOf(separator2);
//                    String str = word.substring(sepPos1 + separator1.length(), sepPos2);
//                    String time = word.substring(sepPos2 + separator2.length());
//                    Deadlines deadline = new Deadlines(str, time);
//                    tasks.add(deadline);
//                    System.out.println("Aites, I've added this task for you :)");
//                    System.out.println(deadline.toString());
//
//                } else if (words[0].equals("event")) {
//                    String separator1 = "event";
//                    String separator2 = "/from";
//                    String separator3 = "/to";
//                    int sepPos1 = word.indexOf(separator1);
//                    int sepPos2 = word.indexOf(separator2);
//                    int sepPos3 = word.indexOf(separator3);
//                    String str = word.substring(sepPos1 + separator1.length(), sepPos2);
//                    String from = word.substring(sepPos2 + separator2.length(), sepPos3);
//                    String to = word.substring(sepPos3 + separator3.length());
//                    Events event = new Events(str, from, to);
//                    tasks.add(event);
//                    System.out.println("Aites, I've added this task for you :)");
//                    System.out.println(event.toString());
//                }
//                count++;
//                System.out.println("Now you have " + count + " task in the list ~~");
//            }
//            System.out.println("__________________________");
//        }
//    }
//}
