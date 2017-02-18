package com.backbase.boardmembers.ui.memberslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backbase.boardmembers.R;
import com.backbase.boardmembers.interactors.BoardMembersInteractor;
import com.backbase.boardmembers.interactors.BoardMembersInteractorImpl;
import com.backbase.boardmembers.models.MembersResponseDTO;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mohamed on 17/02/17.
 */
public class MembersListFragment extends Fragment {



    @Bind(R.id.layout_loading)
    View mLoadingLayout ;

    View rootView ;

    @Bind(R.id.members_recycle_view)
    RecyclerView membersRecycleView ;

//    ProductsListPresenter productsListPresenter ;

//    ProductsListAdapter productsListAdapter ;

    ButterKnife binder ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView  = inflater.inflate(R.layout.members_list_fragment, container , false);
        binder.bind(this,rootView);

        BoardMembersInteractor interactor = new BoardMembersInteractorImpl();
        interactor.getAllBoardMembers(new BoardMembersInteractor.OnFetchAllBoardMembers() {
            @Override
            public void onSuccessFetchingAllBoardMembers(List<MembersResponseDTO.MemberDetails> membersResponseDTO) {

            }

            @Override
            public void onErrorFetchingAllBoardMembers(String errorMessage) {

                Log.e("Message", errorMessage);
            }
        });
        return rootView;
    }


}
