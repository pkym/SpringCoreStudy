package hello.core.member;

public class MemberServiceImpl implements MemberService {
    // 추상화와 구체와 모두 의존하고 있는 문제가 있음
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public void join(Member member){
        memberRepository.save(member);
        //Override 한것이 호출됨(다형성)
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
