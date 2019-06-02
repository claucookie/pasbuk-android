package es.claucookie.pasbuk;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.mobivery.utils.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import es.claucookie.pasbuk.helpers.PreferencesHelper;
import io.fabric.sdk.android.Fabric;

public class App extends Application {
    
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        instance = this;

        configImageLoader();
        
    }

    private void configImageLoader() {
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().cacheInMemory()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .displayer(new FadeInBitmapDisplayer(0))
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(displayImageOptions)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .threadPriority(Thread.NORM_PRIORITY)
                .build();

        ImageLoader.getInstance().init(config);
    }

}
