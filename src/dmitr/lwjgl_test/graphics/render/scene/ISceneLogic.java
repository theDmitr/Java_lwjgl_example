package dmitr.lwjgl_test.graphics.render.scene;

import dmitr.lwjgl_test.graphics.kernel.Window;

public interface ISceneLogic {

    void init() throws Exception;
    void input(Window window);
    void update(float interval);
    void render(Window window);

}