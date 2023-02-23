package Duke.Tasks;

import Duke.Tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event(String str1) {
        super(str1);
    }

    String[] mainSplit = super.task_name.split(" ", 2); //original : "event project meeting /from 2019-10-15 /to 2019-10-15"
    String name = mainSplit[1]; // gives "project meeting /from Mon 2pm /to 4pm"
    String[] StrList1 = name.split(" /from ", 2); //gives ["project meeting","2019-10-15 /to 2019-10-15"]
    String nameMain = StrList1[0]; //gives "project meeting"
    String[] StrList2 = StrList1[1].split(" /to ", 2); //gives ["2019-10-15", "2019-10-15"]
    LocalDate frm = LocalDate.parse(StrList2[0]); //gives "2019-12-01"
    LocalDate to = LocalDate.parse(StrList2[1]); //gives "2019-12-02"
    String finalTime = "from " + frm + " to " + to;


    @Override
    public String toString() {
        if(super.status == true) {
            return "[E][X] " + nameMain + " " + frm.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        else {
            return "[E][ ] " + nameMain + " " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

}
