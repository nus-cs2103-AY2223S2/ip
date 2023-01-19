public class Echo extends Commands{
    public Echo(String str) {
        this.setCommandStorage(str);
    }
    @Override
    public void execute() {
        System.out.println(this.getCommandStorage());
    }
}
