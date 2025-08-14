package servlet;

import java.io.IOException;
import java.util.List;

import dao.TodoListDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TodoList;


@WebServlet("/lists")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoListDAO dao;
    
	public void init() throws ServletException {
		dao = new TodoListDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
	    System.out.println("POST action = " + action);      //デバック用
		
		if("update".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			dao.updateList(id, name);
			response.sendRedirect("lists?action=list");
			return;
		}else if("insert".equals(action)) {
			String name = request.getParameter("name");
			if(name != null && !name.trim().isEmpty()) {
			dao.addList(name);
			}
			response.sendRedirect("lists?action=list");
			return;
			
		} else if ("toggle".equals(action)) {      //新しい打消し線メソッド
		    int id = Integer.parseInt(request.getParameter("id"));
		    boolean done = request.getParameter("done") != null;
		    dao.updateDoneStatus(id, done); 
		    response.sendRedirect("lists?action=list");
		    return;	
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if("edit".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			TodoList list = dao.findById(id);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
			
		}else if ("delete".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.deleteList(id);
			response.sendRedirect("lists?action=list");
			return;
			
		}else if("list".equals(action) || action == null ) {
			List<TodoList> lists = dao.getAllLists();
			request.setAttribute("lists", lists);
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		}else {
			List<TodoList> lists = dao.getAllLists();
			request.setAttribute("lists", lists);
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		}
		
		
		}
}


