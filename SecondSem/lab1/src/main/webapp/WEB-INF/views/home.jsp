<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script type="text/javascript">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


	<script>
		$(function(){
			$('.list-group li').click(function(e) {
				e.preventDefault()
				$that = $(this)
				$(".list-group").find('li').removeClass('list-group-item-success');
				$that.addClass('list-group-item-success');
			});
		})
	</script>
	<title><fmt:message key="nav.home"/></title>
</head>
<body class="bg-secondary">

<header>
	<nav class="navbar navbar-light bg-dark">
		<a href="${pageContext.request.contextPath}/controller?command=home" class="navbar-brand text-light"><fmt:message key="nav.project_name"/></a>

		<form class="form-inline">

			<c:choose>
				<c:when test="${not fn:contains(pageContext.request.queryString, 'lang')}">
					<a class="mr-sm-2" href="?${pageContext.request.queryString}&lang=uk">Укр</a>
					<a class="mr-sm-2" href="?${pageContext.request.queryString}&lang=en">Eng</a>
				</c:when>
				<c:otherwise>
					<a class="mr-sm-2" href="?${fn:replace(pageContext.request.queryString,'lang=en','lang=uk')}">Укр</a>
					<a class="mr-sm-2" href="?${fn:replace(pageContext.request.queryString,'lang=uk','lang=en')}">Eng</a>
				</c:otherwise>
			</c:choose>

			<!-- SignIn/SignUp -->
			<c:set var="username" value="${sessionScope.user.login}" scope="page"/>

			<c:choose>
				<c:when test="${empty username}">
					<a class="btn btn-dark mr-sm-2" data-toggle="modal" data-target="#modalRegister" href="#"><fmt:message key="nav.register"/></a>
					<a class="btn btn-success mr-sm-2"  data-toggle="modal" data-target="#modalLogin"><fmt:message key="nav.sign_in"/></a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-dark text-muted mr-sm-2" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="nav.sign_out"/></a>
					<a class="btn btn-dark mr-sm-2" href="${pageContext.request.contextPath}/controller?command=profile"><fmt:message key="nav.profile"/></a>
					<!-- Add log out -->
				</c:otherwise>
			</c:choose>

		</form>
	</nav>
</header>

<div class="container-fluid">

	<div class="row">

		<c:if test="${sessionScope.user.role eq 'ADMIN'}">
			<div class="col-2  m-0 p-0 bg-secondary position-fixed">
				<a class="btn btn-dark btn-block m-0" href="${pageContext.request.contextPath}/controller?menutype=AVAILABLE"><fmt:message key="menu.type.available"/></a>
				<a class="btn btn-dark btn-block m-0" href="${pageContext.request.contextPath}/controller?menutype=RENTED"><fmt:message key="menu.type.rented"/></a>
				<a class="btn btn-dark btn-block m-0" href="${pageContext.request.contextPath}/controller?menutype=BROKEN"><fmt:message key="menu.type.broken"/></a>
			</div>
		</c:if>

		<div class="col-10 px-0 min-vh-100 offset-2 bg-light ">
			<div class="content">
				<div class="container">
					<div class="row py-4">
						<c:forEach items="${requestScope.data}" var="car">
							<div class="col-md-3">
								<div class="card py-2 px-2">
									<div class="card-block">
										<h5 class="card-title d-flex justify-content-center align-items-center">${car.model}</h5>
										<small class="card-subtitle text-muted align-items-top d-flex justify-content-center">${car.price} uah/hour</small>
										<div class="card-block">
											<div id="colorselect" class="js-swatch js-star" style="background: ${car.color}; margin: auto; border: 2px solid black; width: 50px; height: 27px; border-radius: 3px; margin-bottom: 5px;"></div>

											<c:if test="${not empty sessionScope.user.login}">
												<c:url value="/controller" var="rentUrl">
													<c:param name="command" value="rent" />
													<c:param name="car_id" value="${car.id}"/>
													<c:param name="price" value="${car.price}"/>
												</c:url>

												<c:choose>
													<c:when test="${sessionScope.user.role eq 'ADMIN'}">
														<c:if test="${requestScope.menutype eq 'AVAILABLE'}">
															<c:url value="/controller" var="editUrl">
																<c:param name="command" value="edit" />
																<c:param name="id" value="${car.id}" />
															</c:url>
															<a href="${editUrl}" class="col-12 card-link btn btn-outline-primary align-items-center justify-content-center"><fmt:message key="action.edit"/></a>
														</c:if>
														<c:if test="${requestScope.menutype eq 'BROKEN'}">
															<c:url value="/controller" var="fixUrl">
																<c:param name="command" value="fix" />
																<c:param name="id" value="${car.id}" />
															</c:url>
															<a href="${fixUrl}" class="col-12 card-link btn btn-outline-primary align-items-center justify-content-center"><fmt:message key="action.fix"/></a>
														</c:if>
													</c:when>
													<c:otherwise>
														<a href="${rentUrl}" class="col-12 card-link btn btn-outline-primary align-items-center justify-content-center"><fmt:message key="action.subscribe"/></a>
													</c:otherwise>
												</c:choose>
											</c:if>
										</div>
									</div>
								</div>
								<div class="mt-4"> </div>
							</div>
						</c:forEach>
						<c:if test="${sessionScope.user.role eq 'ADMIN'}">
							<c:if test="${requestScope.menutype eq 'AVAILABLE'}">
								<div class="col-md-3 mt-4">
									<div class="card bg-primary py-2 d-flex justify-content-center align-items-center">
										<a style="color:white;" href="${pageContext.request.contextPath}/controller?command=edit"><fmt:message key="action.add"/></a>
									</div>
								</div>
							</c:if>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>


<!-- Modals -->
<div class="modal fade login" id="modalLogin" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"><fmt:message key="nav.sign_in"/></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" action="${pageContext.request.contextPath}/controller?command=login" method="post">
					<div class="form-group">
						<i class="fa fa-user"></i>
						<input name="login" type="text" class="form-control" placeholder="Username" required="required">
					</div>
					<div class="form-group">
						<i class="fa fa-lock"></i>
						<input name="password" type="password" class="form-control" placeholder="Password" required="required">
					</div>
					<button class="btn btn-success"><fmt:message key="action.continue"/></button>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="modalRegister" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"><fmt:message key="nav.register"/></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" action="${pageContext.request.contextPath}/controller?command=registration" method="post">
					<div class="form-group">
						<div class="form-group-inline">
							<i class="fa fa-user"></i>
							<small class="text-secondary"><fmt:message key="regex.info.login"/></small>
						</div>
						<input name="login" type="text" class="form-control" placeholder="Username" required="required">
					</div>
					<div class="form-group">
						<div class="form-group-inline">
							<i class="fa fa-lock"></i>
							<small class="text-secondary"><fmt:message key="regex.info.password"/></small>
						</div>

						<input name="password" type="password" class="form-control" placeholder="Password" required="required">
					</div>
					<div class="form-group">
						<input name="passport" type="text" class="form-control" placeholder="Passport" required="required">
					</div>
					<button class="btn btn-success"><fmt:message key="action.continue"/></button>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="modalDownload" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Download</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<c:url value="/controller" var="url">
					<c:param name="command" value="download" />
				</c:url>

				<form class="form-horizontal" action="${url}" method="post">
					<h2>Sort by</h2>
					<div class="form-group radio">
						<label><input type="radio" name="sort" value="title" checked>Title</label>
						<br>
						<label><input type="radio" name="sort" value="price">Price</label>
					</div>

					<button class="btn btn-success">Download</button>
				</form>
			</div>
		</div>
	</div>
</div>

<c:if test="${sessionScope.invalidData eq 'true'}">
	<script>
		alert("<fmt:message key='alert.wrong.input'/>");
	</script>
</c:if>
</body>
</html>

