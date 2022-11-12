package com.usermanagement.bean;

import static org.junit.Assert.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUserStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserIntStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCountry() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCountry() {
		fail("Not yet implemented");
	}

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}
	
	
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	HttpSession session;

	@Mock
	RequestDispatcher rd;

	@Before
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() throws Exception {
		when(request.getParameter("user")).thenReturn("abhinav");
		when(request.getParameter("password")).thenReturn("passw0rd");
		when(request.getParameter("rememberMe")).thenReturn("Y");
		when(request.getSession()).thenReturn(session);
		when(request.getRequestDispatcher("/HelloWorld.do")).thenReturn(rd);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		new Login().doPost(request, response);

		// Verify the session attribute value
		verify(session).setAttribute("user", "abhinav");

		verify(rd).forward(request, response);

		String result = sw.getBuffer().toString().trim();

		System.out.println("Result: " + result);

		assertEquals("Login successfull...", result);}

}
