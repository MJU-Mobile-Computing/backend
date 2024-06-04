package com.mocum.domain.mypage.application;

import com.mocum.domain.mypage.dto.MypageEditReq;
import com.mocum.domain.mypage.dto.MypageRes;
import com.mocum.domain.user.domain.User;
import com.mocum.domain.user.domain.repository.UserRepository;
import com.mocum.global.payload.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MypageService {

    private final UserRepository userRepository;

    @Transactional
    public MypageRes viewMypage() {

        User user = userRepository.findById(1L).orElseThrow(NullPointerException::new);

        return MypageRes.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .gender(user.getGender())
                .birthdate(user.getBirthdate())
                .height(user.getHeight())
                .goal(user.getGoal())
                .weight(user.getWeight())
                .amountOfActivity(user.getAmountOfActivity())
                .goalWeight(user.getGoalWeight())
                .goalSteps(user.getGoalSteps())
                .build();
    }

    @Transactional
    public Message editMypage(MypageEditReq mypageEditReq) {

        User user = userRepository.findById(1L).orElseThrow(NullPointerException::new);

        user.updateUserInfo(
                mypageEditReq.getFirstname(),
                mypageEditReq.getLastname(),
                mypageEditReq.getGender(),
                mypageEditReq.getBirthdate(),
                mypageEditReq.getHeight(),
                mypageEditReq.getGoal(),
                mypageEditReq.getWeight(),
                mypageEditReq.getAmountOfActivity(),
                mypageEditReq.getGoalWeight(),
                mypageEditReq.getGoalSteps()
        );



        return Message.builder()
                .message("내 정보가 수정되었습니다.")
                .build();
    }
}
