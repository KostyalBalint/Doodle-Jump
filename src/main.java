import Game.GameFrame;
import Game.GameLoop;

public class main {
    static GameFrame gameFrame;
    static GameLoop gameLoop;

    public static void main(String[] args) {
        gameFrame = new GameFrame();
        gameFrame.setVisible(true);

        try {
            gameLoop = GameLoop.getInstance();
            gameLoop.setUpdateFunction(gameFrame);
        }catch (Exception e) {
            e.printStackTrace();
        }
        gameLoop.start();
    }
}
