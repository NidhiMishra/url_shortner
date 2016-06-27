package pabtGame.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pabtGame.models.BallBatch;
import pabtGame.models.Ball;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nidhi Mishra on 07/11/15.
 */
public class CreateBallService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBallService.class);

    public CreateBallService() {
    }

    public BallBatch createBatch(int count, int speed)
    {
        BallBatch ballBatch = new BallBatch();
        List<Ball> ballList = createBallList(count);

        ballBatch.setCount(count);
        ballBatch.setSpeed(speed);
        ballBatch.setBallList(ballList);

        return ballBatch;

    }

    private List<Ball> createBallList(int count) {

        List<Ball> ballList = new ArrayList<>();
        int x=0;
        double drift = 0;
        for(int i=0; i<count; i++)
        {
            Ball ball = new Ball();


            double randomNumber = drift + Math.random();

            if(randomNumber < 0.5)
            {

                x = x + 1;
                drift = -0.23;
                if(x == 19)
                {
                    drift = 0.5;
                }
            }
            else
            {
                x = x - 1;
                drift = 0.23;
                if(x == -21)
                {
                    drift = -0.5;
                }
            }
            LOGGER.info(String.valueOf(x));
            double randomNumber2 = Math.random();
            if(randomNumber2 < 0.2)
            {
                ball.setBad(false);
            }

            ball.setX(x);
            ballList.add(ball);
        }

        return ballList;
    }
}
























