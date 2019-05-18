package pl.agh.tw.lab8;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

/**
 * Created by Patryk on 2015-06-16.
 */
public class MandelbrotTask implements Callable{
    private final int MAX_ITER = 100;
    private final double ZOOM = 150;
    private double zx, zy, cX, cY, tmp;
    private int y;
    private int width;
    private BufferedImage I;

    MandelbrotTask(int y, int w, BufferedImage I) {
        this.y = y;
        this.width = w;
        this.I = I;
    }

    public Integer call() {
        for (int x = 0; x < width; x++) {
            zx = zy = 0;
            cX = (x - 400) / ZOOM;
            cY = (y - 300) / ZOOM;
            int iter = MAX_ITER;

            while (zx * zx + zy * zy < 4 && iter > 0) {
                tmp = zx * zx - zy * zy + cX;
                zy = 2.0 * zx * zy + cY;
                zx = tmp;
                iter--;
            }

            I.setRGB(x, y, iter | (iter << 8));
        }

        return 0;
    }
}
