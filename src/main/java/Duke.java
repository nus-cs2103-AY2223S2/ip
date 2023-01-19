import java.util.*;
public class Duke {
    public static boolean offBot = false;
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        //CommandCreator creator = new CommandCreator();
        User user = new User();
        while(!offBot) {
            Scanner sc= new Scanner(System.in); //System.in is a standard input stream
            try {
                CommandManager.createCommand(sc.nextLine());
                CommandManager.executeQueue(user);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
