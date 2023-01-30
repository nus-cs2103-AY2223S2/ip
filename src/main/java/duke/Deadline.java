package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


import util.DukeException;

public class Deadline extends Task {

    protected String by;
    static String divider = "    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    public static void createDeadlineTask(ArrayList<Task> array, String[] splitInput) {
        if (splitInput.length == 1 || splitInput[1].equals("")){
            try {
                throw new DukeException("deadline");
            } catch (Exception e) {
                System.out.println(divider);
                System.out.println(e.toString());
                System.out.println(divider);
            }
        } else {
            for(int j=1; j< splitInput.length; j++){
                if(splitInput[j].equals("/by")){
                    for (int k=1; k< j-1; k++){
                        splitInput[1] = splitInput[1] + " " + splitInput[k+1];
                    }
                    for (int l=splitInput.length-1; l > j +1; l--){
                        splitInput[splitInput.length-1] = splitInput[l-1]+" "+splitInput[splitInput.length-1];
                    }
                } else {
                    splitInput[j] = splitInput[j];
                }
            }
               
            String date = splitInput[splitInput.length-1];
            String desc = splitInput[1];

            if(isDate(date)){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                LocalDate ld = LocalDate.parse(date, formatter);
                Deadline d = new Deadline(desc, ld);
                array.add(d);
                System.out.println(divider);
                System.out.println("     Got it. I've added this task:");
                System.out.println("     " + d.toString());
                System.out.println("     Now you have " + array.size() + " tasks in the list.");
                System.out.println(divider);
            } else {
                Deadline d = new Deadline(desc, date);
                array.add(d);
                System.out.println(divider);
                System.out.println("     Got it. I've added this task:");
                System.out.println("     " + d.toString());
                System.out.println("     Now you have " + array.size() + " tasks in the list.");
                System.out.println(divider);
            }
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