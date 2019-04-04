package e.alicia.pals;

import android.content.Context;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import e.alicia.pals.baseDatos.DataBasePlan;
import e.alicia.pals.modelo.Plan;

public class Planes extends AppCompatActivity {
    private RecyclerView rv;
    ArrayList<Plan> planes;
    DataBasePlan database;
    Context con = this;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_planes);
        rv = findViewById(R.id.rv);
        planes = new ArrayList<>();

        FirebaseDatabase db;

        rv.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseDatabase.getInstance();
        DatabaseReference dbr = db.getReference("planes");
        database = new DataBasePlan(dbr);
        rv.setLayoutManager(new LinearLayoutManager(this));
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                planes.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Plan user = dataSnapshot1.getValue(Plan.class);
                    planes.add(user);
                }
                adapter = new Adapter(Planes.this, planes);
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                System.out.println(adapter.getItemCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
