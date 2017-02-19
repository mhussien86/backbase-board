package com.backbase.boardmembers;

import com.backbase.boardmembers.interactors.BoardMembersInteractor;
import com.backbase.boardmembers.interactors.BoardMembersInteractorImpl;
import com.backbase.boardmembers.models.MembersResponseDTO;
import com.backbase.boardmembers.ui.memberslist.MembersListPresenter;
import com.backbase.boardmembers.ui.memberslist.MembersListPresenterImpl;
import com.backbase.boardmembers.ui.memberslist.MembersListView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mohamed on 19/02/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MembersListPresenterTest {

    @Mock
    MembersListView view;

    private MembersListPresenter membersListPresenter;

    @Mock
    private BoardMembersInteractor boardMembersInteractor;

    @Mock
    private BoardMembersInteractor.OnFetchAllBoardMembers onFetchAllBoardMembers ;


    @Before
    public void setUp()throws Exception{

        MockitoAnnotations.initMocks(this);
        view = mock(MembersListView.class);
        onFetchAllBoardMembers = mock(BoardMembersInteractor.OnFetchAllBoardMembers.class);
        boardMembersInteractor = mock(BoardMembersInteractorImpl.class);
        membersListPresenter = new MembersListPresenterImpl(view);

    }

    @Test
    public void verifyCallsWebServiceCalls()throws Exception{

        boardMembersInteractor.getMembersTest();
        verify(boardMembersInteractor).getMembersTest();
        boardMembersInteractor.unSubscribe();
        verify(boardMembersInteractor).unSubscribe();

    }


    @Test
    public void verifyUICalls(){

        List<MembersResponseDTO.MemberDetails> list = boardMembersInteractor.getMembersTest();
        view.setBoardMembersList(list);
        verify(view).setBoardMembersList(list);
    }


    @Test
    public void verifyErrorCalled(){
        view.showError("Error");
        verify(view).showError("Error");
    }

}
