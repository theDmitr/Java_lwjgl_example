package dmitr.lwjgl_test.graphics.render.scene;

import dmitr.lwjgl_test.graphics.kernel.Window;
import dmitr.lwjgl_test.tools.Randomizer;
import dmitr.lwjgl_test.graphics.render.Color;
import dmitr.lwjgl_test.graphics.render.Renderer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Menu implements ISceneLogic {

    private Renderer renderer;
    private Color color = new Color(0, 0, 0, 0);

    public Menu() {
        renderer = new Renderer();
    }

    @Override
    public void init() throws Exception {
        System.out.println("[INFO] Scene initialized!");
        renderer.init();
    }

    @Override
    public void input(Window window) {
        if (window.isKeyPressed(GLFW.GLFW_KEY_UP)) {
            color.setR(Randomizer.getFloat(0f, 1f));
            color.setG(Randomizer.getFloat(0f, 1f));
            color.setB(Randomizer.getFloat(0f, 1f));
        }
    }

    @Override
    public void update(float interval) {

    }

    @Override
    public void render(Window window) {
        if (window.isResized()) {
            GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        GL11.glClearColor(color.getR(), color.getG(), color.getB(), color.getA());

        renderer.clear();
    }

}