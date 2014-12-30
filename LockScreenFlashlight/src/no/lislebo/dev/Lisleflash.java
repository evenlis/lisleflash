package no.lislebo.dev;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.hardware.Camera.Parameters;
import android.hardware.Camera;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

public class Lisleflash extends Activity implements SurfaceHolder.Callback
{
    private boolean flashOn;
    private boolean previewOn;
    private View button;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    static Camera camera;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        disablePhoneSleep();
    }

    private void disablePhoneSleep() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void getCamera() {
        if(camera == null) {
            try {
                camera = Camera.open();
            } catch(RuntimeException e) {
                Context context = getApplicationContext();
                CharSequence text = e.toString();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    public void toggle(View view) {
        if(flashOn){
            flashOff();
        } else {
            flashOn();
        }
    }

    public void flashOn() {
        Toast.makeText(this, "Flash on", Toast.LENGTH_SHORT).show();
        if(camera == null) {
            return;
        }
        flashOn = true;
        Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
    }

    public void flashOff() {
        Toast.makeText(this, "Flash off", Toast.LENGTH_SHORT).show();
        if(camera == null) {
            return;
        }
        flashOn = false;
        Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
    }

    private void startPreview() {
        if (!previewOn && camera != null) {
            camera.startPreview();
            previewOn = true;
        }
    }

    private void stopPreview() {
        if (previewOn && camera != null) {
            camera.stopPreview();
            previewOn = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int heihgt) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onStart() {
        super.onStart();
        startPreview();
        getCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        flashOff();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(camera != null) {
            camera.release();
            stopPreview();
            camera = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(camera != null) {
            flashOff();
            stopPreview();
            camera.release();
        }
    }
}
