<%@ page
   language="java"
   isErrorPage="true"
%>

<% 
try {
	String error = (String) request.getAttribute("error");
	if ( error != null ) {
		request.removeAttribute("error");
		%>
	<div id=error>
		<form>
			<ul>
			<% 
			out.write(error);
			%>
			</ul>
		</form>
	</div>
		<%
	}
}
catch(Exception e) {}
%>