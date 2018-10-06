package net.sabamiso.android.androidopencv343test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

import java.util.Random;

class OpenCVView extends View {
    Bitmap bmp;
    Mat mat;

    public OpenCVView(Context context) {
        super(context);

        mat = new Mat();
        mat.create(new Size(640, 480), CvType.CV_8UC3);

        byte [] buf = new byte[3];

        Random r = new Random();
        for (int y = 0; y < mat.rows(); ++y) {
            for (int x = 0; x < mat.cols(); ++x) {
                buf[0] = (byte)r.nextInt(255);
                buf[1] = (byte)r.nextInt(255);
                buf[2] = (byte)r.nextInt(255);
                mat.put(y, x, buf);
            }
        }

        bmp = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);

        Utils.matToBitmap(mat, bmp);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect src = new Rect(0, 0,  mat.cols(), mat.cols());
        Rect dst = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawBitmap(bmp, src, dst, null);
    }
}
