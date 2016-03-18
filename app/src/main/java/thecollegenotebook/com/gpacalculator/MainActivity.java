package thecollegenotebook.com.gpacalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private TextView textbox;
    private double number = 0;
    private ArrayList<Double> group = new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        textbox = (TextView) findViewById(R.id.textView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Toast.makeText(MainActivity.this, "Your orientation is portrait", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(MainActivity.this, "Your orientation is landscape", Toast.LENGTH_SHORT).show();
        }
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
//        if (id == R.id.action_settings) {
//            return true;
//        }
        if (id == R.id.action_clear) {
            textbox.setText("");
            number = 0;
            group = new ArrayList<Double>();
        }

        return super.onOptionsItemSelected(item);
    }

    public void numberClickHandler(View view) {
        Button button = (Button) (findViewById(view.getId()));
//        Snackbar.make(coordinatorLayout, "You clicked " +button.getText(), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        if (textbox != null) {
            if (button.getText().toString().contains(".")) {
                if (textbox.getText().toString().isEmpty()) {
                    textbox.setText("0" + textbox.getText() + button.getText());
                } else if (textbox.getText().toString().contains(".")) {
                    Snackbar.make(coordinatorLayout, "You already entered a decimal", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                } else {
                    textbox.setText("" + textbox.getText() + button.getText());
                }
            } else {
                textbox.setText("" + textbox.getText() + button.getText());
            }
        }
    }

    public void clearClickHandler(View view) {
        textbox.setText("");
    }

    public void abcClickHandler(View view){
        Intent intent = new Intent(this, AlphActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    public void addClickHandler(View view) {
        try {
            number = Double.parseDouble(textbox.getText().toString());
        } catch (NumberFormatException e) {
            Snackbar.make(coordinatorLayout, "Something went wrong.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            return;
        }
        group.add(number);
        number = 0;
        textbox.setText("");

    }

    public void doneClickHandler(View view) {
        if (!textbox.getText().toString().isEmpty()) {
            try {
                number = Double.parseDouble(textbox.getText().toString());
            } catch (NumberFormatException e) {
                Snackbar.make(coordinatorLayout, "Something went wrong.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
            group.add(number);
        }
        double sum = 0;
        for (int i = 0; i < group.size(); i++) {
            sum += group.get(i);
        }
        double result = sum / group.size();
        result = (double) Math.round(result * 100) / 100;
        textbox.setText("" + result);
        number = 0;
        group = new ArrayList<Double>();
    }
}
