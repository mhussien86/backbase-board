package com.backbase.boardmembers.ui.memberslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.backbase.boardmembers.R;
import com.backbase.boardmembers.data.APIConstants;
import com.backbase.boardmembers.models.MembersResponseDTO;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mohamed on 17/02/17.
 */
public class MembersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_HEADER = 0;
    private final int VIEW_TYPE_ITEM = 1;
    List<MembersResponseDTO.MemberDetails> membersList;
    OnMemberClickListener onMemberClickListener;

    Context context ;
    public interface OnMemberClickListener {
        void onMemberClick(MembersResponseDTO.MemberDetails memberDetails);
    }

    public MembersAdapter(Context context , List<MembersResponseDTO.MemberDetails> membersList, OnMemberClickListener onMemberClickListener) {
        this.membersList = membersList;
        this.onMemberClickListener = onMemberClickListener;
        this.context = context ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder vh = null;
        if (viewType == VIEW_TYPE_HEADER) {
            View listHeader = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item_layout, parent, false);
            vh = new HeaderViewHolder(listHeader);
        } else if (viewType == VIEW_TYPE_ITEM) {
            View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list_item, parent, false);
            vh = new MemberViewHolder(listItem);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final MembersResponseDTO.MemberDetails memberDetails = membersList.get(position);
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.teamTitle.setText(memberDetails.getHeader());
        } else if (holder instanceof MemberViewHolder) {
            MemberViewHolder memberViewHolder = (MemberViewHolder) holder;
            memberViewHolder.memberName.setText(context.getString(R.string.member_full_name, memberDetails.getName(), memberDetails.getSurname()));
            Glide.with(context).load(APIConstants.IMAGE_SERVICE_PATH + memberDetails.getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.avatar).centerCrop().into(memberViewHolder.memberPhoto);
            memberViewHolder.bind(memberDetails, onMemberClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return membersList == null ? 0 : membersList.size();
    }


    @Override
    public int getItemViewType(int position) {

        if (membersList.get(position) != null && membersList.get(position).getHeader() != null) {
            return VIEW_TYPE_HEADER;
        } else if (membersList.get(position) != null && membersList.get(position).getHeader() == null) {
            return VIEW_TYPE_ITEM;
        } else {
            return -1;
        }
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemImg)
        CircleImageView memberPhoto ;

        @Bind(R.id.member_name)
        TextView memberName ;
        public MemberViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final MembersResponseDTO.MemberDetails memberDetails, final MembersAdapter.OnMemberClickListener onMemberClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onMemberClickListener.onMemberClick(memberDetails);
                }
            });
        }

    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.pageTitle)
        TextView teamTitle;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
