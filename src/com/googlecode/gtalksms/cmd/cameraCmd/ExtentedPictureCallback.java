package com.googlecode.gtalksms.cmd.cameraCmd;

import java.io.File;
import java.util.Date;

import com.googlecode.gtalksms.tools.Tools;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.util.Log;

public abstract class ExtentedPictureCallback implements PictureCallback {
    private File path;
    protected Context ctx;
    protected String recipient;

    public ExtentedPictureCallback(File path, Context ctx, String recipient) {
        this.path = path;
        this.ctx = ctx;
        this.recipient = recipient;
    }
    
    public void onPictureTaken(byte[] data, Camera camera) {
        File filename = new File(path, "photo-" + new Date().getDate() + ".jpg");

        if (Tools.writeFile(data, filename)) {
            onPictureSaved(filename);
        } else {
            Log.e(Tools.LOG_TAG, "Error writing file");
        }
        camera.release();
    }
    
    protected abstract boolean onPictureSaved(File picture);    
}