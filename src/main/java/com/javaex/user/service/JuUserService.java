package com.javaex.user.service;

import com.javaex.user.model.JuLoginVo;
import com.javaex.user.model.JuUserVo;
import com.javaex.user.repository.JuUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JuUserService {

    @Autowired
    private JuUserDao juDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* 이메일 중복체크 */
    public boolean exeEmailCheck(String email) {
        int count = juDao.selectUserByEmail(email);

        if (count >= 1) {
            return false; // 이미 가입된 이메일 가입불가능
        } else {
            return true; // 없는 이메일 가입가능
        }

    }

    /* 닉네임 중복체크 */
    public boolean exeNicknameCheck(String nickname) {
        int count = juDao.selectUserByNickname(nickname);

        if (count >= 1) {
            return false; // 이미 가입된 이메일 가입불가능
        } else {
            return true; // 없는 이메일 가입가능
        }

    }

    /* 유저 회원가입 */
    public int exeUserSignUp(JuUserVo juUserVo) {
        // ✅ 비밀번호 해시 처리
        String rawPassword = juUserVo.getPassword_hash();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        juUserVo.setPassword_hash(encodedPassword); // 암호화된 값 저장

        // Step 1: Member 테이블에 삽입
        int memberInsertCount = juDao.insertMember(juUserVo);

        if (memberInsertCount != 1) {
            return 0;
        }

        int memberId = juUserVo.getMember_id();
        juUserVo.setMember_id(memberId);
        juUserVo.setUser_provider("일반");

        return juDao.insertUser(juUserVo);
    }


    /* 업체 회원가입 */
    public int exeVenderSignUp(JuUserVo juUserVo) {
        // ✅ 비밀번호 해시 처리
        String rawPassword = juUserVo.getPassword_hash();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        juUserVo.setPassword_hash(encodedPassword); // 암호화된 값 저장

        int memberInsertCount = juDao.insertMemberVender(juUserVo);
        if (memberInsertCount != 1) return 0;

        int memberId = juUserVo.getMember_id();
        juUserVo.setMember_id(memberId);

        return juDao.insertVender(juUserVo);
    }

    /* 로그인 */
    public JuLoginVo exeLogin(JuLoginVo juLoginVo) {
        JuLoginVo savedUser = juDao.selectUser(juLoginVo);

        // ✅ 비밀번호 비교 (해시된 값과 비교)
        if (savedUser != null && passwordEncoder.matches(juLoginVo.getPassword_hash(), savedUser.getPassword_hash())) {
            if ("업체".equals(savedUser.getType()) && !savedUser.isIs_active()) {
                savedUser.setIs_active(false);
            }
            return savedUser;
        }

        return null;
    }

    /* 휴대폰번호 중복체크 */
    public boolean exePhoneNumberCheck(String phone_number) {
        int count = juDao.selectUserByPhoneNumber(phone_number);

        if (count >= 1) {
            return false; // 이미 가입된 이메일 가입불가능
        } else {
            return true; // 없는 이메일 가입가능
        }
    }

    /* 소셜 회원가입 */
    public int exeSocialSignUp(JuUserVo juUserVo) {
        // Step 1: Member 테이블에 삽입
        int memberInsertCount = juDao.insertMember2(juUserVo);

        if (memberInsertCount != 1) {
            return 0; // 실패시 0 반환
        }

        // Step 2: 생성된 member_id 가져오기
        int memberId = juUserVo.getMember_id(); // MyBatis의 useGeneratedKeys 설정으로 자동으로 설정됨
        // Step 3: User 테이블에 삽입
        juUserVo.setMember_id(memberId); // 외래키로 사용될 member_id 설정

        return juDao.insertUser2(juUserVo); // 성공시 1 반환
    }

    /* 소셜 로그인 */
    public JuLoginVo exeSocialLogin(String email) {
        JuLoginVo authUser = juDao.selectUser2(email);

        return authUser;
    }

}
