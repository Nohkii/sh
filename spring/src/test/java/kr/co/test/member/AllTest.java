package kr.co.test.member;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// 통합테스트
@RunWith(Suite.class)
@SuiteClasses({MemberTest.class, MemberTest2.class})
public class AllTest {

}
