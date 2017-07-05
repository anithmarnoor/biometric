<sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
	<div class="nav-side-menu">
		<div class="brand">PAM</div>
		<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content"></i>

		<div class="menu-list">

			<ul id="menu-content" class="menu-content collapse out">
				<!--  <li>
                  <a href="#">
                  <i class="fa fa-dashboard fa-lg"></i> Dashboard
                  </a>
                </li> -->

				<!--  <li  data-toggle="collapse" data-target="#products" class="collapsed active">
                  <a href="#"><i class="fa fa-gift fa-lg"></i> UI Elements <span class="arrow"></span></a>
                </li>
                <ul class="sub-menu collapse" id="products">
                    <li class="active"><a href="#">CSS3 Animation</a></li>
                    <li><a href="#">General</a></li>
                    <li><a href="#">Buttons</a></li>
                    <li><a href="#">Tabs & Accordions</a></li>
                    <li><a href="#">Typography</a></li>
                    <li><a href="#">FontAwesome</a></li>
                    <li><a href="#">Slider</a></li>
                    <li><a href="#">Panels</a></li>
                    <li><a href="#">Widgets</a></li>
                    <li><a href="#">Bootstrap Model</a></li>
                </ul>
 -->

				<!-- <li data-toggle="collapse" data-target="#service" class="collapsed">
                  <a href="#"><i class="fa fa-globe fa-lg"></i> Services <span class="arrow"></span></a>
                </li>  
                <ul class="sub-menu collapse" id="service">
                  <li>New Service 1</li>
                  <li>New Service 2</li>
                  <li>New Service 3</li>
                </ul>


                <li data-toggle="collapse" data-target="#new" class="collapsed">
                  <a href="#"><i class="fa fa-car fa-lg"></i> New <span class="arrow"></span></a>
                </li>
                <ul class="sub-menu collapse" id="new">
                  <li>New New 1</li>
                  <li>New New 2</li>
                  <li>New New 3</li>
                </ul> -->


				<li><a href="/PAM/myProfile"> <i class="fa fa-user fa-lg"></i>
						My Profile
				</a></li>
				<sec:authorize access="hasRole('ADMIN') ">
					<li><a href="/PAM/list"> <i class="fa fa-users fa-lg"></i>
							Users
					</a></li>
				</sec:authorize>
				<li data-toggle="collapse" data-target="#payslips" class="collapsed">
					<a href="#"><i class="fa fa-globe fa-lg"></i> Payslips <span
						class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="payslips">
					<sec:authorize access="hasRole('ADMIN') ">
						<li><a href="view-wages">Set Wages</a></li>
						<li><a href="view-salaryDivision">Salary Division</a></li>
						<li><a href="view-BiometricData">Biometric Data</a></li>
						<li><a href="view-searchAttendance">Attendance</a></li>
						<li><a href="view-PaySlip">Pay Slip Generation</a></li>
					</sec:authorize>
					<li><a href="view-Attendance-${profile.id}">My Attendance</a></li>
					<li><a href="view-PaySlip-${profile.id}">My PaySlip</a></li>
				</ul>
			</ul>
		</div>
	</div>
</sec:authorize>