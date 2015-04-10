package siolabs.osahub.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

import siolabs.osahub.Entity.Transaction;
import siolabs.osahub.R;
import siolabs.osahub.database.ExpenseDatabaseHelper;

public class AddNewTransactionActivity extends ActionBarActivity {

    ToggleButton isExpenseTB;
    Spinner accountS;
    Spinner categoryS;
    TextView datepickerDP;
    EditText amountET;
    ExpenseDatabaseHelper dbHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_transaction);
        
        //get the database 
        dbHelper = new ExpenseDatabaseHelper(this);

        ImageButton datePicker = (ImageButton) findViewById(R.id.datePickerBtn);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment((TextView) findViewById(R.id.chosenDateTextView));
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        
        isExpenseTB = (ToggleButton) findViewById(R.id.toggleBtn);
        accountS = (Spinner) findViewById(R.id.accountSpinner);
        categoryS = (Spinner) findViewById(R.id.catSpinner);
        amountET = (EditText) findViewById(R.id.amountEditText);
        datepickerDP = (TextView) findViewById(R.id.chosenDateTextView);
        
        Button doneTransaction = (Button) findViewById(R.id.doneAddNewTransBtn);
        doneTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewTransaction();
            }
        });
    }

    private void addNewTransaction() {
        Transaction t = new Transaction();
        if(isExpenseTB.getText().toString().equalsIgnoreCase("Income")){
            //expense
            t.setExpense(true);
        }else {
            t.setExpense(false);
        }
        
        t.setAccName(accountS.getSelectedItem().toString());
        t.setCatName(accountS.getSelectedItem().toString());
        t.setDateStr(datepickerDP.getText().toString());
        t.setAmount(Float.valueOf(amountET.getText().toString()));
        
        //TODO check if values is inserted correctly
        if(dbHelper.insertTransaction(t) <0)
        {
            Toast.makeText(this,"Transaction Failed", Toast.LENGTH_LONG).show();
            setResult(Activity.RESULT_OK);
        }
        else{
            Toast.makeText(this,"Transaction Added Successfully", Toast.LENGTH_LONG).show();
            setResult(Activity.RESULT_CANCELED);
        }

        
        
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
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }



        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //TODO Code here to set the Date to the object
            this.year = year;
            this.monthOfYear = monthOfYear + 1 ;
            this.dayOfMonth = dayOfMonth;
            date.setText(dayOfMonth + "/" + monthOfYear +"/" + year);
        }
    }
}
