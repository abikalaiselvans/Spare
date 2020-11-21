<!DOCTYPE html>
<html>
<head>
       <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>:: SPARE ::</title>

        <!--CSS -->
		 <link rel="shortcut icon" href="resources/img/care.png">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link href="resources/css/app.min.1.css" rel="stylesheet">
        
		<link href="resources/css/animate.min.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
		<link href="resources/css/material-design-iconic-font.min.css" rel="stylesheet">
        <link href="resources/css/socicon.min.css" rel="stylesheet">
        <!-- CSS -->
	</head>
    <body>
        <header id="header">
            <ul class="header-inner">
                <li id="menu-trigger" data-trigger="#sidebar">
                    <div class="line-wrap">
                        <div class="line top"></div>
                        <div class="line center"></div>
                        <div class="line bottom"></div>
                    </div>
                </li>
				<li class="logo hidden-xs"><a href="home">Welcome to ${USERNAME}</a></li>
                
                
                <li class="pull-right">
					<ul class="top-menu">
						<li id="toggle-width">
							<div class="toggle-switch">
								<input id="tw-switch" type="checkbox" hidden="hidden">
								<label for="tw-switch" class="ts-helper"></label>
							</div>
						</li>
						
						 
						<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>&nbsp;<font color='#ffffff'>Logout</font></a></li>
					</ul>
					
					
                </li>
                
                
            </ul>
            
            <!-- Top Search Content -->
            <div id="top-search-wrap">
                <input type="text">
                <i id="top-search-close">&times;</i>
            </div>
        </header>
        
        <section id="main">
            <aside id="sidebar">
                <div class="sidebar-inner c-overflow">
                    <div class="profile-menu">
                        <a href="#">
                            <div class="profile-pic">
                                <img src="resources/img/profile-pics/1.jpg" alt="">
                            </div>
							<div class="profile-info">${USERNAME}<i class="md md-arrow-drop-down"></i>
                            </div>
                        </a>

                        <ul class="main-menu">
                            <li>
                                <a href="profile-about.html"><i class="md md-person"></i> View Profile</a>
                            </li>
                            <li>
                                <a href="#"><i class="md md-settings-input-antenna"></i> Privacy Settings</a>
                            </li>
                            <li>
                                <a href="#"><i class="md md-settings"></i> Settings</a>
                            </li>
                            <li>
                                <a href="#"><i class="md md-history"></i> Logout</a>
                            </li>
                        </ul>
                    </div>

                    <ul class="main-menu">
                        <li class="active"><a href="smart.html"><i class="md md-home"></i> Home</a></li>
                        
                        <li class="sub-menu">
                            <a href="#"><i class="md md-settings"></i>Common</a>
							<ul>
                                <li><a href="#">Staff Registration</a></li>
                                <li><a class="active" href="#">Reports</a></li>
                            </ul>
                        </li>
                        <li class="sub-menu">
                            <a href="#"><i class="md md-account-child"></i>HRM</a>

                            <ul>
                                <li><a href="#">Application</a></li>
                                <li><a href="#">Offer Letter</a></li>
								<li><a href="#">Online Test</a></li>
                                <li><a href="#">Reports</a></li>
								
                            </ul>
                        </li>
                        <li class="sub-menu">
                            <a href="#"><i class="md md-insert-drive-file"></i>Attendance</a>

                            <ul>
                                <li><a href="#">Register</a></li>
                                <li><a href="#">Process</a></li>
                                <li><a href="#">Report</a></li>
                            </ul>
                        </li>
                        <li class="sub-menu">
                            <a href="#"><i class="md md-local-atm"></i>Payroll</a>
                            <ul>
                                <li><a href="#">Appointment Order</a></li>
                                <li><a href="#">Migrate Allowance & Recovery</a></li>
                                <li><a href="#">Salary Process</a></li>
                                <li><a href="#">Reports</a></li>
                            </ul>
                        </li>
                        <li class="sub-menu">
                            <a href="#"><i class="md md-now-widgets"></i>Inventory</a>
                            <ul>
                                <li><a href="#">Quotation/Tender</a></li>
                                <li><a href="#">CPO</a></li>
								<li><a href="#">Purchase Order</a></li>
								<li><a href="#">Reports</a></li>
                            </ul>
                        </li>
						 <li class="sub-menu">
                            <a href="#"><i class="md md-add-shopping-cart"></i>Marketing</a>
                            <ul>
								<li><a href="#">Dailly Calls</a></li>
                                <li><a href="#">Quotation</a></li>
                                <li><a href="#">Funnel</a></li>
								<li><a href="#">Reports</a></li>
                            </ul>
                        </li>
                        <li><a href="calendar.html"><i class="md md-today"></i> Calendar</a></li>
                    </ul>
                </div>
            </aside>
            

            
            
            <section id="content">
                <div class="container">
                    
                 <div class="mini-charts">
						<div class="card">                 
							<div class="card-body card-padding">
								<div class="row">
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Commonindex"><i class="md md-perm-data-setting icon"></i>Common</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="HRMindex"><i class="md md-people icon"></i>HRM</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Attendanceindex"><i class="md md-alarm icon"></i>Attendance</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Payrollindex"><i class="md md-local-atm icon"></i>Payroll</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Inventoryindex"><i class="md md-now-widgets icon"></i>Inventory</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Conveyanceindex"><i class="md md-account-balance-wallet icon"></i>Conveyance</a>	                            
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="#"><i class="md md-live-help icon"></i>Help</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Marketingindex"><i class="md md-shopping-cart icon"></i>Marketing</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Managemantindex"><i class="md md-account-balance icon"></i>Managemant</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="CRMindex"><i class="md md-quick-contacts-dialer icon"></i>CRM</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="SpareHome"><i class="md md-keyboard-hide icon"></i>Spare</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Transportindex"><i class="md md-directions-bus icon"></i>Transport</a>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Serviceindex"><i class="md md-my-library-books icon"></i>Service Report</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Debugindex"><i class="md md-error icon"></i>Debug</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Fileindex"><i class="md md-cloud-upload icon"></i>File Upload</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Trainingindex"><i class="md md-school icon"></i>Training</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="Utilityindex"><i class="md md-verified-user icon"></i>Utility</a>
										</div>
									</div>
									<div class="col-md-2">
										<div class="ch-grid">
											<a href="logout"><i class="md md-history icon"></i>Logout</a>
										</div>
									</div>
								</div>
							</div>
						</div>
                    </div>
                </div>
            </section>
        </section>
        <%=request.getRealPath("/") %>
        <footer id="footer">
			<div class = "text-center">
              <p>© 2007 SMART SOLUTIONS PVT. LTD.,    All rights reserved.</p>
			 </div> 
        </footer>
        
         <!-- Placeholder for IE9 -->
        <!--[if IE 9 ]>
            <script src="vendors/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
        <![endif]-->
        
        <!-- Javascript Libraries -->
      	<script src="resources/js/jquery-1.11.3.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/jquery.nicescroll.min.js"></script>
		<script src="resources/js/functions.js"></script>
        
        
	</body>
 </html>