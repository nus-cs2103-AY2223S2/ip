public class ToDo extends Task{
    String tag = "T";
    public ToDo() {
        super.tag = tag;
    }

    @Override
    public void genDscp(String input) throws InvalidTodo {
        if (input.isBlank()) {
            throw new InvalidTodo();
        }
        super.description = input;
    }

    //Override toString
}
