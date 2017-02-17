package com.backbase.boardmembers.interactors;

import com.backbase.boardmembers.data.ServiceGenerator;
import com.backbase.boardmembers.data.api.MembersAPI;
import com.backbase.boardmembers.models.MembersResponseDTO;

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

                        onFetchAllBoardMembers.onSuccessFetchingAllBoardMembers(membersResponseDTO);
                    }

                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(final Throwable error) {

                        onFetchAllBoardMembers.onErrorFetchingAllBoardMembers(""+error.getLocalizedMessage());

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
