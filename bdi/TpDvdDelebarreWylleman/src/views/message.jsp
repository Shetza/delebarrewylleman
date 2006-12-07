<% 
try {
	String message = (String) request.getAttribute("message");
	if ( message != null ) {
		request.removeAttribute("message");
		%>
	<form>
		<ul>
		<% 
		out.write(message);
		%>
		</ul>
	</form>
		<%
	}
}
catch(Exception e) {}
%>