package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) { // 순수 자바 코드 자바 메서드로 개발 한 코드

        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memeberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());

        // app로직을 테스트 하는 것 좋은 방법 X >> JUnit 테스트 이용

    }
}
