public class Event extends Task {

    public Event(String str1) {
        super(str1);
    }

    String[] mainSplit = super.task_name.split(" ", 2); //original : "event project meeting /from Mon 2pm /to 4pm"
    String name = mainSplit[1]; // gives "project meeting /from Mon 2pm /to 4pm"
    String[] StrList1 = name.split(" /from ", 2); //gives ["project meeting","Mon 2pm /to 4pm"]
    String nameMain = StrList1[0]; //gives "project meeting"
    String[] StrList2 = StrList1[1].split(" /to ", 2); //gives ["Mon 2pm", "to 4pm"]
    String frm = StrList2[0]; //gives "Mon 2pm"
    String to = StrList2[1]; //gives "to 4pm"
    String finalTime = "from " + frm + " to " + to;


    @Override
    public String toString() {
        if(super.status == true) {
            return "[E][X] " + nameMain + " " + finalTime;
        }
        else {
            return "[E][ ] " + nameMain + " " + finalTime;
        }
    }

}
