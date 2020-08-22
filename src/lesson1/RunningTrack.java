package lesson1;

public class RunningTrack {
    private int lengthTrack;

    public int getLengthTrack() {
        return lengthTrack;
    }

    public void setLengthTrack(int lengthTrack) {
        this.lengthTrack = lengthTrack;
    }

    public boolean testRun(int length, int lengthTrack) {
        if (length >= lengthTrack) {
            return true;
        } else {
            return false;
        }
    }

    public void info() {
        System.out.println("Беговая дорожка длиной: " + lengthTrack + " метров");
    }

}
