package com.backbase.boardmembers.data.api;

import com.backbase.boardmembers.models.MembersResponseDTO;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by mohamed on 17/02/17.
 */
public interface MembersAPI {

    @GET("members.php")
    Observable<MembersResponseDTO> fetchAllMembers();
}
