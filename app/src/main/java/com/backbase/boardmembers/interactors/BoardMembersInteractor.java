package com.backbase.boardmembers.interactors;

import com.backbase.boardmembers.models.MembersResponseDTO;

import java.util.List;

/**
 * Created by mohamed on 17/02/17.
 */
public interface BoardMembersInteractor {

    void getAllBoardMembers(OnFetchAllBoardMembers onFetchAllBoardMembers);

    interface OnFetchAllBoardMembers{

        void onSuccessFetchingAllBoardMembers(List<MembersResponseDTO.MemberDetails> membersResponseDTO);

        void onErrorFetchingAllBoardMembers(String errorMessage);
    }
    List<MembersResponseDTO.MemberDetails> getMembersTest();
    void unSubscribe();
}
