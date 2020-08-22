package lesson1;

public class Human implements GetLimit {
    private String name;
    private int lengthRun;
    private int heightJump;

    public Human(String name) {
        this.name = name;
    }

    public Human(String name, int lengthRun, int heightJump) {
        this.name = name;
        this.lengthRun = lengthRun;
        this.heightJump = heightJump;
    }

    public void setLengthRun(int lengthRun) {
        this.lengthRun = lengthRun;
    }

    public void setHeightJump(int heightJump) {
        this.heightJump = heightJump;
    }

    @Override
    public void run() {
//        public void run(int lengthRun) {
        System.out.println("Человек " + name + " может пробежать " + lengthRun + " метров");
    }

    @Override
    public void jump() {
        System.out.println("Человек " + name + " может перепрыгнуть " + heightJump + " метров");
    }

    @Override
    public int getLengthRun() {
        return lengthRun;
    }

    @Override
    public int getHeightJump() {
        return heightJump;
    }

}
