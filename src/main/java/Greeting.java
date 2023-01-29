class Greeting extends Event {
    int status;

    public Greeting() {
        super(false);
        this.status = -1;
    }

    public Greeting(int inPlay) {
        super(false);
        this.status = inPlay;
    }

    public Event toNext() {
        if (this.status == 0) {
            return new Ending();
        } else {
            return new DoTask();
        }
    }

    @Override
    public String toString() {
        String toPrintOut = "";
        toPrintOut += "_".repeat(22) + '\n';
        toPrintOut += "SHALL WE PLAY A GAME?" + '\n';
        toPrintOut += "_".repeat(22) + '\n';
        return toPrintOut;
    }
}