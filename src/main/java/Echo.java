public class Echo extends Commands {
    public Echo(String str) {
        this.setCommandStorage(str);
    }
    @Override
    public void execute(User user) {
        System.out.println(this.getCommandStorage());
    }
}
