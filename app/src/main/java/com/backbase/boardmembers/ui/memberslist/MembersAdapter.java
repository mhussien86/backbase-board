package com.backbase.boardmembers.ui.memberslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.backbase.boardmembers.models.MembersResponseDTO;

import java.util.List;

/**
 * Created by mohamed on 17/02/17.
 */
public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.ViewHolder> {

    public interface OnMemberClickListener {
        void onMemberClick(MembersResponseDTO.MemberDetails memberDetails);
    }


    public MembersAdapter(List countryList, OnMemberClickListener listener) {
//            this.countryList = countryList ;
//            this.listener = listener ;
    }

    @Override
    public MembersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MembersAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
