public class Echo extends Commands {
    public Echo(String str) {
        this.setCommandStorage(str);
    }
    @Override
    public test void execute(User user) {
        System.out.println(this.getCommandStorage());
    }
}
