package e.alicia.pals;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import e.alicia.pals.modelo.Plan;
public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder>{
    private List<Plan> mUserLsit=new ArrayList<>();
    private Context mContext;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new ItemViewHolder(view);
    }

    public Adapter(Context mContext,List<Plan> mUserLsit) {
        this.mContext=mContext;
        this.mUserLsit = mUserLsit;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Plan user=mUserLsit.get(position);
        holder.mTvName.setText(user.getFecha());
        holder.mTvEmail.setText(user.getNombre());
       // holder.mTvPwd.setText(user.getLugar());
    }

    @Override
    public int getItemCount() {
        return mUserLsit.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName,mTvEmail,mTvPwd;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mTvEmail=itemView.findViewById(R.id.list_desc);
            mTvName=itemView.findViewById(R.id.list_title);
         //   mTvPwd=itemView.findViewById(R.id.rlTvPwd);

        }
    }}