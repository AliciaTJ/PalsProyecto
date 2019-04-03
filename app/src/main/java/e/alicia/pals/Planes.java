package e.alicia.pals;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import e.alicia.pals.baseDatos.DataBasePlan;
import e.alicia.pals.modelo.Plan;

public class Planes extends AppCompatActivity {
    private RecyclerView rv;
    ArrayList<Plan> planes;
    Adapter adapter;
    DataBasePlan database;
    EditText nameEditTxt,propTxt,descTxt;
    Context con=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        planes = new ArrayList<>();

        rv.setAdapter(adapter);

        FirebaseDatabase db;

        rv.setLayoutManager(new LinearLayoutManager(this));

        //SETUP FB
        db = FirebaseDatabase.getInstance();
        DatabaseReference dbr = db.getReference("planes");
        database = new DataBasePlan(dbr);

        //ADAPTER
        adapter = new Adapter(this, database.retrieve());
        rv.setAdapter(adapter);
        System.out.println(adapter.getItemCount());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     displayInputDialog();
            }
        });
    }
/*
    private void displayInputDialog() {
        Dialog d = new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.input_dialog);

        nameEditTxt= (EditText) d.findViewById(R.id.);
        propTxt= (EditText) d.findViewById(R.id.propellantEditText);
        descTxt= (EditText) d.findViewById(R.id.descEditText);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);
        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET DATA
                String name = nameEditTxt.getText().toString();

                //SET DATA
                Plan s = new Plan();
                s.setNombre(name);

                //VALIDATE
                if (name.length() > 0 && name != null) {
                    if (database.save(s)) {
                        nameEditTxt.setText("");
                        adapter = new Adapter(Planes.this, database.retrieve());
                        rv.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(Planes.this, "Name Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        d.show();
    }*/

}