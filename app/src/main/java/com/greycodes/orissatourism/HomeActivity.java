package com.greycodes.orissatourism;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.greycodes.orissatourism.attractions.ArtsCraftsFragment;
import com.greycodes.orissatourism.attractions.TemplesFragment;
import com.greycodes.orissatourism.festivals.FestivalsFragment;
import com.greycodes.orissatourism.navdrawer.ExpandableListAdapter;
import com.greycodes.orissatourism.attractions.WaterWorldFragment;
import com.greycodes.orissatourism.destinations.DestinationsFragment;
import com.greycodes.orissatourism.others.AboutUsFragment;
import com.greycodes.orissatourism.specialinterest.SpecialInterestFragment;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class HomeActivity extends ActionBarActivity {

    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    static final String DISPLAY_MESSAGE_ACTION="com.example.test.DISPLAY_MESSAGE";;
    static final String SERVER_URL = "http://doylefermi.x20.in/register.php";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static String acc = "";
    public String msg = "";
    public static String accn = "";
    String SENDER_ID = "1019787135827";
    static final String TAG = "GCMDemo";
    TextView mDisplay;
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    SharedPreferences prefs;
    Context context;
    String regid="";
    String email="";
    String title="";
    String name="";
    private DrawerLayout mDrawerLayout;
    private android.support.v4.app.ActionBarDrawerToggle mDrawerToggle;
    private String[] navMenuTitles;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader= new ArrayList<String>();
    HashMap<String, List<String>> listDataChild=new HashMap<String,List<String>>();
    private LinearLayout mDrawerLinear;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupActionBar();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction;
        transaction=fragmentManager.beginTransaction();
        Fragment f=new DestinationsFragment();
        transaction.replace(R.id.frame_layout, f).commit();
        mDrawerLinear= (LinearLayout) findViewById(R.id.left_drawer);
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_bar);
        mDrawerLinear = (LinearLayout) findViewById(R.id.left_drawer);
        expListView = (ExpandableListView) findViewById(R.id.tourist_list);
        gcmcheck();

        listDataHeader.add(navMenuTitles[0]);
        listDataHeader.add(navMenuTitles[1]);
        listDataHeader.add(navMenuTitles[2]);
        listDataHeader.add(navMenuTitles[3]);
        listDataHeader.add(navMenuTitles[4]);
        List<String> dest=new ArrayList<String>();
        List<String> attr=new ArrayList<String>();
        List<String> spl=new ArrayList<String>();
        List<String> fest=new ArrayList<String>();
        List<String> abtus=new ArrayList<String>();
        attr.add("Water World");
        attr.add("Temples");
        attr.add("Arts & Crafts");
        listDataChild.put(listDataHeader.get(0), dest); // Header, Child data
        listDataChild.put(listDataHeader.get(1), attr);
        listDataChild.put(listDataHeader.get(2), spl);
        listDataChild.put(listDataHeader.get(3), fest); // Header, Child data
        listDataChild.put(listDataHeader.get(4), abtus);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_bar);
        mDrawerToggle = new android.support.v4.app.ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.ic_drawer,R.string.action_bar_title1, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
                super.onDrawerClosed(view);
            }


            public void onDrawerOpened(View drawerView) {
                title=getTitle().toString();
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        listAdapter=new ExpandableListAdapter(this,listDataHeader,listDataChild);
        expListView.setAdapter(listAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(){
            @Override
        public boolean onGroupClick(ExpandableListView parent,View v,int groupPosition,long id){
                if(groupPosition!=1)
                {
                    displayView(groupPosition,groupPosition);
                    return false;
                }
                else
                     return false;

            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                displayView(groupPosition,childPosition);
        return false;
            }
        });




    }
    private void displayView(int group,int position ) {
        Fragment fragment = null;
        switch (group) {
            case 0: fragment=new DestinationsFragment();break;
            case 1: switch(position){

                        case 0:fragment=new WaterWorldFragment();break;
                        case 1:fragment=new TemplesFragment();break;
                        case 2:fragment=new ArtsCraftsFragment();break;
            }
                break;
            case 2: fragment=new SpecialInterestFragment();break;

            case 3: fragment=new FestivalsFragment();break;

            case 4: fragment=new AboutUsFragment();break;


            default: break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction;
            transaction=fragmentManager.beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.frame_layout, fragment).commit();
            expListView.setItemChecked(position, true);
            expListView.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerLinear);

        } else {

            Log.e("MainActivity", "Error in creating fragment");
        }
    }
    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT))
        {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
        else{
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m=getMenuInflater();
        m.inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerLinear);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    public void setupActionBar(){
        //getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffff5253));


    }

    private void gcmcheck()
    {



       Account[] accounts = AccountManager.get(this).getAccounts();

        acc= accounts[0].name;
        accn=acc.substring(0, acc.indexOf('@'));
        name=accn;
        email=acc;

        context = getApplicationContext();
        gcm = GoogleCloudMessaging.getInstance(this);
        regid = getRegistrationId(context);

        if (regid=="") {    //Toast.makeText(getApplicationContext(), "Registering device...", Toast.LENGTH_SHORT).show();
                            registerInBackground();
                        }
        //else {Toast.makeText(getApplicationContext(), "Device registered, registration ID=" + regid,Toast.LENGTH_LONG).show(); }

    }

    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; setContentView(R.layout.activity_main);if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        return registrationId;
    }

    private SharedPreferences getGCMPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return getSharedPreferences(HomeActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }
    private void registerInBackground() {

        new AsyncTask<Void,Void,String>() {

            @Override
            protected String doInBackground(Void... params) {

                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;

                    // You should send the registration ID to your server over HTTP,
                    // so it can use GCM/HTTP or CCS to send messages to your app.
                    // The request to your server should be authenticated if your app
                    // is using accounts.
                    sendRegistrationIdToBackend();

                    // For this demo: we don't need to send it because the device
                    // will send upstream messages to a server that echo back the
                    // message using the 'from' address in the message.

                    // Persist the regID - no need to register again.
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            private void sendRegistrationIdToBackend() {
                final int MAX_ATTEMPTS = 5;
                final int BACKOFF_MILLI_SECONDS = 2000;
                final Random random = new Random();
                Log.i(TAG, "registering device (regId = " + regid + ")");
                String serverUrl = SERVER_URL;
                Map<String, String> params = new HashMap<String, String>();
                params.put("regId", regid);
                params.put("name",name);
                params.put("email",email);


                long backoff = BACKOFF_MILLI_SECONDS + random.nextInt(1000);
                // Once GCM returns a registration id, we need to register on our server
                // As the server might be down, we will retry it a couple
                // times.
                for (int i = 1; i <= MAX_ATTEMPTS; i++) {
                    Log.d(TAG, "Attempt #" + i + " to register");
                    try {
                        post(serverUrl, params);
                        // displayMessage(context, "Registered");
                        return;
                    } catch (IOException e) {
                        // Here we are simplifying and retrying on any error; in a real
                        // application, it should retry only on unrecoverable errors
                        // (like HTTP error code 503).
                        Log.e(TAG, "Failed to register on attempt " + i + ":" + e);
                        if (i == MAX_ATTEMPTS) {
                            break;
                        }
                        try {
                            Log.d(TAG, "Sleeping for " + backoff + " ms before retry");
                            Thread.sleep(backoff);
                        } catch (InterruptedException e1) {
                            // Activity finished before we complete - exit.
                            Log.d(TAG, "Thread interrupted: abort remaining retries!");
                            Thread.currentThread().interrupt();
                            return;
                        }
                        // increase backoff exponentially
                        backoff *= 2;
                    }
                }
                //  String message = context.getString(R.string.server_register_error,
                //        MAX_ATTEMPTS);
                //CommonUtilities.displayMessage(context, message);

            }
            private  void post(String endpoint, Map<String, String> params)throws IOException{
                URL url;
                try {
                    url = new URL(endpoint);
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException("invalid url: " + endpoint);
                }
                StringBuilder bodyBuilder = new StringBuilder();
                Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
                // constructs the POST body using the parameters
                while (iterator.hasNext()) {
                    Map.Entry<String, String> param = iterator.next();
                    bodyBuilder.append(param.getKey()).append('=')
                            .append(param.getValue());
                    if (iterator.hasNext()) {
                        bodyBuilder.append('&');
                    }
                }
                String body = bodyBuilder.toString();
                Log.v(TAG, "Posting '" + body+ "' to " + url);
                byte[] bytes = body.getBytes();
                HttpURLConnection conn = null;
                try {
                    Log.e("URL", "> " + url);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.setFixedLengthStreamingMode(bytes.length);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded;charset=UTF-8");
                    // post the request
                    OutputStream out = conn.getOutputStream();
                    out.write(bytes);
                    out.close();
                    // handle the response
                    int status = conn.getResponseCode();
                    if (status != 200) {
                        throw new IOException("Post failed with error code " + status);
                    }
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }

            protected void onPostExecute(String msg) {
                //setContentView(R.layout.activity_gcm_broadcast_receiver);
            }


        }.execute(null, null, null);}

    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGCMPreferences(context);
        //int appVersion = getAppVersion(context);
        // Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        //  editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }

}
