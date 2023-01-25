public class Deadline extends Task{


    public Deadline(String str1) {
        super(str1);
    }

    String[] mainSplit = super.task_name.split(" ", 2);
    String name = mainSplit[1]; // gives "return book /by Sunday"
    String[] StrList = name.split(" /by ", 2); //gives ["return book", "Sunday"]
    String nameMain = StrList[0]; //gives "return book"
    String dl = "(by: " + StrList[1] + ")"; //gives "by: Sunday"


    @Override
    public String toString() {
        if(super.status == true) {
            return "[D][X] " + nameMain + " " + dl;
        }
        else {
            return "[D][ ] " + nameMain + " " + dl;
        }
    }
}
