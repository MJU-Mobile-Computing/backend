package com.mocum.domain.mypage.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MypageRes {

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
