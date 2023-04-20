package dmitr.lwjgl_test;

import dmitr.lwjgl_test.graphics.kernel.PerformanceEngine;
import dmitr.lwjgl_test.graphics.kernel.Window;
import dmitr.lwjgl_test.graphics.render.scene.Menu;

public class Main {

    public static void main(String[] args) {
        Window.WindowProperties properties = new Window.WindowProperties("Window", 400, 400, true, true, 60, 30);
        PerformanceEngine engine = new PerformanceEngine(properties, new Menu());
        engine.run();
    }

}