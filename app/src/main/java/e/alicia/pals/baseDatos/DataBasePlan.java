package e.alicia.pals.baseDatos;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import e.alicia.pals.modelo.Plan;
import e.alicia.pals.modelo.Usuario;

public class DataBasePlan {

    DatabaseReference db;
    Boolean saved=null;
    List<Plan> planes=new ArrayList<>();

    public DataBasePlan(DatabaseReference db) {
        this.db = db;
    }

    //---------------------------planes

    //guardar
    public Boolean save(Plan plan)
    {
        if(plan==null)
        {
            saved=false;
        }else {

            try
            {
                db.child("planes").push().setValue(plan);
                saved=true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }

        }

        return saved;
    }

    //leer
    public List<Plan> retrieve()
    {
        db.child("planes").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return planes;
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {

            Plan plan =ds.getValue(Plan.class);
            planes.add(plan);
        }


    }


}
