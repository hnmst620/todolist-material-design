<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.TodoList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>リストの編集</title>

<!-- Materialize CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<style>
  body {
    padding: 2rem;
  }
</style>
</head>
<body>
  <div class="container">
    <h4 class="center-align">リストを編集</h4>
   
    <%
      TodoList list = (TodoList) request.getAttribute("list");
      if(list != null){
    %>
    
    <form action="lists" method="post">
      <input type="hidden" name="action" value="update">
      <input type="hidden" name="id" value="<%= list.getId() %>">
      
      <div class="input-field">
        <input type="text" name="name" id="name" value="<%= list.getName() %>" required>
        <label for="name" class="active">タスク名</label>
      </div>
      
      <div class="right-align">
        <button class="btn waves-effect waves-light yellowgreen" type="submit">
          <i class="material-icons left">save</i> 更新
        </button>
        <a href="lists?action=list" class="btn grey">
          <i class="material-icons left">arrow_back</i> 戻る
        </a>
      </div>
    </form>
    
    <%
       }else{
    %>
      <p>該当する項目が見つかりません。</p>
    <%
       }
    %>
  </div>
</body>
</html>