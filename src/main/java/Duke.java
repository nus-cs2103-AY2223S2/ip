import Exceptions.EmptyCommandException;
import Exceptions.EmptyArgumentException;
import Exceptions.InvalidCommandException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.util.Scanner;
public class Duke {
    public static void main(String[] args) throws EmptyCommandException, InvalidCommandException, EmptyArgumentException {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Task[] arr = new Task[100];
        int nextIndex = 0;

        while (true) {
            Scanner bucky = new Scanner(System. in);
            String str = bucky.nextLine();

            if (str.equals("")) { throw new EmptyCommandException();}

            String s[] = str.split(" ", 2);
            String firstWord = s[0];

            if (str.equals("bye")) {
                System.out.println ("Bye. Hope to see you again soon!");
                break;
            } else if (str.equals("list")) {
                for (int i = 0 ; i < nextIndex ; i++) {
                    System.out.println((i+1) + ". " + arr[i]);
                }
            } else if (firstWord.equals("mark")) {
                int num = Integer.parseInt(s[1]) - 1;
                arr[num].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + arr[num]);
            } else if (firstWord.equals("unmark")) {
                int num = Integer.parseInt(s[1]) - 1;
                arr[num].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + arr[num]);
            } else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")){
                if (s.length <= 1) {
                    throw new EmptyArgumentException();
                }
                if (firstWord.equals("todo")) {
                    arr[nextIndex++] = new ToDo(s[1]);
                }
                else if (firstWord.equals("deadline")) {
                    String st[] = s[1].split(" /by ", 2);
                    arr[nextIndex++] = new Deadline(st[0], st[1]);
                } else if (firstWord.equals("event")) {
                    String st[] = s[1].split(" /from ", 2);
                    String stt[] = st[1].split(" /to ", 2);
                    arr[nextIndex++] = new Event(st[0], stt[0], stt[1]);
                }
                System.out.println("Got it. I've added this task:\n" + arr[nextIndex-1]
                    + "\nNow you have " + nextIndex + " tasks in the list.");
            } else {
                throw new InvalidCommandException();
            }
        }

    }
}
