public class Unmark extends Commands {
    public Unmark(String str) {
        this.setCommandStorage(str);
    }

    @Override
    public void execute(User user) {
        String content = this.getCommandStorage();
        int taskNumber = Character.getNumericValue(content.charAt(content.length() - 1));
        taskNumber -= 1;
        user.markTask(taskNumber, false);
        System.out.println("OK, I've marked this task as not done yet:\n" + "[" + user.getTaskIcon(taskNumber)
                + "] " + user.getTaskContent(taskNumber));
    }
}