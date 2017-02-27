package com.backbase.boardmembers.models;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by mohamed on 17/02/17.
 */
@Parcel
public class MembersResponseDTO {

    public List<MemberDetails> CXP;

    public List<MemberDetails> Mobile;

    public List<MemberDetails> Launchpad;

    public List<MemberDetails> getCXP() {
        return CXP;
    }

    public void setCXP(List<MemberDetails> CXP) {
        this.CXP = CXP;
    }

    public List<MemberDetails> getLaunchpad() {
        return Launchpad;
    }

    public void setLaunchpad(List<MemberDetails> launchpad) {
        Launchpad = launchpad;
    }

    public List<MemberDetails> getMobile() {
        return Mobile;
    }

    public void setMobile(List<MemberDetails> mobile) {
        Mobile = mobile;
    }


}
