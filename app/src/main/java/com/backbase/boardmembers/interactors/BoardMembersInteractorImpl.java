package com.backbase.boardmembers.interactors;

import com.backbase.boardmembers.data.ServiceGenerator;
import com.backbase.boardmembers.data.api.MembersAPI;
import com.backbase.boardmembers.data.errorhandling.RetrofitException;
import com.backbase.boardmembers.models.MembersResponseDTO;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mohamed on 17/02/17.
 */
public class BoardMembersInteractorImpl implements BoardMembersInteractor {


    MembersAPI membersAPI;
    final CompositeSubscription mCompositeSubscription;

    public BoardMembersInteractorImpl() {

        membersAPI = new ServiceGenerator().createService(MembersAPI.class);
        mCompositeSubscription = new CompositeSubscription();
    }


    @Override
    public void getAllBoardMembers(final OnFetchAllBoardMembers onFetchAllBoardMembers) {


        Observable<MembersResponseDTO> observable = membersAPI.fetchAllMembers();

        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MembersResponseDTO>() {
                    @Override
                    public void onNext(MembersResponseDTO membersResponseDTO) {

                        List<MembersResponseDTO.MemberDetails> membersList = new ArrayList<MembersResponseDTO.MemberDetails>();
                        membersList.add(new MembersResponseDTO.MemberDetails("CXP"));
                        membersList.addAll(membersResponseDTO.getCXP());
                        membersList.add(new MembersResponseDTO.MemberDetails("Launchpad"));
                        membersList.addAll(membersResponseDTO.getLaunchpad());
                        membersList.add(new MembersResponseDTO.MemberDetails("Mobile"));
                        membersList.addAll(membersResponseDTO.getMobile());
                        onFetchAllBoardMembers.onSuccessFetchingAllBoardMembers(membersList);
                    }

                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(final Throwable exception) {

                        RetrofitException  error = (RetrofitException) exception;
                        if(error.getKind() == RetrofitException.Kind.NETWORK){
                            onFetchAllBoardMembers.onErrorFetchingAllBoardMembers(""+ error.getLocalizedMessage());
                        }

                    }
                }));

    }

    @Override
    public void unSubscribe() {

        if(null !=mCompositeSubscription && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
