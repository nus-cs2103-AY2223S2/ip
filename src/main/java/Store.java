import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
//A CLASS THAT STORE THE TASKS
public class Store extends Commands {
    public Store(String str) {
        this.setCommandStorage(str);
    }
    @Override
    public void execute(User user){
        String content = this.getCommandStorage();
        Tasks task = null;
        if (content.matches("^deadline\\s.*$")) {
            String back = content.substring(9);
            String[] substrings = back.split("/");
            String date = substrings[1];
            task = new Deadline(substrings[0], date.substring(3));
        } else if (content.matches("^event\\s.*$")) {
            String back = content.substring(6);
            String[] substrings = back.split("/");
            String from = substrings[1].substring(5);
            String to = substrings[2].substring(3);
            task = new Event(substrings[0], from, to);
        } else if (content.matches("^todo\\s.*$")){
            String desc = content.substring(5);
            task = new Todo(desc);
        }
        user.addTask(task);
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + user.getTaskCount() + " tasks in the list.");
    }
}