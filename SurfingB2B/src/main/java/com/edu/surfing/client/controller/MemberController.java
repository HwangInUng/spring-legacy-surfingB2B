package com.edu.surfing.client.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.business.BusinessMember;
import com.edu.surfing.domain.member.Member;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;
import com.edu.surfing.model.member.JoinService;
import com.edu.surfing.model.member.MemberService;
import com.edu.surfing.model.util.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private final MemberService memberService; // 회원가입, 정보수정, 삭제 등의 서비스 지원
	private final JoinService joinService; // 회원가입 시 인증절차 서비스 지원

	@GetMapping("/join/email/{email:.+}/")
	public ResponseEntity<Message> sendAuthEmail(@PathVariable String email, HttpSession session) {
		log.debug("------ " + email + " 이메일 인증 요청 ------");

		int authCode = joinService.sendEmail(email);
		session.setAttribute("emailAuthCode", authCode);

		Message message = new Message();
		message.setMsg("이메일 전송완료, 이메일을 확인 후 인증번호를 입력해주세요.");

		log.debug("인증 코드는? " + authCode);
		log.debug("------ " + email + " 이메일 전송완료 ------");

		return ResponseEntity.ok(message);
	}

	@GetMapping("/join/email-auth/{emailCode}")
	public ResponseEntity<Message> checkEmailCode(@PathVariable String emailCode, HttpSession session) throws CustomException {
		log.debug("------ " + emailCode + " 인증번호 확인 요청 ------");

		int authCode = (Integer) session.getAttribute("emailAuthCode");
		log.debug("세션에서 불러온 코드는? " + authCode);

		Message message = new Message();
		if (Integer.parseInt(emailCode) == authCode) {
			message.setMsg("인증 성공");
			message.setCode(1);
			session.removeAttribute("emailAuthCode");
		} else {
			throw new CustomException(ErrorCode.NOT_FOUND_AUTHCODE, "메일");
		}

		log.debug("------ 인증번호 확인 완료 ------");
		return ResponseEntity.ok(message);
	}

	@GetMapping("/join/sms/{phoneNo}")
	public ResponseEntity<Message> sendAuthSMS(@PathVariable String phoneNo, HttpSession session) {
		log.debug("------ " + phoneNo + "로 인증문자 전송 요청 ------");

		int authCode = joinService.sendMessage(phoneNo);
		session.setAttribute("smsAuthCode", authCode);

		Message message = new Message();
		message.setMsg("문자 전송완료, 확인 후 인증번호를 입력해주세요.");

		log.debug("------ " + phoneNo + "로 인증문자 전송 완료 ------");
		return ResponseEntity.ok(message);
	}

	@GetMapping("/join/sms-auth/{smsCode}")
	public ResponseEntity<Message> checkSmsCode(@PathVariable String smsCode, HttpSession session) throws CustomException {
		log.debug("------ " + smsCode + " 인증번호 확인 요청 ------");

		int authCode = (Integer) session.getAttribute("smsAuthCode");
		log.debug("세션에서 불러온 코드는? " + authCode);

		Message message = new Message();
		if (Integer.parseInt(smsCode) == authCode) {
			message.setMsg("인증 성공");
			message.setCode(1);
			session.removeAttribute("smsAuthCode");
		} else {
			throw new CustomException(ErrorCode.NOT_FOUND_AUTHCODE, "문자");
		}

		log.debug("------ 인증번호 확인 완료 ------");
		return ResponseEntity.ok(message);
	}

	@GetMapping("/join/{memberId}")
	public ResponseEntity<Message> checkMemberId(@PathVariable String memberId) {
		log.debug("------ 아이디 중복체크 요청 ------");

		memberService.getMemberById(memberId);

		Message message = new Message();
		message.setMsg("사용가능한 아이디입니다.");

		log.debug("------ 아이디 중복체크 응답 ------");
		return ResponseEntity.ok(message);
	}

	@PostMapping("/join/member")
	public ResponseEntity<Message> regist(Member member, HttpServletRequest request) {
		log.debug("------ " + member.getMemberName() + "님 회원가입 요청 ------");
		String savePath = (String) request.getSession().getServletContext().getAttribute("savePath");

		memberService.registMember(member, savePath);

		Message message = new Message();
		message.setMsg("회원가입 성공");

		log.debug("------ " + member.getMemberName() + "님 회원가입 성공 ------");
		return ResponseEntity.ok(message);
	}

	@GetMapping("/token/mypage")
	public ResponseEntity<Member> accessMyPage(HttpServletRequest request) {
		log.debug("----- Mypage 접속 요청 ------");

		// interceptor로부터 전달받은 memberIdx
		Member member = (Member) request.getAttribute("member");

		log.debug("----- Mypage 접속 허용 ------");
		return ResponseEntity.ok(member);
	}
}
