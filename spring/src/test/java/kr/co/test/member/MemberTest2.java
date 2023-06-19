package kr.co.test.member;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {config.MvcConfig.class})
public class MemberTest2 {

	@Autowired
	WebApplicationContext ctx;
	
	MockMvc mockMvc;
	
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void memberIndex() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/member/index.do");
		mockMvc.perform(req);
	}
	
	// 파라미터가 있는 경우
	@Test
	public void memberIndex2() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders
				.get("/member/index.do")
				.param("searchWord", "h");
		mockMvc.perform(req);
	}
	
}
