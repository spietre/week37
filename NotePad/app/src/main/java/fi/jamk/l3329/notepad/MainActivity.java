package fi.jamk.l3329.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> notes;
    private static final int EDIT_NOTE = 1;
    private static final int NEW_NOTE = 2;
    private static int last_edited;
    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        // find list view
        listview = (ListView) findViewById(R.id.list_view);

        // generate some dummy data
//            String[] notes = new String[]{
//                    "Here you can add new notes..", "Or create your shopping list.."
//            };

        notes = new ArrayList<>();
        notes.add("Here you can add new notes..");
        notes.add("Or create your shopping list..");

//        // add data to ArrayList
//        ArrayList<String> list = new ArrayList<String>();
//        for (int i = 0; i < notes.size(); ++i) {
//            list.add(notes.get(i));
//        }


        // add data to ArrayAdapter (default Android ListView style/layout)
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);

        // set data to listView with adapter
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get list row data (now String as a phone name)
                last_edited = position;
                String note = notes.get(position);
                // create an explicit intent
                Intent intent = new Intent(MainActivity.this, NoteDetail.class);
                // add data to intent
                intent.putExtra("note", note);

                // start a new activity
                //startActivity(intent);

                //startActivity(intent);
                startActivityForResult(intent, EDIT_NOTE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_NOTE){
            if (resultCode == 30) {
                Bundle bundle = data.getExtras();
                String note = bundle.getString("note");
                notes.set(last_edited,note);
            } else if (resultCode == 60){
                notes.remove(last_edited);
                Toast.makeText(getBaseContext(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
            listview.setAdapter(adapter);
        } else if (requestCode == NEW_NOTE){
            if (resultCode == 30) {
                Bundle bundle = data.getExtras();
                String note = bundle.getString("note");
                notes.add(note);
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
                listview.setAdapter(adapter);
                Toast.makeText(getBaseContext(), "New note created", Toast.LENGTH_SHORT).show();
            } else if (resultCode == 60){
                Toast.makeText(getBaseContext(), "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_note:
                newNote();
                return true;
            case R.id.exit_application:
                Toast.makeText(getBaseContext(), "Quit", Toast.LENGTH_SHORT).show();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addNote(String note, ArrayList<String> notes) {
        notes.add(note);

    }

    public void newNote() {

        Intent intent = new Intent(MainActivity.this, NoteDetail.class);
        intent.putExtra("note", "");

        startActivityForResult(intent, NEW_NOTE);
    }



}
