package fi.jamk.l3329.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NoteDetail extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        String note = bundle.getString("note");


        EditText et = (EditText) findViewById(R.id.textFieldNote);

        et.append(note);
//        TextView textView = (TextView) findViewById(R.id.phoneTextView);
//        textView.setText(phone);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                Intent intent = new Intent();
                EditText et = (EditText) findViewById(R.id.textFieldNote);
                intent.putExtra("note", et.getText().toString());
                setResult(30, intent);
                finish();
                return true;
            case R.id.delete_this_note:
                setResult(60);
                finish();
                return true;
            case R.id.exit_application:
                Toast.makeText(getBaseContext(), "Canceled", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveButtonPressed(View view) {

        finish();
    }

    public void deleteOrExitButtonPressed(View view) {
        // finish and close activity
        finish();
    }


}
