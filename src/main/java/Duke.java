import java.time.format.DateTimeParseException;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException{
        Ui ui = new Ui();
        Parser parser = new Parser();
        ui.initialise();
        parser.initialise();
        
        while(!parser.isInput("bye")) {
            if (parser.isInput("list")) {
                if(ui.getList().isEmpty()) {
                    ui.emptyErr();
                } else {
                    ui.viewList();
                }
            } else if (parser.isInput("mark")) {
                if(ui.getList().isEmpty()) {
                    ui.emptyErr();
                } else {
                    int num = parser.getTaskNum();
                    ui.markTask(num);
                }
            } else if (parser.isInput("unmark")) {
                if(ui.getList().isEmpty()) {
                    ui.emptyErr();
                } else {
                    int num = parser.getTaskNum();
                    ui.unmarkTask(num);
                }
            } else if (parser.isInput("todo")) {
                ToDo temp = ui.makeToDo(parser.getRaw());
                ui.addToList(temp);
            } else if (parser.isInput("event")) {
                try {
                    Event temp = ui.makeEvent(parser.getRaw());
                    ui.addToList(temp);
                } catch (DateTimeParseException e) {
                    System.out.println("Duke: Wrong date/time format!");
                    System.out.println("     Please enter correct format (yyyy/MM/dd HHmm)!");
                    ui.reset();
                }
            } else if (parser.isInput("deadline")) {
                try {
                    Deadline temp = ui.makeDeadline(parser.getRaw());
                    ui.addToList(temp);
                } catch (DateTimeParseException e) {
                    System.out.println("Duke: Wrong date/time format!");
                    System.out.println("      Please enter correct format (yyyy/MM/dd HHmm)!");
                    ui.reset();
                }
            } else if (parser.isInput("delete")) {
                if(ui.getList().isEmpty()) {
                    ui.emptyErr();
                } else {
                    int num = parser.getTaskNum();
                    ui.delete(num);
                }
            } else {
                System.out.println("Duke; Sorry I don't recognise that command :( Please try again.");
                ui.reset();
            }
            parser.newInput();
        }
        parser.closeParser();
        ui.save();
        ui.end();
    }

}
