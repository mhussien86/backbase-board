package com.backbase.boardmembers.interactors;

import com.backbase.boardmembers.models.MembersResponseDTO;

/**
 * Created by mohamed on 17/02/17.
 */
public interface BoardMembersInteractor {

    void getAllBoardMembers(OnFetchAllBoardMembers onFetchAllBoardMembers);

    interface OnFetchAllBoardMembers{

        void onSuccessFetchingAllBoardMembers(MembersResponseDTO membersResponseDTO);

        void onErrorFetchingAllBoardMembers(String errorMessage);
    }

    void unSubscribe();
}
