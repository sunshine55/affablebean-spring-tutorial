package com.hvn.tutorial.affablebean.service;

import com.hvn.tutorial.affablebean.domain.Member;
import com.hvn.tutorial.affablebean.repository.MemberDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTests {

	/** {@link MemberService} instance under test */
	@InjectMocks
	private MemberService service;
	
	/**
	 * Mock objects declaration
	 */
	@Mock private MemberDao mockMemberDao;
	
	/**
	 * Method to perform setup work for each test.
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Method to perform tear down work for each test.
	 */
	@After
	public void tearDown() throws Exception {
		Mockito.reset(mockMemberDao);
	}

	/**
	 * Test {@link MemberService#getAll()}
	 */
	@Test
	public void getAll() {
		// given
		List<Member> memberList = Arrays.asList(new Member());
		Mockito.when(mockMemberDao.findAll()).thenReturn(memberList);
		// when
		List<Member> expectedList = service.getAll();
		// then
		assertThat(expectedList).isEqualTo(memberList);
		Mockito.verify(mockMemberDao).findAll();
	}

}
