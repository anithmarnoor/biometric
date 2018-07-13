<sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
	<div class="nav-side-menu">
		<div class="brand">PAM</div>
		<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content"></i>

		<div class="menu-list">

			<ul id="menu-content" class="menu-content collapse out">

				<li><a href="myProfile"> <i class="fa fa-user fa-lg"></i>
						My Profile
				</a></li>
				<sec:authorize access="hasRole('ADMIN') ">
					<li data-toggle="collapse" data-target="#hierarchy"
						class="collapsed"><a href="#"><i
							class="fa fa-globe fa-lg"></i>Hierarchy<span class="arrow"></span></a></li>
					<ul class="sub-menu collapse" id="hierarchy">


						<li><a href="addDepartment">Add Department</a></li>
						<li><a href="addDesignation">Add Designation</a></li>
						<li><a href="listDesignations">Designations</a></li>

					</ul>
				</sec:authorize>
				<li data-toggle="collapse" data-target="#Users" class="collapsed">
					<a href="#"> <i class="fa fa-users fa-lg"></i> Users<span
						class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="Users">
					<sec:authorize access="hasRole('ADMIN') ">
						<li><a href="newuser">Add User</a></li>
					</sec:authorize>
					<li><a href="list">Users List</a></li>
					<sec:authorize access="hasRole('ADMIN') ">
						<li><a href="inactiveUsersList">Inactive Users</a></li>
						<li><a href="update-results">Update Results</a></li>
					</sec:authorize>
				</ul>
				<li data-toggle="collapse" data-target="#exams" class="collapsed"><a
					href="#"><i class="fa fa-credit-card"></i> Exams <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="exams">
					<sec:authorize access="hasRole('ADMIN') ">
						<!-- <li><a href="overtimes-list">OT Details</a></li> -->
						<li><a href="view-exams">Exams</a></li>
						<li><a href="view-results-">Results</a></li>
					</sec:authorize>
					<li><a href="view-results-${profile.id}">My Results</a></li>
				</ul>

				<li data-toggle="collapse" data-target="#attendance"
					class="collapsed"><a href="#"><i class="fa fa-pie-chart"></i>Attendance
						<span class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="attendance">
					<sec:authorize access="hasRole('ADMIN') ">
						<li><a href="addMachine">Add Machine</a></li>
						<li><a href="attendanceLog">Attendance Log</a></li>
					</sec:authorize>
					<li><a href="view-Attendance">Search Attendance</a></li>

				</ul>


				<li data-toggle="collapse" data-target="#leaves" class="collapsed">
					<a href="#"><i class="fa fa-pie-chart"></i>Leaves <span
						class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="leaves">
					<sec:authorize access="hasRole('ADMIN') ">
						<li><a href="leaveTypes-list">Leave Types</a></li>
						<li><a href="leavesLimit-list">Leaves Limit</a></li>
					</sec:authorize>
					<li><a href="user-leavesLimit-list">My Leaves Limit</a></li>
					<li><a href="holidays-list">Holidays</a></li>
					<li><a href="apply-leave">Apply Leave</a></li>
					<li><a href="user-leaves">My Leaves</a></li>
					<sec:authorize access="hasRole('ADMIN') ">
						<li><a href="applied-leaves">View All Leaves</a></li>
					</sec:authorize>
				</ul>
				<!-- <li data-toggle="collapse" data-target="#payslips" class="collapsed"><a
					href="#"><i class="fa fa-credit-card"></i> PayRoll <span
						class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="payslips">
					<sec:authorize access="hasRole('ADMIN') ">
						<li><a href="overtimes-list">OT Details</a></li>
						<li><a href="view-wages">Set Wages</a></li>
						<li><a href="view-salaryDivision">Salary Division</a></li>
						<li><a href="list-formula">Payroll Formula</a></li>
						<li><a href="list-formula">View Payroll Formula</a></li>
						<li><a href="view-searchAttendance">Search Attendance</a></li>
						<li><a href="generate-PaySlip">Generate Pay Slip</a></li>
					</sec:authorize>
					<li><a href="view-PaySlip">View My PaySlip</a></li>
				</ul> -->

			</ul>
		</div>
	</div>
</sec:authorize>