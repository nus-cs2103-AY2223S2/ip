package duke;

public class Parser {
    public Parser(){
    }

    public String parse(String input) {
        Integer len = input.length();
        if (input.equals("bye")){
            return "BYE";
        } else if (input.equals("list")) {
            return "LIST";
        } else if (len >= 8 && input.substring(0, 6).equals("delete")) {
            return "DELETE";
        } else if (len >= 6 && input.substring(0, 4).equals("mark")) {
            return "MARK";
        } else if (len >= 8 && input.substring(0, 6).equals("unmark")) {
            return "UNMARK";
        } else if (len >= 6 && input.substring(0, 4).equals("todo")) {
            return "TODO";
        } else if (len >= 10 && input.substring(0, 8).equals("deadline")) {
            return "DEADLINE";
        } else if (len >= 7 && input.substring(0, 5).equals("event")) {
            return "EVENT";
        } else {
            return "ERROR";
        }
    }
}
