package siolabs.badmotherfucker.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Date;
import java.util.Calendar;

import siolabs.badmotherfucker.R;

public class AddNewTransactionActivity extends ActionBarActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_transaction);

        ImageButton datePicker = (ImageButton) findViewById(R.id.datePickerBtn);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment((TextView) findViewById(R.id.chosenDateTextView));
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_transaction, menu);
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

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        
        public static int year;
        public static int monthOfYear;
        public static int dayOfMonth;
        public TextView date;
        
        
        public DatePickerFragment(){}
        //TODO remove this and use Fragments Arguments
       public DatePickerFragment(TextView chosenDate){
           this.date = chosenDate;
       }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }



        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //TODO Code here to set the Date to the object
            this.year = year;
            this.monthOfYear = monthOfYear +1 ;
            this.dayOfMonth = dayOfMonth;
            date.setText(dayOfMonth + "/" + monthOfYear +"/" + year);
        }
    }
}
