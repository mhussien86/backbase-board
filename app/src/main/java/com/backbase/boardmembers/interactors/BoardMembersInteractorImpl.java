package com.backbase.boardmembers.interactors;

import com.backbase.boardmembers.data.ServiceGenerator;
import com.backbase.boardmembers.data.api.MembersAPI;
import com.backbase.boardmembers.data.errorhandling.RetrofitException;
import com.backbase.boardmembers.models.MembersResponseDTO;
import com.backbase.boardmembers.ui.AppConstants;

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

                        onFetchAllBoardMembers.onSuccessFetchingAllBoardMembers(handleReturnedData(membersResponseDTO));
                    }

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
                }));

    }

    public List<MembersResponseDTO.MemberDetails> getMembersTest(){

        List<MembersResponseDTO.MemberDetails> membersList = new ArrayList<MembersResponseDTO.MemberDetails>();

        for(int i = 0 ; i < 5 ; i++){

            membersList.add(new MembersResponseDTO.MemberDetails());
        }


        return membersList;
    }
    public List<MembersResponseDTO.MemberDetails> handleReturnedData(MembersResponseDTO membersResponseDTO){

        List<MembersResponseDTO.MemberDetails> membersList = new ArrayList<MembersResponseDTO.MemberDetails>();
        membersList.add(new MembersResponseDTO.MemberDetails(AppConstants.CXP_TEAM));
        for(MembersResponseDTO.MemberDetails memberDetails : membersResponseDTO.getCXP()){
            memberDetails.setDepartment(AppConstants.CXP_TEAM);
        }
        membersList.addAll(membersResponseDTO.getCXP());
        membersList.add(new MembersResponseDTO.MemberDetails(AppConstants.LAUNCHPAD_TEAM));
        for(MembersResponseDTO.MemberDetails memberDetails : membersResponseDTO.getLaunchpad()){
            memberDetails.setDepartment(AppConstants.LAUNCHPAD_TEAM);
        }
        membersList.addAll(membersResponseDTO.getLaunchpad());
        membersList.add(new MembersResponseDTO.MemberDetails(AppConstants.MOBILE_TEAM));
        for(MembersResponseDTO.MemberDetails memberDetails : membersResponseDTO.getMobile()){
            memberDetails.setDepartment(AppConstants.MOBILE_TEAM);
        }
        membersList.addAll(membersResponseDTO.getMobile());

        return membersList;
    }

    @Override
    public void unSubscribe() {

        if(null !=mCompositeSubscription && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
