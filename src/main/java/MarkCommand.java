public class MarkCommand {

    private int number;
    public MarkCommand(String number) {
        this.number = Integer.parseInt(number) - 1;
    }

    public void mark() {
        try {
            Task curr = TaskList.get(number);
            curr.mark();
            Ui.printMark(curr);
        } catch (Exception m){
            Ui.printWrongNumber();
            String s = Ui.readCommand();
            new MarkCommand(s).mark();
        }
    }
}
