package duke.tasks;

public class DoAfter extends Task {

    public DoAfter(String keyword, String message, Boolean isComplete) {
        super(keyword, message, isComplete);
    }

    @Override
    public String provideDetails() {
        String[] separateText = this.taskDescription.split("/after");

        return this.isComplete ? "[DA]" + "[x] " + separateText[0] + " (after: " + separateText[1] + ")"
                : "[DA]" + "[ ] " + separateText[0] + " (after: " + separateText[1] + ")";
    }
}





