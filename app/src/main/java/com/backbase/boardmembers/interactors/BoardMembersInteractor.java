package com.backbase.boardmembers.interactors;

import com.backbase.boardmembers.models.MemberDetails;

import java.util.List;

/**
 * Created by mohamed on 17/02/17.
 */
public interface BoardMembersInteractor {

    void getAllBoardMembers(OnFetchAllBoardMembers onFetchAllBoardMembers);

    interface OnFetchAllBoardMembers{

        void onSuccessFetchingAllBoardMembers(List<MemberDetails> membersResponseDTO);

        void onErrorFetchingAllBoardMembers(String errorMessage);
    }
    List<MemberDetails> getMembersTest();
    void unSubscribe();
}
