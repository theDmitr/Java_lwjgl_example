package dmitr.lwjgl_test.tools;

import java.util.Random;

public class Randomizer {

    public static float getFloat(float start, float end) { return new Random().nextFloat() * (end - start) + start; }

}