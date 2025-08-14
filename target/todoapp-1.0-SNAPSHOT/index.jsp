<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.TodoList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todoリスト</title>

<!-- Materialize CSS & JS -->
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
    <h4 class="center-align">TODO リスト</h4>
    
    
    <form action = "lists" method ="post">
      <input type="hidden" name="action" value="insert">
      
      <div class="input-field">
        <input type="text" id="name" name="name" required>
        <label for="name">タスク名</label>
      </div>
      
      <div class="right-align">
        <button class="btn waves-effect waves-light blue" type="submit">
          <i class="material-icons left">add</i> 追加
        </button>
        <a href="lists?action=list" class="btn green">
          <i class="material-icons left">list</i> リスト一覧
        </a>
      </div>
    </form>
  </div>
 
</body>
</html>