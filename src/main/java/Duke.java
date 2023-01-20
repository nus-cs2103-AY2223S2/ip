import java.util.*;
import java.util.ArrayList;
public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<String> lst = new ArrayList<String>();
    public static void add_to_list(String str) {
        lst.add(str);
        System.out.println("added: " + str);
    }
    public static void print_list() {
        for(int i = 0; i < lst.size(); i++) {
            System.out.println((i+1) + ". " + lst.get(i));
        }
    }
    public static void reply() {
        String str = sc.nextLine();
        if(str.equals("bye")) {
            System.out.println("Bye mortal, I will get back to destroying" +
                    " galaxies");
        } else if(str.equals("list")) {
            print_list();
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