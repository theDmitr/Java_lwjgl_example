package dmitr.lwjgl_test.graphics.kernel;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFWErrorCallback.createPrint;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private long window;
    private WindowProperties windowProperties;
    private boolean resized;

    public Window(WindowProperties properties) {
        this.windowProperties = properties;
    }

    public void init() {
        createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("[ERROR] GLFW initialization failed!");
        }

        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, this.windowProperties.resizable ? GL_TRUE : GL_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

        window = glfwCreateWindow(windowProperties.width, windowProperties.height, windowProperties.title, NULL, NULL);

        if (window == NULL) {
            throw new RuntimeException("[ERROR] Window creation error!");
        }

        glfwSetFramebufferSizeCallback(window, (window, width, height) -> {
            windowProperties.width = width;
            windowProperties.height = height;
            setResized(true);
        });

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidMode.width() - windowProperties.width) / 2, (vidMode.height() - windowProperties.height) / 2);

        glfwMakeContextCurrent(window);
        glfwSwapInterval(windowProperties.vSync ? 1 : 0);
        glfwShowWindow(window);

        GL.createCapabilities();
        System.out.println("[INFO] Window initialized!");
    }

    public boolean windowShouldClose() {
        return glfwWindowShouldClose(window);
    }

    public String getTitle() {
        return windowProperties.title;
    }

    public int getWidth() {
        return windowProperties.width;
    }

    public int getHeight() {
        return windowProperties.height;
    }

    public int getFPS() { return windowProperties.FPS; }

    public int getUPS() { return windowProperties.UPS; }

    public boolean isResized() {
        return resized;
    }

    public boolean isVSync() {
        return windowProperties.vSync;
    }

    public boolean isKeyPressed(int key) { return glfwGetKey(window, key) == GLFW_PRESS; }

    public void setVSync(boolean value) { windowProperties.vSync = value; }

    public void setResized(boolean value) { resized = value; }

    public void update() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public static class WindowProperties {
        private String title;
        private int width, height;
        private int FPS, UPS;
        private boolean vSync, resizable;

        public WindowProperties(String title, int width, int height, boolean vSync, boolean resizable, int FPS, int UPS) {
            this.title = title;
            this.width = width;
            this.height = height;
            this.vSync = vSync;
            this.resizable = resizable;
            this.FPS = FPS;
            this.UPS = UPS;
        }
    }

}