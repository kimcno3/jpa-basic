package com.example.jpabasic;

import com.example.jpabasic.domain.Board;
import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
public class JpaBasicApplication {

	public static void main(String[] args) {
		// 엔티티 매니저 팩토리는 생성 비용이 크다(JPA 동작을 위한 기반 객체 생성, 구현체에 따라 디비 커넥션 풀 생성).
		// 그러므로 프로젝트 전체에서 하나만 생성해 공유하는 것을 추천
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		// 엔티티 매니저는 디비에 엔티티를 CRUD 할 수 있도록 한다.
		// 디비 커넥션과 밀접한 관계이므로 스레드간에 공유가 되어서는 안된다.
		EntityManager em = emf.createEntityManager();
		// JPA는 항상 트랜잭션 안에서 데이터가 변경되어야 한다.
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			logic6_2_1(em);
			tx.commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			tx.rollback();
		} finally {
			em.close(); // 반드시 종료
		}
		emf.close(); // 애플리케이션 종료 시 종료
	}

	// 6.2.1 일대다 단뱡항 관계 문제점
	private static void logic6_2_1(EntityManager em) {

		Team t1 = new Team("t1", "팀1");
		Member m1 = new Member("m1", "회원1"); // 외래키 주인

		t1.setMember(m1);

		em.persist(m1);
		em.persist(t1);

	}
	// 5.2.1 순수한 객체 연관관계
//	private static void logic5_2_1(EntityManager em) {
////		Member m1 = em.find(Member.class, "m1");
////		Member m2 = em.find(Member.class, "m2");
////
////		Team t1 = m1.getTeam();
////		Team t2 = m2.getTeam();
////
////		System.out.println(t1.getName() + ", " + t2.getName());
//
//
//	}
//
//	// 4.6.3. SEQUENCE 전략
//	private static void logic4_6_3(EntityManager em) {
//		Board board = new Board();
//		em.persist(board);
//		System.out.println("board id is " + board.getId());
//	}
//
//	// 3.4.3 엔티티 수정(변경 감지)
//	private static void logic3_4_3(EntityManager em) {
//		Member memberA = em.find(Member.class, "memberA");
//
//		memberA.setAge(20);
//
//		// em.update(member); 이런 코드가 있어야 할 것 같지만 없다.
//	}
//
//
//	// 3.4.2 엔티티 등록(쓰기 지연)
//	private static void logic3_4_2(EntityManager em) {
//		// 트랜잭션 객체 생성
//		//EntityTransaction transaction = em.getTransaction();
//		// 트랜잭션 시작
//		//transaction.begin();
//		// 회원A 생성
//		Member memberA = new Member();
//		memberA.setId("memberA");
//		memberA.setUsername("회원A");
//		// 회원B 생성
//		Member memberB = new Member();
//		memberB.setId("memberb");
//		memberB.setUsername("회원B");
//		// 영속 상태
//		em.persist(memberA);
//		em.persist(memberB);
//		// 커밋 : 해당 시점에 영속 상태의 객체들에 대한 INSERT 문이 실행된다.(쓰기 지연)
//		//transaction.commit();
//	}
//
//	// 3.4.1. 1차 캐시
//	private static void logic3_4_1(EntityManager em) {
//		// 비영속
//		Member member = new Member();
//		member.setId("member1");
//		member.setUsername("회원1");
//
//		// 영속 - 1차 캐시에 저장
//		em.persist(member);
//
//		// 1차 캐시에서 조회
//		Member findMember = em.find(Member.class, "member1");
//
//		// DB에서 조회 - 1차 캐시에 저장된 객체가 아니므로 DB에서 조회
//		Member findMember2 = em.find(Member.class, "member2");
//
//		// 동일성 보장 - 같은 객체 주소에서 조회해오므로 동일성이 보장된다.
//		// REPEATABLE READ 격리 수준을 어플리케이션 수준에서 제공
//		Member m1 = em.find(Member.class, "member1");
//		Member m2 = em.find(Member.class, "member1");
//		System.out.println(m1 == m2); // 같은 객제 주소를 가진다.
//
//	}
//
//	// 3.3 엔티티의 생명주기
//	private static void logic3_3(EntityManager em) {
//
//		// 1. 비영속 : 엔티티를 생성한 상태
//		Member member = new Member();
//		member.setId("member1");
//		member.setUsername("회원1");
//
//		// 2. 영속 : 엔티티를 PC에 저장한 상태
//		em.persist(member);
//
//		// 3-1. 준영속 : 엔티티를 PC와 분리한 상태
//		em.detach(member);
//
//		// 3-2. 아래 두 로직을 실행해도 준영속 상태가 된다.
//		em.close(); // PC 종료
//		em.clear(); // PC 초기화
//
//		// 4. 삭제 : 엔티티를 PC와 DB에서 살제한 상태
//		em.remove(member);
//	}
//
//	// 2.6
//	private static void logic2_6(EntityManager em) {
//
//		Member member = new Member();
//		member.setId("id1");
//		member.setUsername("선호");
//		member.setAge(28);
//		// 등록
//		em.persist(member);
//		// 수정
//		member.setAge(26);
//		// 한건 조회
//		Member findMember = em.find(Member.class, "id1");
//		log.info("findMember = {} , age = {}", findMember.getUsername(), findMember.getAge());
//		// 목록 조회
//		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
//		log.info("members size = {}", members.size());
//		// 삭제
//		em.remove(member);
//	}
}
