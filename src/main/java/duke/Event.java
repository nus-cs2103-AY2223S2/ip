package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import util.*;

public class Event extends Task {

    protected String duration;
    static String divider = "    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══";

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + duration +")";
    }

    public static void createEventTask(ArrayList<Task> array, String[] splitInput) {
        if (splitInput.length == 1 || splitInput[1].equals("")){
            try {
                throw new DukeException("event");
            } catch (Exception e) {
                System.out.println(divider);
                System.out.println(e.toString());
                System.out.println(divider);
            }
        } else {
            for(int j=1; j< splitInput.length; j++){
                if(splitInput[j].equals("/from")){
                    for (int k=1; k< j-1; k++){
                        splitInput[1] = splitInput[1] + " " + splitInput[k+1];
                    }
                    for(int i = j + 1; i < splitInput.length; i++) {
                        splitInput[i] = splitInput[i].replace("/to", "to:");
                    }
                    for (int l=splitInput.length-1; l > j +1; l--){
                        splitInput[splitInput.length-1] = splitInput[l-1]+" "+splitInput[splitInput.length-1];
                    }
                } else {
                    splitInput[j] = splitInput[j];
                }
            }
               
            String duration = splitInput[splitInput.length-1];
            String desc = splitInput[1];
            Event e = new Event(desc, duration);
            array.add(e);
            System.out.println(divider);
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + e.toString());
            System.out.println("     Now you have " + array.size() + " tasks in the list.");
            System.out.println(divider);
        }
        
    }
    public static boolean isDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate ld = null;
        try {
            ld = LocalDate.parse(date, formatter);
            System.out.println(ld);
        } catch (DateTimeParseException e) {
            System.out.println("Date " + date + " is not a date.");
            return false;
    
        }
    
        return true;
    }
}