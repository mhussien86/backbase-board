package com.backbase.boardmembers.ui.memberslist;

import com.backbase.boardmembers.interactors.BoardMembersInteractor;
import com.backbase.boardmembers.interactors.BoardMembersInteractorImpl;
import com.backbase.boardmembers.models.MemberDetails;

import java.util.List;

/**
 * Created by mohamed on 17/02/17.
 */
public class MembersListPresenterImpl implements MembersListPresenter, BoardMembersInteractor.OnFetchAllBoardMembers {

    MembersListView membersListView ;
    BoardMembersInteractor boardMembersInteractor ;

    public MembersListPresenterImpl(MembersListView membersListView){

        this.membersListView = membersListView ;
        boardMembersInteractor = new BoardMembersInteractorImpl();
    }

    @Override
    public void getBoardMembersList() {

        membersListView.showLoading();
        boardMembersInteractor.getAllBoardMembers(this);
    }

    @Override
    public void onDestroy() {

        boardMembersInteractor.unSubscribe();
        membersListView = null ;

    }

    @Override
    public void onSuccessFetchingAllBoardMembers(List<MemberDetails> memberDetailsList) {

        membersListView.hideLoading();
        membersListView.setBoardMembersList(memberDetailsList);
    }

    @Override
    public void onErrorFetchingAllBoardMembers(String errorMessage) {

        membersListView.hideLoading();
        membersListView.showError(errorMessage);

    }
}
