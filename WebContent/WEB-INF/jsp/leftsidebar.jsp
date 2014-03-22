<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<sec:authorize access="isAnonymous()">
    <h2>Members</h2>
    <form class="form-signin" role="form" action='<c:url value='j_spring_security_check' />' method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input name="j_username" type="text" class="form-control" placeholder="Email address" required autofocus><br/>
        <input name="j_password" type="password" class="form-control" placeholder="Password" required>
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
      
      <c:if test="${not empty error}">
		<div><font color="red"><br/>
			Invalid username or password</font>
		</div>
	</c:if>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
              	<ul class="nav nav-stacked" id="sidebar">
              	  <li><a href="#sec0">Section 0</a></li>
                  <li><a href="#sec1">Section 1</a></li>
                  <li><a href="#sec2">Section 2</a></li>
                  <li><a href="#sec3">Section 3</a></li>
                  <li><a href="#sec4">Section 4</a></li>
                  <li><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
              	</ul>
</sec:authorize>