package com.nirvacsh.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nirvacsh.dao.PeriodicalsDAO;
import com.nirvacsh.models.Periodicals;

@WebServlet("/periodicals")
public class PeriodicalsController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private PeriodicalsDAO periodicalDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		periodicalDAO = new PeriodicalsDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paction = request.getServletPath();

		try {
			switch (paction) {
			case "/pnew":
				showNewPeriodicalForm(request, response);
				break;
			case "/pinsert":
				insertPeriodical(request, response);
				break;
			case "/pdelete":
				deletePeriodical(request, response);
				break;
			case "/pedit":
				showEditPeriodicalForm(request, response);
				break;
			case "/pupdate":
				updatePeriodical(request, response);
				break;
			default:
				listPeriodicals(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listPeriodicals(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Periodicals> listPeriodicals = periodicalDAO.listAllPeriodicals();
		request.setAttribute("listPeriodicals", listPeriodicals);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/periodicals/PeriodicalsList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewPeriodicalForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/periodicals/PeriodicalsForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditPeriodicalForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		Periodicals existingPeriodical = periodicalDAO.getPeriodicalById(pid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/periodicals/PeriodicalsForm.jsp");
		request.setAttribute("periodical", existingPeriodical);
		dispatcher.forward(request, response);
	}

	private void insertPeriodical(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String title = request.getParameter("title");
		float price = Float.parseFloat(request.getParameter("price"));

		Periodicals newPeriodical = new Periodicals(title, price);
		periodicalDAO.insertPeriodicals(newPeriodical);
		response.sendRedirect("plist");
	}

	private void updatePeriodical(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		String title = request.getParameter("title");
		float price = Float.parseFloat(request.getParameter("price"));

		Periodicals periodical = new Periodicals(pid, title, price);
		periodicalDAO.updatePeriodicals(periodical);
		response.sendRedirect("plist");
	}

	private void deletePeriodical(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));

		Periodicals periodical = new Periodicals(pid);
		periodicalDAO.deletePeriodical(periodical);
		response.sendRedirect("plist");

	}
}
