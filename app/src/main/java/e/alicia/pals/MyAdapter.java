package e.alicia.pals;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import e.alicia.pals.modelo.Plan;
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context c;
    ArrayList<Plan> spacecrafts;

    public MyAdapter(Context c, ArrayList<Plan> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final  Plan s=spacecrafts.get(position);

        holder.nameTxt.setText(s.getNombre());
        holder.propTxt.setText(s.getFecha());
        holder.descTxt.setText(s.getInformacion());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                //OPEN DETAI ACTIVITY
                openDetailActivity(s.getNombre(),s.getInformacion(),s.getFecha());
            }
        });
    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }

    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c,DetailActivity.class);

        i.putExtra("NAME_KEY",details[0]);
        i.putExtra("DESC_KEY",details[1]);
        i.putExtra("PROP_KEY",details[2]);

        c.startActivity(i);
    }
}




















