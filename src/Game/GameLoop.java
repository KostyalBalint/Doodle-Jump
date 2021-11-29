package Game;

import Item.UpdatableIF;

/**
 * The GameLoop class is responsible for running the game loop.
 * This runs in a separate thread, and tries to maintain a constant 60 FPS
 */
public class GameLoop extends Thread {

    private static final int FPS = 60;
    private UpdatableIF fn;
    private static GameLoop instance;

    /**
     * Empty constructor
     */
    public GameLoop() {
    }

    /**
     * Resets the GameLoop instance
     * @return GameLoop The GameLoop instance
     */
    public static GameLoop reinitialize() {
        instance = new GameLoop();
        return instance;
    }

    /**
     * Gets the GameLoop instance, if it doesn't exist, it creates one
     *
     * @return GameLoop The GameLoop instance
     */
    public static GameLoop getInstance() {
        if (instance == null) {
            instance = new GameLoop();
        }
        return instance;
    }

    /**
     * Sets the function to be called for every frame
     *
     * @param fn The function to be called, must implement UpdatableIF
     */
    public void setUpdateFunction(UpdatableIF fn) {
        this.fn = fn;
    }

    /**
     * The game loop, runs in a separate thread
     */
    public void run() {
        //Call fn.update() every 1s/FPS times
        long startTime;
        long elapsedTime;
        long waitTime;
        long totalTime = 0;
        long targetTime = 1000 / FPS;
        while (true) {
            startTime = System.nanoTime();
            fn.update();
            elapsedTime = System.nanoTime() - startTime;
            waitTime = targetTime - elapsedTime / 1000000;
            if(waitTime < 0){
                waitTime = 5;
            }
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            totalTime += System.nanoTime() - startTime;
            if(totalTime >= 1000000000){
                //System.out.println("FPS: " + FPS);
                totalTime = 0;
            }
        }
    }
}
