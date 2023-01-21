import java.util.*;
import java.util.ArrayList;
public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<ATask> lst = new ArrayList<ATask>();
    public static void add_to_list(String str) {
        lst.add(new ATask(str, false));
        System.out.println("added: " + str);
    }

    public static void print_list() {
        for(int i = 0; i < lst.size(); i++) {
            if(lst.get(i).status) {
                System.out.println((i+1) + ". " + "[X]" + lst.get(i).task_name);
            }
            else {
                System.out.println((i+1) + ". " + "[ ]" + lst.get(i).task_name);
            }
        }
    }

    public static void mark(ATask tsk) {
        tsk.status = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tsk.task_name);
    }

    public static void unmark(ATask tsk) {
        tsk.status = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + tsk.task_name);
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
        else {
            add_to_list(str);
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