package offlineminds.com.mymovielist.ui.mainactivity;


import android.app.ActionBar;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;


import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.androidnetworking.AndroidNetworking;

import offlineminds.com.mymovielist.R;
import offlineminds.com.mymovielist.ui.CommedyFragment.CommedyFragment;
import offlineminds.com.mymovielist.ui.HomeFragment.HomeFragment;

public class MainActivity extends FragmentActivity implements MainView {

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

        AndroidNetworking.initialize(getApplicationContext());


        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        navigationList = (ListView)findViewById(R.id.navigationDrawerList);
        frameLayout = (FrameLayout)findViewById(R.id.contentFrame);

        listItems = getResources().getStringArray(R.array.drawerListItems);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        navigationList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item,listItems));
        navigationList.setOnItemClickListener(new DrawerItemClickListener());

        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //THis is not mandatory
        actionBar.setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name){

//            public void onDrawerClosed(View view) {
//                actionBar.setTitle(R.string.app_name);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//            }
//
//            public void onDrawerOpened(View drawerView) {
//                actionBar.setTitle(R.string.app_name);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//            }

        };

//        drawerLayout.setDrawerListener(drawerToggle);
        if(savedInstanceState == null){
            selectItem(0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selectItem(int position){
        switch (position){
            case 0:
                Fragment homeFragment = new HomeFragment();
                FragmentManager homeFragMgr = getSupportFragmentManager();
                FragmentTransaction homeFragTrans = homeFragMgr.beginTransaction();
                homeFragTrans.replace(R.id.contentFrame,homeFragment).commit();
                drawerLayout.closeDrawer(navigationList);
                break;

            case 1:
                Fragment commedyFragment = new CommedyFragment();
                FragmentManager commedyFragMgr = getSupportFragmentManager();
                FragmentTransaction commedyFragTransct = commedyFragMgr.beginTransaction();
                commedyFragTransct.replace(R.id.contentFrame,commedyFragment).commit();
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
