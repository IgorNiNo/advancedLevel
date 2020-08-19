package lesson1;

public class Wall {
    private  int heightWall;

    public int getHeightWall() {
        return heightWall;
    }

    public void setHeightWall(int heightWall) {
        this.heightWall = heightWall;
    }

    public boolean testJump(int height, int heightWall) {
        if (height >= heightWall) {
            return true;
        } else {
            return false;
        }
    }

    public void info() {
        System.out.println("Стена высотой: " + heightWall + " метров");
    }

}
