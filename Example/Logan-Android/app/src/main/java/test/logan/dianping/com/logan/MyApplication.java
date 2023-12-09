package test.logan.dianping.com.logan;

import android.app.Application;
import android.os.SystemClock;
import android.util.Log;

import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;
import com.dianping.logan.OnLoganProtocolStatus;

import java.io.File;

public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getName();
    private static final String FILE_NAME = "logan_v1";

    @Override
    public void onCreate() {
        super.onCreate();
        initLogan();

        long start = SystemClock.elapsedRealtime();
        for (int i = 0; i < 10000; i++) {
            Logan.w("MyApplication onCreate", 3);
        }
        Log.i(TAG, "cost: " + (SystemClock.elapsedRealtime() - start));
        Logan.f();
    }

    private void initLogan() {
        LoganConfig config = new LoganConfig.Builder()
                .setCachePath(getFilesDir().getAbsolutePath())
                .setPath(getExternalFilesDir(null).getAbsolutePath() + File.separator + FILE_NAME)
                .setEncryptKey16("0123456789012345".getBytes())
                .setEncryptIV16("0123456789012345".getBytes())
                .build();
        Logan.init(config);
        Logan.setDebug(true);
        Logan.setOnLoganProtocolStatus(new OnLoganProtocolStatus() {
            @Override
            public void loganProtocolStatus(String cmd, int code) {
                Log.d(TAG, "clogan > cmd : " + cmd + " | " + "code : " + code);
            }
        });

    }
}
