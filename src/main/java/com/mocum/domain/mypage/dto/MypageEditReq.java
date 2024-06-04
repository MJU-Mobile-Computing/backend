package com.mocum.domain.mypage.dto;

import lombok.Data;

@Data
public class MypageEditReq {
    private String firstname;
    private String lastname;
    private String gender;
    private String birthdate;
    private String height;
    private String goal;
    private String weight;
    private String amountOfActivity;
    private String goalWeight;
    private String goalSteps;
}
