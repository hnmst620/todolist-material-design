<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.TodoList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO リスト一覧</title>

<!-- Materialize CSS & JS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<style>
  body {
    padding: 2rem;
  }
  .completed-task {
    text-decoration: line-through;
    color: gray;
  }
  .active-task {
    color: black;
  }
</style>


</head>
<body>
  
  <div class="container">
     <h4 class="center-align">TODO リスト</h4>
    
     <div class="right-align">
       <a href="index.jsp" class="btn waves-effect waves-light blue">
         <i class="material-icons left">add</i> 追加
       </a>
  </div>
     
     <ul class="collection with-header">
       <li class="collection-header"><h5>タスク一覧</h5></li>
  
      <%
        List<TodoList> lists = (List<TodoList>) request.getAttribute("lists");
        if(lists != null) {
          for(TodoList list : lists){
      %>
      <li class="collection-item">
         <form action="lists" method="post" style="display: inline;">
          <input type="hidden" name="action" value="toggle">
          <input type="hidden" name="id" value="<%= list.getId() %>">

          <label>
            <input type="checkbox" name="done" onchange="this.form.submit();" <%= list.isDone() ? "checked" : "" %> />
            <span class="<%= list.isDone() ? "completed-task" : "active-task" %>"><%= list.getName() %></span>
          </label>
        </form>
      
 
        <div class="secondary-content">
           <a href="lists?action=edit&id=<%= list.getId() %>"
           class="btn-small orange"><i class="material-icons">edit</i>編集</a>
           <a href="lists?action=delete&id=<%= list.getId() %>" 
           class="btn-small red" onclick="return confirm('本当に削除しますか？');">
           <i class="material-icons">delete</i>削除</a>
        </div>
      </li>
      <%
         }
        }
      %>
     </ul>
  </div>
</body>
</html>