package offlineminds.com.mymovielist.ui.mainactivity;


import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;


import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;


import java.util.Calendar;

import offlineminds.com.mymovielist.R;
import offlineminds.com.mymovielist.appalarmmgr.MovieAlarmReceiver;
import offlineminds.com.mymovielist.appalarmmgr.MyReceiver;
import offlineminds.com.mymovielist.appalarmmgr.ScheduledDataFetching;
import offlineminds.com.mymovielist.ui.ComedyFragment.ComedyFragment;
import offlineminds.com.mymovielist.ui.HomeFragment.HomeFragment;

public class MainActivity extends FragmentActivity implements MainView {

    static String TAG= MainActivity.class.getName();
    MovieAlarmReceiver movieAlarmReceiver;
    DrawerLayout drawerLayout;
    ListView navigationList;
    FrameLayout frameLayout;
    String[] listItems;
    ActionBar actionBar;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationList = (ListView) findViewById(R.id.navigationDrawerList);
        frameLayout = (FrameLayout) findViewById(R.id.contentFrame);

        listItems = getResources().getStringArray(R.array.drawerListItems);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        navigationList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, listItems));
        navigationList.setOnItemClickListener(new DrawerItemClickListener());

        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //THis is not mandatory
        actionBar.setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name) {

        };

//        drawerLayout.setDrawerListener(drawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }
        movieAlarmReceiver = new MovieAlarmReceiver();
        setAlarm(this);
        setNewAlarm(this);
    }

    public void setNewAlarm(Context context){
        Intent myIntent = new Intent(context, MyReceiver.class);
        PendingIntent myPendingIntent = PendingIntent.getBroadcast(context,1231231,myIntent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long interval = (3*60*60*1000);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),interval,myPendingIntent);

        Toast.makeText(context, "Alarm Set After " + (3*60) + " seconds", Toast.LENGTH_SHORT).show();
    }


    public void setAlarm(Context context) {

        Log.d(TAG,"Inside Set Alarm of Main Activity");
        AlarmManager alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, MovieAlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 00);
        int interval = 1000;

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), interval, alarmIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selectItem(int position) {
        switch (position) {
            case 0:
                Fragment homeFragment = new HomeFragment();
                FragmentManager homeFragMgr = getSupportFragmentManager();
                FragmentTransaction homeFragTrans = homeFragMgr.beginTransaction();
                homeFragTrans.replace(R.id.contentFrame, homeFragment).commit();
                drawerLayout.closeDrawer(navigationList);
                break;

            case 1:
                Fragment commedyFragment = new ComedyFragment();
                FragmentManager commedyFragMgr = getSupportFragmentManager();
                FragmentTransaction commedyFragTransct = commedyFragMgr.beginTransaction();
                commedyFragTransct.replace(R.id.contentFrame, commedyFragment).commit();
                break;

            default:
                Toast.makeText(this, "Not Defined", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void attachDrawer() {

    }


    @Override
    public void attachFragmentToMainActivity() {

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            selectItem(position);
            drawerLayout.closeDrawer(navigationList);
        }
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
}
