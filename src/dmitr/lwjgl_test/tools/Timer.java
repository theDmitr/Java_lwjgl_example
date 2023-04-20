package dmitr.lwjgl_test.tools;

public class Timer {

    private double lastTime;

    public void init() {
        lastTime = getTime();
        System.out.println("[INFO] Timer initialized!");
    }

    public float getElapsedTime() {
        double currentTime = getTime();
        lastTime = currentTime;
        return (float) (currentTime - lastTime);
    }

    public double getLastTime() {
        return lastTime;
    }

    public static double getTime() {
        return System.nanoTime() / 1000000000.0;
    }

}