package com.mocum.domain.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name") //이름
    private String firstname;

    @Column(name = "last_name") // 성
    private String lastname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    private String birthdate;

    @Column(name = "height")
    private String height;

    @Column(name = "goal")
    private String goal;

    @Column(name = "weight")
    private String weight;

    @Column(name = "amount_of_activity")
    private String amountOfActivity;

    @Column(name = "goal_weight")
    private String goalWeight;

    @Column(name = "goal_steps")
    private String goalSteps;


    @Builder
    public User(String firstname, String lastname, String gender, String birthdate, String height, String goal, String weight, String amountOfActivity, String goalWeight, String goalSteps) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.height = height;
        this.goal = goal;
        this.weight = weight;
        this.amountOfActivity = amountOfActivity;
        this.goalWeight = goalWeight;
        this.goalSteps = goalSteps;
    }

    public void updateUserInfo(String firstname, String lastname, String gender, String birthdate, String height, String goal, String weight, String amountOfActivity, String goalWeight, String goalSteps) {
        if (firstname != null) this.firstname = firstname;
        if (lastname != null) this.lastname = lastname;
        if (gender != null) this.gender = gender;
        if (birthdate != null) this.birthdate = birthdate;
        if (height != null) this.height = height;
        if (goal != null) this.goal = goal;
        if (weight != null) this.weight = weight;
        if (amountOfActivity != null) this.amountOfActivity = amountOfActivity;
        if (goalWeight != null) this.goalWeight = goalWeight;
        if (goalSteps != null) this.goalSteps = goalSteps;
    }
}
