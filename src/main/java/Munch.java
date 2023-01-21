import java.util.ArrayList;
import java.util.*;

public class Munch {

    public static void main(String[] args) throws MunchException {

        System.out.println("Hello! I am Munchmunch hehe :)");
        System.out.println("How may I help you?");
        System.out.println("__________________________");
        ArrayList<Task> tasks = new ArrayList<>();
        Boolean exit = true;
        int count = 0;
        while (exit) {
            try {
                Scanner text = new Scanner(System.in);
                String word = text.nextLine();
                // check for specific words : "mark" and "unmark"
                String[] words = word.split(" ");


                // exit program
                if (word.equals("bye")) {
                    System.out.println("See ya champ! Enjoy your day!");
                    exit = false;

                    // generate list of tasks
                } else if (word.equals("list")) {
                    System.out.println("Here are the tasks in your list mah dude:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                } else if (words[0].equals("mark") || words[0].equals("unmark")) {
                    int i = Integer.parseInt(words[1]) - 1;
                    tasks.get(i).wording(words[0]);

                } else if (words[0].equals("todo")) {
                    String separator = "todo";
                    int sepPos = word.indexOf(separator);
                    String str = word.substring(sepPos + separator.length());
                    if (str.length() != 0) {
                        Todo todos = new Todo(str);
                        tasks.add(todos);
                        System.out.println("Aites, I've added this task for you :)");
                        System.out.println(todos);
                        count++;
                        System.out.println("Now you have " + count + " task(s) in the list ~~");
                    } else {
                        throw new IncompleteInputException();
                    }

                } else if (words[0].equals("deadline")) {
                    if (word.contains("deadline") && word.contains("/by")){
                        String separator1 = "deadline";
                        String separator2 = "/by";
                        int sepPos1 = word.indexOf(separator1);
                        int sepPos2 = word.indexOf(separator2);
                        String str = word.substring(sepPos1 + separator1.length(), sepPos2);
                        System.out.println(str.length());
                        String time = word.substring(sepPos2 + separator2.length());
                        Deadlines deadline = new Deadlines(str, time);
                        tasks.add(deadline);
                        System.out.println("Aites, I've added this task for you :)");
                        System.out.println(deadline);
                        count++;
                        System.out.println("Now you have " + count + " task(s) in the list ~~");
                    } else {
                        throw new IncompleteInputException();
                    }

                } else if (words[0].equals("event")) {
                    if (word.contains("event") && word.contains("/from") && word.contains("/to")){
                        String separator1 = "event";
                        String separator2 = "/from";
                        String separator3 = "/to";
                        int sepPos1 = word.indexOf(separator1);
                        int sepPos2 = word.indexOf(separator2);
                        int sepPos3 = word.indexOf(separator3);
                        String str = word.substring(sepPos1 + separator1.length(), sepPos2);
                        String from = word.substring(sepPos2 + separator2.length(), sepPos3);
                        String to = word.substring(sepPos3 + separator3.length());
                        Events event = new Events(str, from, to);
                        tasks.add(event);
                        System.out.println("Aites, I've added this task for you :)");
                        System.out.println(event);
                        count++;
                        System.out.println("Now you have " + count + " task(s) in the list ~~");
                    } else {
                        throw new IncompleteInputException();
                    }

                } else {
                    throw new InvalidInputException();
                }
                System.out.println("__________________________");
            } catch (IncompleteInputException | InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
