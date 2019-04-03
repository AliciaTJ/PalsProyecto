package e.alicia.pals.baseDatos;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import e.alicia.pals.modelo.Plan;
import e.alicia.pals.modelo.Usuario;

public class DataBaseUsuario {

    DatabaseReference db;
    Boolean saved=null;
    List<Plan> usuarios=new ArrayList<>();

    public DataBaseUsuario(DatabaseReference db) {
        this.db = db;
    }



    //----------------------usuarios
    //guardar
    public Boolean save(Usuario usuario)
    {
        if(usuario==null)
        {
            saved=false;
        }else {

            try
            {
                db.child("usuario").push().setValue(usuario);
                saved=true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }

        }

        return saved;
    }

    public Usuario buscar(String codigo){

        final Usuario[] usuario = new Usuario[1];
        Query q=db.equalTo(codigo);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                    System.out.println("jjjj");
                     usuario[0] =dataSnapshot.getValue(Usuario.class);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        } );
        return usuario[0];


}
}
