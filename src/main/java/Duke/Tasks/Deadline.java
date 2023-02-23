package Duke.Tasks;

import Duke.Tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {


    public Deadline(String str1) {
        super(str1);
    }

    String[] mainSplit = super.task_name.split(" ", 2);
    String name = mainSplit[1]; // gives "return book /by 2019-10-15"
    String[] StrList = name.split(" /by ", 2); //gives ["return book", "2019-10-15"]
    String nameMain = StrList[0]; //gives "return book"
    private LocalDate byDate = LocalDate.parse(StrList[1]); //gives "2019-10-15"


    @Override
    public String toString() {
        if(super.status == true) {
            return "[D][X] " + nameMain + " " + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        else {
            return "[D][ ] " + nameMain + " " + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }
}
