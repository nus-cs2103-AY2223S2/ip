package Duke.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PeriodTask extends Task {
    // Format : PeriodTask submit notebook /between 2019-10-15 /and 2019-10-15
    public PeriodTask(String str1) {
        super(str1);
    }

    String[] mainSplit = super.task_name.split(" ", 2); //original : "event project meeting /from 2019-10-15 /to 2019-10-15"
    String name = mainSplit[1]; // gives submit notebook /between 2019-10-15 /and 2019-10-15
    String[] StrList1 = name.split(" /between ", 2); //gives ["submit notebook","2019-10-15 /and 2019-10-15"]
    String nameMain = StrList1[0]; //gives "submit notebook"
    String[] StrList2 = StrList1[1].split(" /and ", 2); //gives ["2019-10-15", "2019-10-15"]
    LocalDate frm = LocalDate.parse(StrList2[0]); //gives "2019-12-01"
    LocalDate to = LocalDate.parse(StrList2[1]); //gives "2019-12-02"
    String finalTime = "from " + frm + " to " + to;


    @Override
    public String toString() {
        if (super.status == true) {
            return "[E][X] " + nameMain + " " + frm.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                    "-" + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        else {
            return "[E][ ] " + nameMain + " " + frm.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                    "-" + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }


}
