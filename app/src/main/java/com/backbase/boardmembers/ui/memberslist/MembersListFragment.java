package com.backbase.boardmembers.ui.memberslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backbase.boardmembers.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mohamed on 17/02/17.
 */
public class MembersListFragment extends Fragment {



    @Bind(R.id.layout_loading)
    View mLoadingLayout ;

    View rootView ;
//    ProductsListPresenter productsListPresenter ;

//    ProductsListAdapter productsListAdapter ;

    ButterKnife binder ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView  = inflater.inflate(R.layout.members_list_fragment, container , false);
        binder.bind(this,rootView);

        return rootView;
    }


}
