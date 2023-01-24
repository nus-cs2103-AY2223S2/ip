package duke.task;

import duke.utilities.DateTranslator;
import duke.utilities.Parser;

public class Deadlines extends Task {

    public final String raw;
    String endDate;
    DateTranslator dateTranslator;

    public Deadlines(String name, boolean done) {
        super(name, done);
        raw = name;
        //dateTranslator = new duke.utilities.DateTranslator(raw);
        extract();
    }

    void extract() {
        try {
            String[] tokens = task_name.split("/");
            task_name = tokens[0];

            if (!DateTranslator.is_date(raw)) {

                String[] date = tokens[1].split(" ");
                StringBuilder temp = new StringBuilder("(" + date[0] + ": ");
                if (date.length > 3) {
                    for (int x = 1; x < date.length; x++) {
                        temp.append(date[x]).append(" ");
                    }
                    temp.append(")");
                    endDate = temp.toString();
                } else {
                    endDate = date.length == 2 ? "(" + date[0] + ": " + date[1] + ")"
                            : "(" + date[0] + ": " + date[1] + " " + date[2] + ")";
                }

            } else {

                dateTranslator = new DateTranslator(raw);
                endDate = dateTranslator.output;
                System.out.println(endDate);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //exception forwarded to Task manager;
        }

    }

    @Override
    public void add() {
        message_add = Parser.add + Parser.deadlineUnmarked_spaced + task_name + endDate;
    }

    @Override
    public void display() {
        if (done)
            message_display = Parser.deadlinedoMarked + task_name + endDate;
        else
            message_display = Parser.deadlineUnmarked + task_name + endDate;
    }

    @Override
    public void delete() {
        if (done)
            message_delete = Parser.delete + Parser.deadlineMarked_spaced + task_name + endDate;
        else
            message_delete = Parser.delete + Parser.deadlineUnmarked_spaced + task_name + endDate;
    }

    @Override
    public void marked() {
        message_marked = Parser.mark + Parser.deadlineMarked_spaced + task_name + endDate;
        done = true;
    }

    @Override
    public void unmarked() {
        message_unmarked = Parser.unmark + Parser.deadlineUnmarked_spaced + task_name + endDate;
        done = false;
    }
}
