import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Deadline extends Task{

    private final String deadline;

    public Deadline(String details, String deadline) {
        super(details);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String completionDisplay;
        if (super.isCompleted()) {
            completionDisplay = "[X]";
        } else {
            completionDisplay = "[ ]";
        }
        return ("DEADLINE: " + super.getDetails() + " (By " + deadline +")" + completionDisplay);
    }


    public static HashMap<String, String> parseDeadline(String input) throws IllegalArgumentException {

        List<String> segments = Arrays.asList(input.split(" "));

        if (segments.size() <= 1) {
            throw new IllegalArgumentException("Command must contain additional information");
        }

        int detailsIndex = segments.indexOf("deadline") + 1;
        int deadlineIndex = segments.indexOf("/by") + 1;


        List<String> detailsSublist = segments.subList(detailsIndex, deadlineIndex - 1);
        List<String> deadlineSublist = segments.subList(deadlineIndex, segments.size());

        if (detailsSublist.size() == 0 || deadlineSublist.size() == 0) {
            throw new IllegalArgumentException("Command must contain additional information, missing either deadline or details");
        }

        String details = String.join(" ", detailsSublist);
        String deadline = String.join(" ", deadlineSublist);

        HashMap<String, String> result = new HashMap<>();
        result.put("details", details);
        result.put("deadline", deadline);
        return result;
    }





}
