package com.hyy.clientapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hyy.app.remote.IStudentService;
import com.hyy.app.remote.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText et_aidl_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_aidl_id = (EditText) findViewById(R.id.et_aidl_id);
    }

    private ServiceConnection conn;
    private IStudentService studentService;

    public void bindRemoteService(View v) {

        Intent intent = createExplicitFromImplicitIntent(this,
                new Intent("com.hyy.app.remote.MyRemoteService.Action"));
        if (conn == null) {
            conn = new ServiceConnection() {

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }

                @Override
                public void onServiceConnected(ComponentName name,
                                               IBinder service) {
                    Log.e("TAG", "onServiceConnected()");
                    studentService = IStudentService.Stub.asInterface(service);
                }
            };
            bindService(intent, conn, Context.BIND_AUTO_CREATE);
            Toast.makeText(this, "绑定Service", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经绑定Service", Toast.LENGTH_SHORT).show();
        }

    }

    public void invokeRemote(View v) throws RemoteException {
        if (studentService != null) {
            int id = Integer.parseInt(et_aidl_id.getText().toString());
            Student student = studentService.getStudentById(id);
            Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "请先绑定Service", Toast.LENGTH_SHORT).show();
        }
    }

    public void unbindRemoteService(View v) {
        if (conn != null) {
            unbindService(conn);
            conn = null;
            studentService = null;
            Toast.makeText(this, "解绑Service", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "还未绑定Service", Toast.LENGTH_SHORT).show();
        }
    }

    /***
     * Android L (lollipop, API 21) introduced a new problem when trying to invoke implicit intent,
     * "java.lang.IllegalArgumentException: Service Intent must be explicit"
     *
     * If you are using an implicit intent, and know only 1 target would answer this intent,
     * This method will help you turn the implicit intent into the explicit form.
     *
     * Inspired from SO answer: http://stackoverflow.com/a/26318757/1446466
     * @param context
     * @param implicitIntent - The original implicit intent
     * @return Explicit Intent created from the implicit original intent
     */
    private Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
