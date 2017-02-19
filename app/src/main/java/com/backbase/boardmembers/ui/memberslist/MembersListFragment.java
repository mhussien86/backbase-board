package com.backbase.boardmembers.ui.memberslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backbase.boardmembers.R;
import com.backbase.boardmembers.models.MembersResponseDTO;
import com.backbase.boardmembers.ui.AppConstants;
import com.backbase.boardmembers.ui.memberdetails.MemberDetailsFragment;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mohamed on 17/02/17.
 */
public class MembersListFragment extends Fragment implements MembersListView{

    @Bind(R.id.layout_loading)
    View loadingLayout;

    @Bind(R.id.error_layout)
    View errorLayout ;

    View rootView ;

    @Bind(R.id.members_recycle_view)
    RecyclerView membersRecycleView ;

    MembersListPresenter membersListPresenter ;

    MembersAdapter membersAdapter ;

    ButterKnife binder ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView  = inflater.inflate(R.layout.members_list_fragment, container , false);
        binder.bind(this,rootView);
        membersListPresenter = new MembersListPresenterImpl(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        membersListPresenter.getBoardMembersList();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        membersRecycleView.setLayoutManager(llm);
        membersRecycleView.setHasFixedSize(true);
    }

    @Override
    public void showLoading() {

        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {

        errorLayout.setVisibility(View.VISIBLE);
        Snackbar.make(getView(),getString(R.string.something_wrong),Snackbar.LENGTH_INDEFINITE).setAction(R.string.retry_text, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(membersListPresenter!=null) {
                    errorLayout.setVisibility(View.GONE);
                    membersListPresenter.getBoardMembersList();
                }
            }
        }).show();
    }

    @Override
    public void setBoardMembersList(List<MembersResponseDTO.MemberDetails> membersList) {

        if(errorLayout!=null && errorLayout.getVisibility()==View.VISIBLE){
            errorLayout.setVisibility(View.GONE);
        }
        membersAdapter = new MembersAdapter(getContext(),membersList, new MembersAdapter.OnMemberClickListener() {
            @Override
            public void onMemberClick(MembersResponseDTO.MemberDetails memberDetails) {
                MemberDetailsFragment bottomSheetDialogFragment = new MemberDetailsFragment();
                Bundle args = new Bundle();
                args.putParcelable(AppConstants.MEMBER_DETAILS, Parcels.wrap(memberDetails));
                bottomSheetDialogFragment.setArguments(args);
                bottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });
        membersRecycleView.setAdapter(membersAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        membersListPresenter.onDestroy();
        binder.unbind(this);
    }

}
