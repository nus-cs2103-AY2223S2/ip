class A {
    private int x;
    public static A zeroValueA = new A(0);

    private A(int x){
        this.x = x;
    }

    public static A construct(int x) {
        if (x == 0) {
            return A.zeroValueA;
        } else {
            return A(x);
        }
    }
}
