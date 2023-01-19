import java.util.*;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke What can I do for you?");
        Scanner sc= new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        boolean exit = false;
        while(!exit) {
            String str = sc.nextLine();
            exit = echo(str,list);
        }

    }
    public static boolean echo(String str, ArrayList<String> list) {
        if(str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        } else if(str.equals("list")) {
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                System.out.println(  index + "." + list.get(i));
            }
            return false;
        } else {
            list.add(str);
            System.out.println( "added:" + str);
            return false;
        }
    }
}
