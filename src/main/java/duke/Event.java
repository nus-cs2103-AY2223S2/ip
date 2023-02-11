package duke;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

import util.*;

public class Event extends Task {

    protected String from;
    protected String to;
    static String divider = "    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══";

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = "E";
    }

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString();
        this.to = to.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString();
    }

    public Event(Boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
        this.taskType = "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }


    public static void createEventTask(ArrayList<Task> array, String[] splitInput) {
        String combinedString = String.join(" ", splitInput);
        if (splitInput.length == 1 || splitInput[1].equals("")){
            try {
                throw new DukeException("event");
            } catch (Exception e) {
                System.out.println(divider);
                System.out.println(e.toString());
                System.out.println(divider);
            }
        } else {
            // for(int j=1; j< splitInput.length; j++){
            //     if(splitInput[j].equals("/from")){
            //         for (int k=1; k< j-1; k++){
            //             splitInput[1] = splitInput[1] + " " + splitInput[k+1];
            //         }
            //         for(int i = j + 1; i < splitInput.length; i++) {
            //             splitInput[i] = splitInput[i].replace("/to", "to:");
            //         }
            //         for (int l=splitInput.length-1; l > j +1; l--){
            //             splitInput[splitInput.length-1] = splitInput[l-1]+" "+splitInput[splitInput.length-1];
            //         }
            //     } else {
            //         splitInput[j] = splitInput[j];
            //     }
            // }
               
            // String duration = splitInput[splitInput.length-1];
            // String desc = splitInput[1];
           
           
            String event = combinedString.split(" ", 2)[1];
            String[] event_Arr = event.split(" /from", 2);

                if (event_Arr.length == 2) {
                    String desc = event_Arr[0];
                    String[] period_Arr = event_Arr[1].split(" /to");
                    if (period_Arr.length == 2) {

                        String from = period_Arr[0].strip();
                        String to = period_Arr[1].strip();
;

                        if(isDate(from) && isDate(to)){
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate ldFrom = LocalDate.parse(from, formatter);
                            LocalDate ldTo = LocalDate.parse(to, formatter);
                            Event e = new Event(desc, ldFrom, ldTo);
                            array.add(e);
                            Ui.addTask(array, e);
                        } else {
                            Event e = new Event(desc, from, to);
                            array.add(e);
                            Ui.addTask(array, e);
                        }

                       
                    }
                }
        }
        
    }
    public static boolean isDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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


    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }
  
        // endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
        //           (Assuming that all Strings are roughly equally long)
        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0) {
            return "";
        }
  
        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length())
                        + separator.length());
  
        StringBuffer buf = new StringBuffer(bufSize);
  
        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }



    @Override
    public String saveFormat() {
        String d = " | ";
        int marked = this.getIsDone() ? 1 : 0;
        String timePeriod = from + d + to;
        return "E" + d + marked + d + description + d + timePeriod;
    }

    
  
  }

