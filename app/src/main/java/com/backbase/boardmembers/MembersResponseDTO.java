package com.backbase.boardmembers;

import java.util.List;

/**
 * Created by mohamed on 17/02/17.
 */
public class MembersResponseDTO {

    List<MemberDetails> CXP;

    List<MemberDetails> Mobile;

    List<MemberDetails> Launchpad;

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


    /**
     * Created by mohamed on 17/02/17.
     */
    public static class MemberDetails {


        public String name;

        public String surname;

        public String email;

        public String photo;

        public String role;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

    }
}