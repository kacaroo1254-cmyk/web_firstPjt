package com.office.ex;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("*.mem")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MemberService memberService = new MemberService();

    public MemberController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		String nextPage = null;
		
		switch (command) {
		case "/signup.mem":
			
			nextPage = "view/member/signup_form.jsp";
			
			break;
			
		case "/signup_confirm.mem":
			
			int resultForSignUp = memberService.signUpConfirm(request, response);
			
			if (resultForSignUp == MemberService.SIGN_UP_OK) {
				nextPage = "view/member/signup_ok.jsp";
			} else {
				nextPage = "view/member/signup_ng.jsp";
			}
			
			break;
			
		case "/signin.mem":
			nextPage = "view/member/signin_form.jsp";
			
			break;
		
		case "/signin_confirm.mem":
			
			int resultForSignIn = memberService.signInConfirm(request, response);
			
			if (resultForSignIn == MemberService.SIGN_IN_OK) {
				nextPage = "view/member/signin_ok.jsp";
			} else {
				nextPage = "view/member/signin_ng.jsp";
			}
			
			break;
		case "/signout.mem":
			
			memberService.signOutConfirm(request, response);
			
			break;
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
