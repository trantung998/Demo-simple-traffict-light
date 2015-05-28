package simpletraffictlight.example.tung.demo_simple_traffict_light;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity implements OnClickListener, OnItemClickListener {
    TextView mainTextView;
    Button mainButton;
    EditText mainEditText;

    ListView mainListView;
    ArrayAdapter _arrayAdapter;
    ArrayList _arrayNameList = new ArrayList();

    ShareActionProvider shareActionProvider;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init textView
        mainTextView = (TextView)findViewById(R.id.main_textview);
        mainTextView.setText("Hello word from Main Activity");
        //button
        mainButton = (Button)findViewById(R.id.main_button);
        //mainButton.setOnClickListener(this);
        //edittext
        mainEditText = (EditText)findViewById(R.id.main_text_editor);
        mainEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        mainEditText.clearFocus();
        //list view
        _arrayAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                _arrayNameList);

        mainListView  = (ListView)findViewById(R.id.main_listview);
        mainListView.setAdapter(_arrayAdapter);
        mainListView.setOnItemClickListener(this);
        DisplayGreeting();
    }

    public void DisplayGreeting(){
        sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");

        if (name.length() > 0) {
        Toast.makeText(this,name,Toast.LENGTH_LONG).show();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Hello");
            dialog.setMessage("What your name?");

            final EditText editText = new EditText(this);
            dialog.setView(editText);

            //make ok button
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = editText.getText().toString();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", name);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Welcome, " + name + "!", Toast.LENGTH_LONG).show();
                }
            });

            dialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            dialog.show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem shareItem = menu.findItem(R.id.menu_item_share);

        if(shareItem != null) {
            shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        }

        SetShareIntent();

        return true;
    }

    private void SetShareIntent() {
        if(shareActionProvider != null)
        {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Android develop");
            shareIntent.putExtra(Intent.EXTRA_TEXT, mainTextView.getText());

            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public void onClick(View pView) {
        mainTextView.setText(mainEditText.getText().toString() + " added");
        if(mainEditText.getText().toString().equals("")) return;

        _arrayNameList.add(mainEditText.getText().toString());
        _arrayAdapter.notifyDataSetChanged();
        mainEditText.setText("");

    }

    @Override
    public void onItemClick(AdapterView<?   > parent, View view, int position, long id) {

        Log.d("", position + ": " + _arrayNameList.get(position));
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void OnTextviewClick(View v) {
        Log.d("", "test");
        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
    }

}
