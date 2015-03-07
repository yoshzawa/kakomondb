<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>

<%
	String message = (String) request.getAttribute("message");
	if (message == null) {
		message = "メッセージがありませんでした";
	}
	String[] mondai = (String[]) request.getAttribute("mondai");
	ArrayList<String[]> genreStr = (ArrayList<String[]>) request
			.getAttribute("genreStr");
	ArrayList<String[]> kotaeList = (ArrayList<String[]>) request
			.getAttribute("kotaeList");
%>

<H2><%=message%></H2>

<div class="form-horizontal">

	<div class="form-group">
		<label for="id" class="col-sm-2 control-label">id</label>
		<div class="col-sm-6">
			<input class="form-control" id="id" value="<%=mondai[0]%>"
				<%=(mondai[5].equals("id") != true) ? "disabled" : ""%>>
			<button class="btn btn-default" type="button"
				<%=(mondai[5].equals("id") != true) ? "disabled" : ""%>>Go!</button>

		</div>


	</div>

	<div class="form-group">
		<label for="Comment" class="col-sm-2 control-label">Comment</label>
		<div class="col-sm-6">
			<input class="form-control" id="Comment" value="<%=mondai[1]%>"
				<%=(mondai[5].equals("comment") != true) ? "disabled" : ""%>>
			<%=makeButton("Go!", (mondai[5].equals("comment")))%>


		</div>
	</div>


	<div class="form-group">
		<label for="Genre" class="col-sm-2 control-label">Genre</label> <span
			class="input-group-btn">
			<div class="col-sm-6">
				<select class="form-control" id="Genre"
					<%=(mondai[5].equals("genre") != true) ? "disabled" : ""%>>
					<%
						for (String[] gs : genreStr) {
					%>
					<option value="<%=gs[0]%>"
						<%=(gs[2].equals("default") == true) ? "selected" : ""%>>
						<%=gs[1]%>
					</option>
					<%
						}
					%>
				</select>

				<%=makeButton("Go!", (mondai[5].equals("genre")))%>

			</div>
		</span>
	</div>


	<%
		if (mondai[5].equals("storage")) {
	%>
	<div class="form-group">
		<label for="StorageImage" class="col-sm-2 control-label">StorageImage</label>
		<div class="col-sm-10">
			<input class="form-control" id="StorageImage" value="<%=mondai[3]%>">
		</div>
	</div>
	<%
		} else {
	%>
	<div class="form-group">
		<label class="col-sm-2 control-label">StorageImage</label>
		<div class="col-sm-6">
			<p class="form-control-static"><%=mondai[3]%></p>
		</div>
	</div>
	<%
		}
	%>


	<div class="form-group">
		<label for="Answer" class="col-sm-2 control-label">Answer</label>
		<div class="col-sm-6">
			<%= makeOption(kotaeList) %>
			<%=makeButton("Go!", (mondai[5].equals("answer")))%>

		</div>
	</div>
	</form>
	<a href="/admin/mondai">リストに戻る</a>

	<%!private String makeButton(String message, boolean enabled) {
		String s = "<button class='btn btn-default' type='button' "
				+ (enabled != true ? "disabled" : "") + ">" + message
				+ "</button>";
		return s;
	}

	private String makeOption(ArrayList<String[]> list) {
		String s = "<select class='form-control' id='Genre'> ";
		for (String[] str : list) {
			s += "<option value='" + str[0]+"'"
					+ (str[2].equals("default") == true ? "selected" : "")
					+ ">" + str[1] + "</option>";
		}
		return s;
	}%>