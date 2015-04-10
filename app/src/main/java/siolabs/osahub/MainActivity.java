package siolabs.osahub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import siolabs.osahub.Activity.AddNewTransactionActivity;
import siolabs.osahub.Activity.ViewAccount;
import siolabs.osahub.Activity.ViewAllTransaction;
import siolabs.osahub.Activity.ViewCategory;


public class MainActivity extends ActionBarActivity {


    public enum AppStart {
        FIRST_TIME, FIRST_TIME_VERSION, NORMAL;
    }
    
    //variable to check if the app is opened for first time 
    final String PREFS_NAME = "MyPrefsFile";
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        setContentView(R.layout.activity_main);

        //code to check if it is the first time 
        if (settings.getBoolean("isFirstTime", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");
            // first time task
            //TODO  Create Accounts
            /*
            1.Move to ViewAccount activity
            */
            // TODO Populate Categories
            Intent i = new Intent(this,ViewCategory.class);
            startActivity(i);
            

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("isFirstTime", false).commit();
        }
        //now go with the normal Flow
        // TODO Show the Category List for showing the Categories with amount spend
        /*
         Open the ViewCategorizedSummary Activity
         */
        Intent i = new Intent(this,ViewCategory.class);
        startActivity(i);
        

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
