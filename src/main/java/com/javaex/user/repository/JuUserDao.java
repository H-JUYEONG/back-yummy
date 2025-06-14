package com.javaex.user.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.user.model.JuAnniversaryVo;
import com.javaex.user.model.JuLoginVo;
import com.javaex.user.model.JuUserVo;

@Repository
public class JuUserDao {

	@Autowired
	private SqlSession sqlSession;

	/* 이메일 중복체크 */
	public int selectUserByEmail(String email) {
		int count = sqlSession.selectOne("user.selectByEmail", email);

		return count;
	}
	
	/* 이메일 중복체크(소셜 회원가입시) */
	public int selectSocialUserByEmail(String email, String user_provider) {
		Map<String, Object> params = new HashMap<>();
	    params.put("email", email);
	    params.put("user_provider", user_provider);
		int count = sqlSession.selectOne("user.selectSocialByEmail", params);

		return count;
	}

	/* 닉네임 중복체크 */
	public int selectUserByNickname(String nickname) {
		int count = sqlSession.selectOne("user.selectByNickname", nickname);

		return count;
	}

	/* Member 테이블에 데이터 삽입 */
	public int insertMember(JuUserVo juUserVo) {
		return sqlSession.insert("user.insertMember", juUserVo);
	}

	/* User 테이블에 데이터 삽입 */
	public int insertUser(JuUserVo juUserVo) {
		return sqlSession.insert("user.insertUser", juUserVo);
	}

	/* 업체 Member 테이블에 데이터 삽입 */
	public int insertMemberVender(JuUserVo juUserVo) {
		return sqlSession.insert("user.insertMemberVender", juUserVo);
	}

	/* Vender 테이블에 데이터 삽입 */
	public int insertVender(JuUserVo juUserVo) {
		return sqlSession.insert("user.insertVender", juUserVo);
	}

	/* 로그인 */
	public JuLoginVo selectUser(JuLoginVo juLoginVo) {
		JuLoginVo authUser = sqlSession.selectOne("user.selectByIdPw", juLoginVo);
		return authUser;
	}

	/* Member 테이블에 데이터 삽입2 */
	public int insertMember2(JuUserVo juUserVo) {
		return sqlSession.insert("user.insertMember2", juUserVo);
	}

	/* User 테이블에 데이터 삽입2 */
	public int insertUser2(JuUserVo juUserVo) {
		return sqlSession.insert("user.insertUser2", juUserVo);
	}

	/* 소셜 로그인 */
	public JuLoginVo selectUser2(String email) {
		JuLoginVo authUser = sqlSession.selectOne("user.selectByIdPw2", email);
		return authUser;
	}

	/* 휴대폰번호 중복체크 */
	public int selectUserByPhoneNumber(String phone_number) {
		int count = sqlSession.selectOne("user.selectByPhoneNumber", phone_number);

		return count;
	}

	/* 기념일 문자 알림 */
	// 오늘 알림이 설정된 기념일 조회
	public List<JuAnniversaryVo> getNotificationsForToday() {
		return sqlSession.selectList("user.notificationsForToday");
	}

	// SMS 이력 저장
	public void insertSmsHistory(int userId, int anniversaryId, String phone, String message, String status) {
		JuAnniversaryVo history = new JuAnniversaryVo();
		history.setUserId(userId);
		history.setAnniversaryId(anniversaryId);
		history.setPhone(phone);
		history.setMessage(message);
		history.setStatus(status);
		sqlSession.insert("user.insertSmsHistory", history);
	}
}
