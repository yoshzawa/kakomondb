<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	String message = (String) request.getAttribute("message");
	if (message == null) {
		message = "メッセージがありませんでした";
	}
	String[] mondai = (String[]) request.getAttribute("mondai");
	
%>

<H2><%= message %></H2>


    <div class="form-group">
    <label class="col-sm-2 control-label">ID</label>
    <div class="col-sm-10">
      <p class="form-control-static"><%= mondai[0] %></p>
    </div>
  </div>

<% 
	if (mondai[5].equals("comment")){
%>
		  <div class="form-group">
		    <label for="Comment" class="col-sm-2 control-label">Comment</label>
		    <div class="col-sm-10">
		      <input  class="form-control" id="Comment" value="<%= mondai[1] %>">
		    </div>
		  </div>
<% 		
	} else { 
%>  
    <div class="form-group">
    <label class="col-sm-2 control-label">Comment</label>
    <div class="col-sm-10">
      <p class="form-control-static"><%= mondai[1] %></p>
    </div>
  </div>
<% 		
	} 
%>

<% 
	if (mondai[5].equals("genre")){
%>
		  <div class="form-group">
		    <label for="Genre" class="col-sm-2 control-label">Genre</label>
		    <div class="col-sm-10">
		      <input  class="form-control" id="Genre" value="<%= mondai[2] %>">
		    </div>
		  </div>
<% 		
	} else { 
%>  
    <div class="form-group">
    <label class="col-sm-2 control-label">Genre</label>
    <div class="col-sm-10">
      <p class="form-control-static"><%= mondai[2] %></p>
    </div>
  </div>
<% 		
	} 
%>

<% 
	if (mondai[5].equals("storage")){
%>
		  <div class="form-group">
		    <label for="StorageImage" class="col-sm-2 control-label">StorageImage</label>
		    <div class="col-sm-10">
		      <input  class="form-control" id="StorageImage" value="<%= mondai[3] %>">
		    </div>
		  </div>
<% 		
	} else { 
%>  
    <div class="form-group">
    <label class="col-sm-2 control-label">StorageImage</label>
    <div class="col-sm-10">
      <p class="form-control-static"><%= mondai[3] %></p>
    </div>
  </div>
<% 		
	} 
%>  

<% 
	if (mondai[5].equals("answer")){
%>
		  <div class="form-group">
		    <label for="Answer" class="col-sm-2 control-label">Answer</label>
		    <div class="col-sm-10">
		      <input  class="form-control" id="Answer" value="<%= mondai[4] %>">
		    </div>
		  </div>
<% 		
	} else { 
%>  
    <div class="form-group">
    <label class="col-sm-2 control-label">Answer</label>
    <div class="col-sm-10">
      <p class="form-control-static"><%= mondai[4] %></p>
    </div>
  </div>
<% 		
	} 
%>  
  
<a href="/admin/mondai">リストに戻る</a>