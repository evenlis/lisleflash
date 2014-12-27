package no.lislebo.dev;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.hardware.Camera.Parameters;
import android.hardware.Camera;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

public class Lisleflash extends Activity
{

    static Camera camera = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void toggle(View view) {
        flashOn();
    }

    public void flashOn() {
        boolean camera_available = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
        boolean flash_available = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        Context context = getApplicationContext();
        CharSequence text = camera_available ? "Camera_available" : "Camera_unavailable";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Parameters params;
        try {
            camera = Camera.open();
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
        } catch(Exception e) {
            text = e.toString();
            toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.d("Lisleflash",e.toString());
        }
    }
}
