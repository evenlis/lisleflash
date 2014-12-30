package no.lislebo.dev;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
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
        if(flashOn)
            flashOff();
        else
            flashOn();
    }

    public void flashOn() {
        if(camera == null) {
            return;
        }
        flashOn = true;
        Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
    }

    public void flashOff() {
        if(camera == null) {
            return;
        }
        flashOn = false;
        Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

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
        getCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        flashOn();
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
            camera = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(camera != null) {
            flashOff();
            camera.release();
        }
    }
}
