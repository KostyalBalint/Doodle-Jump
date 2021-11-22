public class GameLoop extends Thread {

    private static final int FPS = 60;
    private final UpdatableIF fn;

    GameLoop(UpdatableIF fn){
        this.fn = fn;
    }


    public void run(){
        //Call fn.update() every 1s/FPS times
        long startTime;
        long elapsedTime;
        long waitTime;
        long totalTime = 0;
        long targetTime = 1000 / FPS;
        while(true){
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
                //TODO: Add FPS to the game
                totalTime = 0;
            }
        }
    }
}
