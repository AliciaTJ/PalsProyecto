package e.alicia.pals;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import e.alicia.pals.baseDatos.DataBasePlan;
import e.alicia.pals.modelo.Plan;

@RequiresApi(api = Build.VERSION_CODES.N)
public class PlanNuevo extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference dbr;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth fba;
    DataBasePlan db;
    EditText etInfo, etTitulo, etLugar;
    TextView etCreado;
    CalendarView cvFecha;
    Switch fechaIndiferente;
    int tipo;
    ImageView imagen;
    private TextInputLayout tilTitulo;
    private TextInputLayout tilInformacion;
    private TextInputLayout tilLugar;
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    TextView fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_nuevo);
        iniciarActivity();
        fechaIndiferente.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    obtenerFecha();
                }else{
                    fecha.setText("Indiferente");
                }
            }
        });


    }

    public void iniciarActivity() {
        tipo = getIntent().getIntExtra("id", 0);
        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbr = firebaseDatabase.getReference();
        imagen = findViewById(R.id.ivCabecera);
        db = new DataBasePlan(dbr);
        switch (tipo) {
            case 1:
                imagen.setImageResource(R.drawable.freak);
                break;
            case 2:
                imagen.setImageResource(R.drawable.cine2);
                break;
            case 3:
                imagen.setImageResource(R.drawable.musica);
                break;
            case 4:
                imagen.setImageResource(R.drawable.fiesta);
                break;
            case 5:
                imagen.setImageResource(R.drawable.otros);
                break;
            case 6:
                imagen.setImageResource(R.drawable.turismo);
                break;
            case 7:
                imagen.setImageResource(R.drawable.cultura);
                break;
            case 8:
                imagen.setImageResource(R.drawable.deportes);
                break;
            default:
                imagen.setImageResource(R.drawable.ic_menu_send);
        }
        etInfo = findViewById(R.id.informacion);
        etCreado=findViewById(R.id.tvCreador);
        etCreado.setText("Creado por: "+ user.getProviderId());
        etTitulo = findViewById(R.id.titulo);
        etLugar = findViewById(R.id.lugar);
        cvFecha = findViewById(R.id.cvFecha);
        etInfo.setText(user.getEmail());
        fechaIndiferente = findViewById(R.id.fechaIndiferente);
        tilInformacion = findViewById(R.id.tilinformacion);
        tilTitulo = findViewById(R.id.tiltitulo);
        tilLugar = findViewById(R.id.tillugar);
        fecha = findViewById(R.id.etFecha);


    }

    public void guardar(View view) {
        if (validarDatos()) {
            Plan plan = new Plan();
            plan.setInformacion(etInfo.getText().toString());
            plan.setFecha(fecha.getText().toString());

            plan.setCreador(user.getEmail());
            plan.setLugar(etLugar.getText().toString());
            plan.setMaximo("10");
            plan.setNombre(etTitulo.getText().toString());
            db.save(plan);
            verPlan();
        } else {

        }
    }


    public void verPlan() {
        Intent i = new Intent(this, VerPlan.class);
        startActivity(i);


    }


    private boolean esNombreValido(String nombre) {
        if (nombre.length() > 50) {
            tilTitulo.setError("Nombre demasiado largo");
            return false;
        } else {
            tilTitulo.setError(null);
        }

        return true;
    }
    private boolean esInfoValido(String nombre) {

        if (nombre.length()<10 || nombre.length() > 50) {
            tilTitulo.setError("Introduce información");
            return false;
        } else {
            tilTitulo.setError(null);
        }

        return true;
    }

    private boolean validarDatos() {
        String nombre = etTitulo.getText().toString();
        String telefono = etInfo.getText().toString();
        String correo = etLugar.getText().toString();

        boolean a = esNombreValido(nombre);


        if (a) {
            // OK, se pasa a la siguiente acción
            Toast.makeText(this, "Se guarda el registro", Toast.LENGTH_LONG).show();
            return true;
        } else {
            return false;
        }

    }


    private void obtenerFecha() {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? 0 + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? 0 + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                fecha.setText(diaFormateado + "/" + mesFormateado + "/" + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }
}


