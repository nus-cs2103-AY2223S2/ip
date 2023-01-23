public class ToDo extends Task{
    String tag = "[T]";
    public ToDo() {
        super.tag = tag;
    }

    @Override
    public void genDscp(String input) {
        String dscp = input.replace("todo ", "");
        super.task = dscp;
    }

    //Override toString
}
