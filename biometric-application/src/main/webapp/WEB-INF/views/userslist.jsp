<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="includeLibs.jsp"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">
	function submitEditAction() {
		document.getElementById("submitForm").action = "editUserView";
		document.getElementById("submitForm").submit();
	}
	function submitShowProfileAction() {
		document.getElementById("submitForm").action = "getCompleteUserProfile";
		document.getElementById("submitForm").submit();
	}
	function submitDeleteAction() {
		document.getElementById("submitForm").action = "deleteUser";
		document.getElementById("submitForm").submit();
	}
	function submitPayRollAction() {
		document.getElementById("submitForm").action = "getPayRollAddPage";
		document.getElementById("submitForm").submit();
	}
	function submitPayRollViewAction() {
		document.getElementById("submitForm").action = "payRollDetails-view";
		document.getElementById("submitForm").submit();
	}
</script>
<title>Users List</title>
</head>

<body>
	<%@include file="authheader.jsp"%>
	<div class="generic-container">
		<form:form id="submitForm" method="POST" modelAttribute="user">
			<div class="row">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<span class="lead">Employees </span>
					</div>
					<div class="well">
						<!--      Changes done by M-->
						<input type="text" id="txtSearch" placeholder="Search here.."
							title="Type in a name">

						<!--      close          -->
						<sec:authorize access="hasRole('ADMIN')">
							<c:choose>
								<c:when test="${inactiveUsers eq 'Yes'}">
								</c:when>
								<c:otherwise>
									<input type="button" onclick="submitShowProfileAction()"
										value="Show Profile" />
									<input type="button" onclick="submitEditAction()" value="Edit" />
									<input type="button" onclick="submitDeleteAction()"
										value="Delete" />
									<input type="button" onclick="submitPayRollAction()"
										value="Define Payroll" />
									<input type="button" onclick="submitPayRollViewAction()"
										value="View Payroll" />
								</c:otherwise>
							</c:choose>

						</sec:authorize>

					</div>

					<div class="wrapper">
						<div id="no-more-tables">

							<table id="tblSearch"
								class="col-md-12 table-bordered table-striped table-condensed cf"
								border="0" cellpadding="0" cellspacing="0" width="60%"
								class="scrollTable">
								<thead class="cf" id="thcolor">
									<tr>
										<th><span>Select</span></th>
										<th><span>Name</span></th>
										<!-- <th><span>Email</span></th> -->
										<th><span>Phone No</span></th>
										<th><span>UserName</span></th>
										<th><span>ID</span></th>
										<!-- <th><span>DOB</span></th>
										<th><span>Address</span></th>
										<th><span>Joining Date</span></th> -->
										<th><span>Status</span></th>
										<!-- <th><span>Left On</span></th> -->
										<!-- <th><span>Department</span></th> -->
										<th><span>Designation</span></th>
										<th><span>Role</span></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${users}" var="user">
										<tr>
											<td id="tdd1" data-title="Select"><input type="radio"
												name="userId" value="${user.id}" /></td>
											<td id="tdd1" data-title="Name">${user.firstName}${user.lastName}</td>
											<%-- <td id="tdd2" data-title="Email">${user.email}</td> --%>
											<td id="tdd3" data-title="Phone No">${user.phoneNo}</td>
											<td id="tdd4" data-title="UserName">${user.userName}</td>
											<td id="tdd5" data-title="Id">${user.id}</td>
											<%-- <td id="tdd8" data-title="Joining Date">${user.doj}</td> --%>
											<td id="tdd11" data-title="Status">${user.status}</td>
											<%-- <c:choose>
												<c:when test="${user.status ne 'Active'}">
													<td data-title="Left On">${user.leftDate}</td>
												</c:when>
												<c:otherwise>
													<td id="tdd12" data-title="Left On">-</td>
												</c:otherwise>
											</c:choose> --%>
											<td id="tdd14" data-title="Designation">${user.designation.designationName}</td>
											<td id="tdd15" data-title="Role">${user.userProfile.type}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
					</div>
				</div>
			</div>

		</form:form>
	</div>

	<script type="text/javascript">
		jQuery.expr[":"].containsNoCase = function(el, i, m) {
			var search = m[3];
			if (!search)
				return false;
			return eval("/" + search + "/i").test($(el).text());
		};

		jQuery(document)
				.ready(
						function() {

							jQuery('#txtSearch').keyup(function(event) {
								if (event.keyCode == 27) {
									resetSearch();
								}
							});

							jQuery('#txtSearch')
									.keyup(
											function() {

												if (jQuery('#txtSearch').val().length > 2) {
													jQuery('#tblSearch tr')
															.hide();
													jQuery(
															'#tblSearch tr:first')
															.show();
													jQuery(
															'#tblSearch tr td:containsNoCase(\''
																	+ jQuery(
																			'#txtSearch')
																			.val()
																	+ '\')')
															.parent().show();

												} else if (jQuery('#txtSearch')
														.val().length == 0) {
													resetSearch();
												}

												if (jQuery('#tblSearch tr:visible').length == 1) {
													jQuery('.norecords')
															.remove();
													jQuery('#tblSearch')
															.append(
																	'<tr class="norecords"><td colspan="5" class="Normal">No records were found</td></tr>');
												}
											});
						});

		function resetSearch() {
			jQuery('#txtSearch').val('');
			jQuery('#tblSearch tr').show();
			jQuery('.norecords').remove();
			jQuery('#txtSearch').focus();
		}
		//table sorting   
		$(function() {
			$('#tblSearch').tablesorter();
		});
	</script>
</body>
</html>