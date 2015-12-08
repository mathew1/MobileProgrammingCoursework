package com.example.mathew.mobileprogrammingcoursework;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Mathew on 07/12/2015.
 */
public class surfaceView  extends SurfaceView implements SurfaceHolder.Callback
{
    private SurfaceHolder shBioSurface;

    surfaceThread drawingThread = null;


    public surfaceView(Context context)
    {
        super(context);
        shBioSurface = getHolder();
        shBioSurface.addCallback(this);
        drawingThread = new surfaceThread(getHolder(), this);
        setFocusable(true);

    }

    public surfaceThread getThread()
    {
        return drawingThread;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {

        drawingThread.setRunning(true);
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        drawingThread.setSurfaceSize(width,height);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        drawingThread.setRunning(false);
        while(retry)
        {
            try {
                drawingThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
