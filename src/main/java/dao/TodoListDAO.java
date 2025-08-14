package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TodoList;


public class TodoListDAO {
	private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/todo";
	private static final String JDBC_USER = "sa";
	private static final String JDBC_PASS = "";
	
	private Connection getConnection() throws SQLException {
		try {
			Class.forName("org.h2.Driver"); // ← 追加
			} catch (ClassNotFoundException e) {
				throw new SQLException("H2ドライバが見つかりません", e);
				}
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		}
	
	public void addList(String name) {
		String sql = "INSERT INTO todo_lists (name) VALUES (?)";
		try(Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			
			// 入力内容をログに出す
	        System.out.println("[DAO] リスト追加処理開始: " + name);

	        // データをINSERT
	        ps.setString(1, name);
	        ps.executeUpdate();
	        
	     // INSERT直後にDB内容を確認
	        System.out.println("[DAO] 現在の todo_lists テーブル:");
	        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM todo_lists");
	        
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String listName = rs.getString("name");
	            System.out.println("  - id: " + id + ", name: " + listName);
	        }

	        System.out.println("[DAO] リスト追加処理完了");

		}catch(SQLException e) {
			System.out.println("[DAO] リスト追加中にエラーが発生しました");
			e.printStackTrace();
		}
	}
	
	public TodoList findById(int id) {
		String sql = "SELECT * FROM todo_lists WHERE id = ?";
		try(Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return new TodoList(rs.getInt("id"),rs.getString("name"));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TodoList> getAllLists(){
		List<TodoList> lists = new ArrayList<>();
		String sql = "SELECT * FROM todo_lists ORDER BY id DESC";
		try(Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				TodoList todo = new TodoList(rs.getInt("id"), rs.getString("name"));
				todo.setDone(rs.getBoolean("done")); 
				lists.add(todo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
	
	public void updateList(int id, String name) {
		String sql = "UPDATE todo_lists SET name = ? WHERE id = ?";
		try(Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("[DAO] リスト更新: id=" + id + ", name=" + name);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteList(int id) {
		String sql = "DELETE FROM todo_lists WHERE id = ?";
		try(Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("[DAO] リスト削除: id=" + id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateDoneStatus(int id, boolean done) {
		try (Connection conn = getConnection()) {
			String sql = "UPDATE todo_lists SET done = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setBoolean(1, done);
	        stmt.setInt(2, id);
	        stmt.executeUpdate();
		}catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
