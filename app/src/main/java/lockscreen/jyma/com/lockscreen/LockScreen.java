package lockscreen.jyma.com.lockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.preference.PreferenceManager;
import java.util.Locale;
/**
 * Created by jym on 2016/3/9.
 */
public class LockScreen extends Activity {
    private boolean a()
    {
        return true;
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        Object localObject = (DevicePolicyManager)getSystemService("device_policy");
        ComponentName localComponentName = new ComponentName(this, LockScreenReceiver.class);
        SharedPreferences localSharedPreferences;
        int i;
        if (((DevicePolicyManager)localObject).isAdminActive(localComponentName))
        {
            localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            i = localSharedPreferences.getInt("times", 0);
            if (i < 2147483647)
            {
                SharedPreferences.Editor localEditor = localSharedPreferences.edit();
                i += 1;
                localEditor.putInt("times", i).commit();
            }
        }
        try
        {
            PackageInfo localPackageInfo = getPackageManager().getPackageInfo("com.hy.minifetion", 0);
            ((DevicePolicyManager)localObject).lockNow();
            finish();
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
            ((DevicePolicyManager)localObject).lockNow();
            finish();
            Process.killProcess(Process.myPid());
            localObject = new Intent("android.app.extra.DEVICE_ADMIN");
            Intent localIntent1 = ((Intent) localObject).putExtra("android.app.extra.DEVICE_ADMIN",544554);
            if (a()) {
                Intent localIntent2 = ((Intent) localObject).putExtra("android.app.extra.ADD_EXPLANATION", "一键锁屏；\n首次使用需激活设备管理器，卸载需先取消激活设备管理器（系统设置--安全--设备管理器）");
            }
            startActivity((Intent)localObject);
            finish();
        }
    }
}
