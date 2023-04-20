package dmitr.lwjgl_test.graphics.kernel;

import dmitr.lwjgl_test.graphics.render.scene.ISceneLogic;
import dmitr.lwjgl_test.tools.Timer;

public class PerformanceEngine implements Runnable {

    private Window window;
    private Timer timer;
    private ISceneLogic sceneLogic;

    public PerformanceEngine(Window.WindowProperties properties, ISceneLogic sceneLogic) {
        this.window = new Window(properties);
        this.timer = new Timer();
        this.sceneLogic = sceneLogic;
    }

    @Override
    public void run() {
        try {
            init();
            loop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void init() throws Exception {
        window.init();
        timer.init();
        sceneLogic.init();
    }

    protected void loop() {
        float elapsed;
        float accumulator = 0f;
        float interval = 1f / window.getUPS();
        boolean running = true;

        while (running && !window.windowShouldClose()) {
            elapsed = timer.getElapsedTime();
            accumulator += elapsed;

            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if (!window.isVSync()) {
                sync();
            }
        }
    }

    private void sync() {
        System.out.println(1);
        float loopSlot = 1f / window.getFPS();
        double end = timer.getLastTime() + loopSlot;
        while (Timer.getTime() < end) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }

    protected void input() {
        sceneLogic.input(window);
    }

    protected void update(float interval) {
        sceneLogic.update(interval);
    }

    protected void render() {
        sceneLogic.render(window);
        window.update();
    }

}