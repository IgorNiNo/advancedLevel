package lesson1;

public class Main {
    public static void main(String[] args) {

        RunningTrack runTrack = new RunningTrack();
        Wall wall = new Wall();

        int[][] barriers = {
                {1000, 5},
                {500, 10},
                {2000, 3},
                {5500, 18},
                {7200, 2},
                {9000, 25},
                {1500, 7},
        };

        GetLimit[] participants = {
                new Human("John",3000,2),
                new Cat("Barsic",600,8),
                new Human("Peter",1500,4),
                new Robot("Verter",15000,20),
                new Human("Donald",3000,6),
                new Robot("Robocop",13000,22),
                new Cat("Murzik",1500,7),
        };


        for (int i = 0; i < participants.length; i++) {
            runTrack.setLengthTrack(barriers[i][0]);
            runTrack.info();
            participants[i].run();
            if( runTrack.testRun(participants[i].getLengthRun(), runTrack.getLengthTrack()) ) {
                System.out.println("Успешно пробежал");
                wall.setHeightWall(barriers[i][1]);
                wall.info();
                participants[i].jump();
                if( wall.testJump(participants[i].getHeightJump(), wall.getHeightWall()) ) {
                    System.out.println("Успешно перепрыгнул");
                } else {
                    System.out.println("Не смог перепрыгнуть");
                }
            } else {
                System.out.println("Не смог пробежать");
            }
        }

    }

}
