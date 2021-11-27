import Game.Game;
import Game.GameLoop;

public class main {
    static GameLoop gameLoop;

    public static void main(String[] args) {

        Game.getInstance().getGameFrame().showMenu();

        try {
            gameLoop = GameLoop.getInstance();
            gameLoop.setUpdateFunction(Game.getInstance());
        }catch (Exception e) {
            e.printStackTrace();
        }
        //gameLoop.start();
    }
}
