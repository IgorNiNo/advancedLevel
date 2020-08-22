package lesson1;

public class Cat implements GetLimit {
    private String name;
    private int lengthRun;
    private int heightJump;

    public Cat(String name) {
        this.name = name;
    }

    public Cat(String name, int lengthRun, int heightJump) {
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
        System.out.println("Кот " + name + " может пробежать " + lengthRun + " метров");
    }

    @Override
    public void jump() {
        System.out.println("Кот " + name + " может перепрыгнуть " + heightJump + " метров");
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
