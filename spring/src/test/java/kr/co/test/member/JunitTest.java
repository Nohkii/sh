package kr.co.test.member;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JunitTest {

	/*
	 * 테스트 순서
	 * Before -> Test -> After (각각)
	 * BeforeClass -> Test -> AfterClass (전체)
	 */
	@Test
	public void test() {
		String name = "홍길동";
		assertEquals(name, "홍길동1");
		System.out.println("테스트");
	}
	@Test
	public void test2() {
		System.out.println("테스트2");
	}
	@Before
	public void before() {
		System.out.println("앞에");
	}
	@After
	public void after() {
		System.out.println("뒤에");
	}
	@BeforeClass
	public static void beforeOne() {
		System.out.println("처음");
	}
	@AfterClass
	public static void afterOne() {
		System.out.println("끝");
	}
}
