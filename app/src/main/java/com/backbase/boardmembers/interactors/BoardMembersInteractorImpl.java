package com.backbase.boardmembers.interactors;

import com.backbase.boardmembers.data.ServiceGenerator;
import com.backbase.boardmembers.data.api.MembersAPI;
import com.backbase.boardmembers.data.errorhandling.RetrofitException;
import com.backbase.boardmembers.models.MemberDetails;
import com.backbase.boardmembers.models.MembersResponseDTO;
import com.backbase.boardmembers.ui.AppConstants;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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


    public Observable<List<MemberDetails>> createObservable() {


        return membersAPI.fetchAllMembers().map(new Func1<MembersResponseDTO, List<MemberDetails>>() {

            @Override
            public List<MemberDetails> call(MembersResponseDTO membersResponseDTO) {
                List<MemberDetails> membersList = new ArrayList<>();
                if(membersResponseDTO.getLaunchpad().size()>0){
                    membersList.add(new MemberDetails(AppConstants.LAUNCHPAD_TEAM));
                }
                for(MemberDetails memberDetails : membersResponseDTO.getLaunchpad()){
                    memberDetails.setDepartment(AppConstants.LAUNCHPAD_TEAM);
                }
                membersList.addAll(membersResponseDTO.getLaunchpad());
                if(membersResponseDTO.getCXP().size()>0) {
                    membersList.add(new MemberDetails(AppConstants.CXP_TEAM));
                }
                for(MemberDetails memberDetails : membersResponseDTO.getCXP()){
                    memberDetails.setDepartment(AppConstants.CXP_TEAM);
                }
                membersList.addAll(membersResponseDTO.getCXP());
                if(membersResponseDTO.getMobile().size()>0){
                    membersList.add(new MemberDetails(AppConstants.MOBILE_TEAM));
                }
                for(MemberDetails memberDetails : membersResponseDTO.getMobile()){
                    memberDetails.setDepartment(AppConstants.MOBILE_TEAM);
                }
                membersList.addAll(membersResponseDTO.getMobile());

                return membersList;
            }
        });

    }


    @Override
    public void getAllBoardMembers(final OnFetchAllBoardMembers onFetchAllBoardMembers) {

        mCompositeSubscription.add(createObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MemberDetails>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(final Throwable exception) {

                        if(exception instanceof RetrofitException) {
                            RetrofitException error = (RetrofitException) exception;
                            if (error.getKind() == RetrofitException.Kind.NETWORK) {
                                onFetchAllBoardMembers.onErrorFetchingAllBoardMembers("" + error.getMessage());
                            }
                        }else{
                            onFetchAllBoardMembers.onErrorFetchingAllBoardMembers("" + exception.getMessage());

                        }
                    }

                    @Override
                    public void onNext(List<MemberDetails> memberDetailses) {

                        onFetchAllBoardMembers.onSuccessFetchingAllBoardMembers(memberDetailses);
                    }
                }));
    }


    public List<MemberDetails> getMembersTest(){

        List<MemberDetails> membersList = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++){

            membersList.add(new MemberDetails());
        }


        return membersList;
    }

    @Override
    public void unSubscribe() {

        if(null !=mCompositeSubscription && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
