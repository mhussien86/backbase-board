package com.backbase.boardmembers.ui.memberslist;

import com.backbase.boardmembers.models.MembersResponseDTO;

import java.util.List;

/**
 * Created by mohamed on 17/02/17.
 */

public interface MembersListView {

    void showLoading();

    void hideLoading();

    void showError(String errorMessage);

    void setBoardMembersList(List<MembersResponseDTO.MemberDetails> membersList);

}
