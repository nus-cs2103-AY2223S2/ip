import java.util.*;
public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static void reply() {
        String str = sc.nextLine();
        if(str.equals("bye")) {
            System.out.println("Bye mortal, I will get back to destroying" +
                    " galaxies");
        }
        else {
            System.out.println(str);
            reply();
        }
    }

    public static void main(String[] args) {
        String welcome_msg = "Hello my name is Thanos, my hobbies are helping people maintain their shedule and " +
                             "destroying galaxyies.";
        System.out.println(welcome_msg);
        reply();

    }
}
