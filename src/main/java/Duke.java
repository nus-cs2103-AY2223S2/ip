import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //try{
            //System.out.println(logo);
            //"todo \D+"
            //deadline .+/by \d{2}/\d{2}/\d{4}
            //event .+/from \d{2}/\d{2}/\d{4} /to \d{2}/\d{2}/\d{4}
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            while (true){
                Pattern todoPattern = Pattern.compile("todo \\D+");
                Pattern deadlinePattern = Pattern.compile("deadline .+/by \\d{2}/\\d{2}/\\d{4}");
                Pattern eventPattern = Pattern.compile("event .+/from \\d{2}/\\d{2}/\\d{4} /to \\d{2}/\\d{2}/\\d{4}");
                if (todoPattern.matcher(command).find()){
                    System.out.println("Todo Task added succesfully!");
                } else if (deadlinePattern.matcher(command).find()){
                    System.out.println("Deadline Task added succcesfully!");
                } else if (eventPattern.matcher(command).find()){
                    System.out.println("Event task added successfully!");
                } else {
                    System.out.println("Incorrect command given!");
                }
            }
        //} catch(Exception e){

        //}
    }


}
