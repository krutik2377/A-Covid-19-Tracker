package com.AppProject.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AppProject.bean.Country;
import com.AppProject.dao.CountryDao;

/**
 * Servlet implementation class CountryServlet
 */
@WebServlet("/")
public class CountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CountryDao countryDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		countryDAO =CountryDao.getinstance();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertCountry(request, response);
				break;
			case "/delete":
				deleteCountry(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateCountry(request, response);
				break;
			default:
				listCountry(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listCountry(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Country> listUser = countryDAO.selectAllCountries();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("country-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("country-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String country = request.getParameter("country");
		Country existingCountry = countryDAO.selectCountry(country);
		RequestDispatcher dispatcher = request.getRequestDispatcher("country-form.jsp");
		request.setAttribute("user", existingCountry);
		dispatcher.forward(request, response);

	}

	private void insertCountry(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String country = request.getParameter("country");
		int total_case = Integer.parseInt(request.getParameter("total_case"));
		int total_death = Integer.parseInt(request.getParameter("total_death"));
		int total_recovered = Integer.parseInt(request.getParameter("total_recovered"));
		Country newCountry = new Country(country, total_case,total_death, total_recovered);
		countryDAO.insertCountry(newCountry);
		response.sendRedirect("list");
	}

	private void updateCountry(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String country = request.getParameter("country");
		int total_case = Integer.parseInt(request.getParameter("total_case"));
		int total_death = Integer.parseInt(request.getParameter("total_death"));
		int total_recovered = Integer.parseInt(request.getParameter("total_recovered"));
		Country book = new Country(country, total_case,total_death, total_recovered);
		countryDAO.updateCountry(book);
		response.sendRedirect("list");
	}

	
	private void deleteCountry(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String country = request.getParameter("country");
		countryDAO.deleteCountry(country);
		response.sendRedirect("list");

	}

}
