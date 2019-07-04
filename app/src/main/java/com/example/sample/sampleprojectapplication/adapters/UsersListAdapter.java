package com.example.sample.sampleprojectapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sample.sampleprojectapplication.R;
import com.example.sample.sampleprojectapplication.model.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by azimmemon on 02/07/19.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UsersHolder>  {

    private Context mContext;
    private List<UserModel> mUsersList;
    private List<UserModel> mUsersCopyList;

    public UsersListAdapter(Context context, List<UserModel> mUsersList){
        this.mContext = context;
        this.mUsersList = mUsersList;
        this.mUsersCopyList = new ArrayList<>();
        this.mUsersCopyList.addAll(mUsersList);
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.user_single_view, parent, false);

        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
        holder.name.setText(mUsersList.get(position).getName());
        holder.nodeId.setText(mUsersList.get(position).getNodeId());
        Picasso.with(mContext)
                .load(mUsersList.get(position).getImageUrl())
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    public class UsersHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_image)
        CircleImageView mImage;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.node_id)
        TextView nodeId;

        public UsersHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void filterData(String query){
        mUsersList.clear();
        if (query.length() == 0){
            mUsersList.addAll(mUsersCopyList);
        }else {
            for (UserModel model: mUsersCopyList){
                if (model.getName().toLowerCase().contains(query.toLowerCase())){
                    mUsersList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
