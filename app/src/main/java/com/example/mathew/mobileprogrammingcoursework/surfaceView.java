package com.example.mathew.mobileprogrammingcoursework;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Mathew on 07/12/2015.
 */
public class surfaceView  extends SurfaceView implements SurfaceHolder.Callback
{
    //Variables
    private SurfaceHolder shBioSurface;

    surfaceThread drawingThread = null;

//Constructor
    public surfaceView(Context context)
    {
        super(context);
        shBioSurface = getHolder();
        shBioSurface.addCallback(this);
        drawingThread = new surfaceThread(getHolder(), this);
        setFocusable(true);

    }
//Getter
    public surfaceThread getThread()
    {
        return drawingThread;
    }
//Constructor
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {

        drawingThread.setRunning(true);
        drawingThread.start();
    }
//Constructor
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        drawingThread.setSurfaceSize(width,height);

    }
//Makes Catch When Destroying Surface
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
