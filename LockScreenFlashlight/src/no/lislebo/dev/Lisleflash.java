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
    private boolean lightOn;
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

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int heihgt) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void toggle(View view) {
        flashOn();
    }

    public void flashOn() {
        Parameters params;
        try {
            camera = Camera.open();
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
        } catch(Exception e) {
            Context context = getApplicationContext();
            CharSequence text = e.toString();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.d("Lisleflash",e.toString());
        }
    }
}
