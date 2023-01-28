package Duke;

public class Mark extends Commands {
    public Mark(String str) {
        this.setCommandStorage(str);
    }

    @Override
    public void execute(TaskList tasks) {
        String content = this.getCommandStorage();
        int taskNumber = Character.getNumericValue(content.charAt(content.length() - 1));
        taskNumber -= 1;
        tasks.markTask(taskNumber, true);
        System.out.println("Nice! I've marked this task as done:\n" + "[" + tasks.getTaskIcon(taskNumber)
                + "] " + tasks.getTaskContent(taskNumber));
    }
}