public class Ui {
    private static final int INDENT_LEVEL = 4;

    public void showError(DukeException e) {
        this.show(e.getDukeMessage());
    }

    public void show(String whatToShow) {
        String indentation = " ".repeat(Ui.INDENT_LEVEL);
        String horizontalLine = "_".repeat(60);
        String indentedInput = whatToShow.replaceAll("(?<=^|\n)", indentation);
        
        System.out.println(indentation + horizontalLine);
        System.out.println(indentedInput);
        System.out.println(indentation + horizontalLine + '\n');
    }
}