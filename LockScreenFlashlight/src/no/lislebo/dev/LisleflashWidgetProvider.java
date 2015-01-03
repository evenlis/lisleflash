import android.widget.RemoteViews;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;

public class LisleflashWidgetProvider extends AppWidgetProvider {
    public void onUpdate(Context context, AppWidgetManager widgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        for(int i=0; i<N; i++) {
            int currentId = appWidgetIds[i];
            Intent intent = new Intent(context, Lisleflash.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.lisleflash_widget);
            views.setOnClickPendingIntent(R.id.button, pendingIntent);
            widgetManager.updateAppWidget(currentId, views);
        }
    }
}
