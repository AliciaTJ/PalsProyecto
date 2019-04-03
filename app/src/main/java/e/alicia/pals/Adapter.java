package e.alicia.pals;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import e.alicia.pals.modelo.Plan;

public class Adapter extends RecyclerView.Adapter<MyViewHolder> {
    Context c;
    List<Plan> spacecrafts;

    public Adapter(Context c, List<Plan> spacecrafts) {
        this.c = c;
        this.notifyDataSetChanged();
        this.spacecrafts = spacecrafts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(c).inflate(R.layout.content_detail,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nameTxt.setText(spacecrafts.get(position).getFecha());
        holder.propTxt.setText(spacecrafts.get(position).getLugar());
        holder.descTxt.setText(spacecrafts.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }
}