import Exceptions.EmptyCommandException;
import Exceptions.EmptyArgumentException;
import Exceptions.InvalidCommandException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) throws EmptyCommandException, InvalidCommandException, EmptyArgumentException {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        ArrayList<Task> arrL = new ArrayList<>();

        Scanner bucky = new Scanner(System. in);

        while (true) {
            String str = bucky.nextLine();

            if (str.equals("")) { throw new EmptyCommandException();}

            String s[] = str.split(" ", 2);
            String firstWord = s[0];

            if (str.equals("bye")) {
                System.out.println ("Bye. Hope to see you again soon!");
                break;
            } else if (str.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0 ; i < arrL.size() ; i++) {
                    System.out.println((i+1) + ". " + arrL.get(i));
                }
            } else if (firstWord.equals("mark")) {
                if (s.length <= 1) { throw new EmptyArgumentException();}
                int num = Integer.parseInt(s[1]) - 1;
                arrL.get(num).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + arrL.get(num));
            } else if (firstWord.equals("unmark")) {
                if (s.length <= 1) { throw new EmptyArgumentException();}
                int num = Integer.parseInt(s[1]) - 1;
                arrL.get(num).markAsDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + arrL.get(num));
            } else if (firstWord.equals("delete")) {
                if (s.length <= 1) { throw new EmptyArgumentException();}
                int num = Integer.parseInt(s[1]) - 1;
                Task t = arrL.remove(num);
                System.out.println("Noted. I've removed this task:\n" + t +
                        "\nNow you have " + arrL.size() + " tasks in the list.");
            } else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")){
                if (s.length <= 1) { throw new EmptyArgumentException();}
                if (firstWord.equals("todo")) {
                    arrL.add(new ToDo(s[1]));
                }
                else if (firstWord.equals("deadline")) {
                    String st[] = s[1].split(" /by ", 2);
                    arrL.add(new Deadline(st[0], st[1]));

                } else if (firstWord.equals("event")) {
                    String st[] = s[1].split(" /from ", 2);
                    String stt[] = st[1].split(" /to ", 2);
                    arrL.add(new Event(st[0], stt[0], stt[1]));
                }
                System.out.println("Got it. I've added this task:\n" + arrL.get(arrL.size()-1)
                    + "\nNow you have " + arrL.size() + " tasks in the list.");
            } else {
                throw new InvalidCommandException();
            }
        }

    }
}
