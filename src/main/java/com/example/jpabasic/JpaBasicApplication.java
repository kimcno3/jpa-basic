package com.example.jpabasic;

import com.example.jpabasic.domain.Member;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class JpaBasicApplication {

	public static void main(String[] args) {
		/* Entity Manger Factory 생성
		 * 엔티티 매니저 팩토리는 생성 비용이 크다(JPA 동작을 위한 기반 객체 생성, 구현체에 따라 디비 커넥션 풀 생성).
		 * 그러므로 프로젝트 전체에서 하나만 생성해 공유하는 것을 추천
		 */
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

		/* Entity Manager 생성
		 * 엔티티 매니저는 디비에 엔티티를 CRUD 할 수 있도록 한다.
		 * 디비 커넥션과 밀접한 관계이므로 스레드간에 공유가 되어서는 안된다.
		 */
		EntityManager em = emf.createEntityManager();

		/* JPA는 항상 트랜잭션 안에서 데이터가 변경되어야 한다. */
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin(); // 트랜잭션 시작
			logic(em); // ** 예제별 로직 호출 지점 **
			tx.commit(); // 트랜잭션 Commit
		} catch (Exception e) {
			log.error(e.getMessage());
			tx.rollback(); // RollBack
		} finally {
			em.close(); // 트랜잭션 종료
		}
		emf.close(); // EntityManagerFactory 종료(어플리케이션 종료 시)
	}

	private static void logic(EntityManager em) {
		Member member = new Member();
		member.setId(1);
		member.setUsername("김선호");
		em.persist(member);
	}
}
