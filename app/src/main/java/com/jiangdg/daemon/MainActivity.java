package com.jiangdg.daemon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jiangdg.aidl.MainServiceActivity;
import com.jiangdg.daemon.service.BackgroundService;
import com.jiangdg.daemon.service.GrayService;
import com.jiangdg.daemon.service.WhiteService;
import com.jiangdg.keepappalive.R;
import com.jiangdg.keepappalive.SportsActivity;

/**
 * https://github.com/D-clock/AndroidDaemonService
 * <p>
 * http://www.52im.net/thread-1140-1-1.html
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();
    /**
     * 黑色唤醒广播的action
     */
    private final static String BLACK_WAKE_ACTION = "com.wake.black";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_deamon);

        findViewById(R.id.btn_white).setOnClickListener(this);
        findViewById(R.id.btn_gray).setOnClickListener(this);
        findViewById(R.id.btn_black).setOnClickListener(this);
        findViewById(R.id.btn_background_service).setOnClickListener(this);
        //
        findViewById(R.id.btn_other_solution).setOnClickListener(this);
        //
        findViewById(R.id.btn_solution_double_service_aidl).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_white) { //系统正常的前台Service，白色保活手段
            Intent whiteIntent = new Intent(getApplicationContext(), WhiteService.class);
            startService(whiteIntent);

        } else if (viewId == R.id.btn_gray) {//利用系统漏洞，灰色保活手段（API < 18 和 API >= 18 两种情况）
            Intent grayIntent = new Intent(getApplicationContext(), GrayService.class);
            startService(grayIntent);

        } else if (viewId == R.id.btn_black) { //拉帮结派，黑色保活手段，利用广播唤醒队友
            Intent blackIntent = new Intent();
            blackIntent.setAction(BLACK_WAKE_ACTION);
            sendBroadcast(blackIntent);

            /*AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            PendingIntent operation = PendingIntent.getBroadcast(this, 123, blackIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis(), operation);*/

        } else if (viewId == R.id.btn_background_service) {//普通的后台进程
            Intent bgIntent = new Intent(getApplicationContext(), BackgroundService.class);
            startService(bgIntent);

        } else if (viewId == R.id.btn_other_solution) {//其他保活方案
            Intent intent = new Intent(this, SportsActivity.class);
            startActivity(intent);
        } else if (viewId == R.id.btn_solution_double_service_aidl) {
            Intent intent = new Intent(this, MainServiceActivity.class);
            startActivity(intent);
        }
    }
}
