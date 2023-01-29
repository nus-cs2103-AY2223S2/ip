import java.util.*;
import java.util.ArrayList;
public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Task> lst = new ArrayList<Task>();
    public static void add_to_list(String str) throws InvalidCommandException, NoDescriptionException{

        if((str.split(" ", 2).length == 1)) {
            throw new NoDescriptionException();
        }
        else if((str.split(" ", 2)[0]).equals("todo")) {

            lst.add(new Todo(str));
        }
        else if((str.split(" ", 2)[0]).equals("deadline")) {
            lst.add(new Deadline(str));
        }
        else if((str.split(" ", 2)[0]).equals("event")) {
            lst.add(new Event(str));
        }
        else {
            throw new InvalidCommandException();
        }
        int size = lst.size();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + lst.get(size-1).toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public static void print_list() {
        for(int i = 0; i < lst.size(); i++) {

            System.out.println((i+1) + ". " + lst.get(i).toString());
        }
    }

    public static void mark(Task tsk) {
        tsk.toggleTrue();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tsk.toString());
    }

    public static void unmark(Task tsk) {
        tsk.toggleFalse();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tsk.toString());
    }
    public static void reply() {
        String str = sc.nextLine();
        if(str.equals("bye")) {
            System.out.println("Bye mortal, I will get back to destroying" +
                    " galaxies");
        }
        else if(str.equals("list")) {
            print_list();
            reply();
        }
        else if((str.split(" ", 2)[0]).equals("mark")) {
            mark(lst.get(Integer.parseInt((str.split(" ", 2)[1])) - 1));
            reply();
        }
        else if((str.split(" ", 2)[0]).equals("unmark")) {
            unmark(lst.get(Integer.parseInt((str.split(" ", 2)[1])) - 1));
            reply();
        }
        else if((str.split(" ", 2)[0]).equals("delete")) {
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + lst.get(Integer.parseInt((str.split(" ", 2)[1])) - 1).toString());
            lst.remove(Integer.parseInt((str.split(" ", 2)[1])) - 1);
            System.out.println("Now you have " + lst.size() + " tasks in the list.");
            reply();
        }
        else {
            try {
                add_to_list(str);
            }
            catch(InvalidCommandException e) {
                System.out.println("the command is invalid");
            }
            catch(NoDescriptionException e) {
                System.out.println("the task needs to have a description");
            }
            reply();
        }
    }

    public static void main(String[] args) {
        String welcome_msg = "Hello my name is Thanos, my hobbies are helping people maintain their schedule and " +
                "destroying galaxies.";
        System.out.println(welcome_msg);
        reply();
    }
}