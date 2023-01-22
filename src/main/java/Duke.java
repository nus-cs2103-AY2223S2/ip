import commands.*;
import exceptions.DukeException;
import tasks.ITask;
import uitilties.UserInterface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static uitilties.UserInterface.receptor;

public class Duke {
    static ArrayList<ITask> tasks = new ArrayList<>();

    public static void main(String[] args) {
//        String inputDate = "2019-12-01 2100";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HHmm");
        String inputDate = "2019-12-01 2100";
        Date date;
        DateFormat outputformat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss aa");

        String output;
        try{
            //Converting the input String to Date
            date= df.parse(inputDate);
            //Changing the format of date and storing it in String
            output = outputformat.format(date);
            //Displaying the date
            System.out.println(output);
        }catch(ParseException pe){
            pe.printStackTrace();
        }
//        String regex = "(\\d{4}-\\d{2}-\\d{2} \\d{4})";
//        Matcher m = Pattern.compile(regex).matcher(inputDate);
////        LocalDateTime date = LocalDateTime.parse(inputDate);
//        System.out.println(date);
//        System.out.println(date.plusHours(9));
//        System.out.println(date);
//        if (m.find()) {
//            LocalDateTime date = LocalDateTime.parse(inputDate);
//            System.out.println(date);
//            System.out.println(date.plusHours(9));
//            System.out.println(date);
//
//        } else {
//            System.out.println("date not found");
//        }

        UserInterface.greeting();
        boolean done = false;
        while (!done)
            try {
                ICommand cmd = receptor(tasks);
                done = cmd.run();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                UserInterface.Speak("Type any command to continue...");
            }

    }
}
