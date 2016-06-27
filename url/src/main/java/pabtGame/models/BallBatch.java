package pabtGame.models;

import java.util.List;

/**
 * Created by Nidhi Mishra on 07/11/15.
 */
public class BallBatch {

    int count;
    int speed;

    List<Ball> ballList;


    public void setCount(int count) {
        this.count = count;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setBallList(List<Ball> ballList) {
        this.ballList = ballList;
    }

    public int getCount() {
        return count;
    }

    public int getSpeed() {
        return speed;
    }

    public List<Ball> getBallList() {
        return ballList;
    }
}
