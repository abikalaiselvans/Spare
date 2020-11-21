<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql"%>
 <!DOCTYPE html>
<html lang="en">
<head>
		<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>:: SPARE :: </title>
       <!-- CSS -->
		<link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="resources/css/form-elements.css">
		<link rel="stylesheet" href="resources/css/style1.css">
		<link href="resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
 		<!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="resources/img/care.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/img/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/img/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/img/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="resources/img/apple-touch-icon-57-precomposed.png">
</head>

<body onload="document.getElementById('username').focus()" >
		<!-- Top menu -->
	<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
					
			</div>
			<!--nav links -->
			<div class="collapse navbar-collapse" id="top-navbar-1">
				<ul class="nav navbar-nav navbar-right navbar-fixed">
					<li>
						<span class="li-text">
							<a href="login.html">Home</a> |
							<a href="#" data-toggle="modal" data-target="#contact">Contact</a>
						</span>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Top content -->
    <div class="top-content">
  		<div class="inner-bg">
            <div class="container">
                <div class="row">
                    <div class="col-sm-7 text">
						<div class="description">
							
							<img src="resources/img/bg.png">
						</div>
                    </div>    
                    <div class="col-sm-5 form-box">
						<div class="form-top">
                        	<div class="form-top-left">
								<h3>Login</h3>
                            </div>
                        	<div class="form-top-right">
                        		<i class="fa fa-pencil"></i>
                        	</div>
                        </div>
                        <div class="form-bottom">
							 
							<form  method="post" action="SpareAuthenticate"  autocomplete="on"  class="registration-form" >
			                    <div class="form-group">
									<div class="input-group">
										<label class="sr-only" for="name">Username</label>
										<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
										<input type="text" name="username"  value='admin' placeholder="Enter the username" class="form-firstname form-control" id="name">
									</div>
			                    </div>
			                    <div class="form-group">
									<div class="input-group">
										<label class="sr-only" for="password">Password</label>
										<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
										<input type="password" name="password" placeholder="Enter the password" class="form-lastname form-control" id="password" data-toggle="tooltip" data-trigger="manual" data-title="Caps lock is on">
										<input type="hidden" name="filename" value="Login"/>
									</div>
			                    </div>
								<div class="form-group">			                       
									<div class="col-md-offset-4">
										<input type="submit" class="btn btn-info btn-lg" value="login" id="submit">
									</div>
								</div>
								<div class="form-group">	
									<div class="col-md-offset-3">
										<a href="#" data-toggle="modal" data-target="#forget"><h4>Forget Password</h4></a>
									</div>
								</div>
			                </form>
		                </div>
                    </div>
					
				</div>
					
			</div>
		</div> 
	</div>
	
	<!-- FORGET password modal-->
	<div class="modal fade forget" tabindex="-1" role="dialog" aria-labelledby="forget" aria-hidden="true" id="forget">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">FORGET PASSWORD</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="" method="post" class="registration-form" >
						<div class="row">
							<div class="form-group">
								<div class="radio col-md-6">
									<label><input type="radio" name="optradio" id="office"  checked="checked">OFFICE MAIL</label>
								</div>
							</div>
							<div class="form-group">
								<div class="radio col-md-offset-2">
									<label><input type="radio" name="optradio" id="personal" value="0">PERSONAL MAIL</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<label class="sr-only" for="name">Email</label>
								<span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
								<input type="text" name="name" placeholder="Email" class="form-firstname form-control" id="name">
							</div>
						</div>
						<div class="form-group">
							<label class="sr-only" for="password">date</label>
							<div class="input-group date form_date" id="datepicker" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									<input class="form-control col-md-8" size="20" type="text" value="" placeholder="Date of Birth" >
							</div>
							<input type="hidden" id="dtp_input2" value="" /><br/>
						</div>
						<div class="form-group">
							<div class="input-group col-md-offset-5">
								<input type="submit" value="Submit" class="btn btn-primary btn-block" tabindex="7">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!--Contact Us modal-->
	<div class="modal fade contact" tabindex="-1" role="dialog" aria-labelledby="contact" aria-hidden="true" id="contact">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">CONTACT US</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-6">
						</div>
						<div class="col-md-6">
							<h4>Mythra Technology</h4>
							<h4>No.11/6, Devakiammal Street,</h4>
							<h4>Shenoy Nagar,Chennai-600030,</h4>
							<h4>Tamilnadu,India</h4>
							<div class="row">
								<div class="col-md-2">
									<span class="glyphicon glyphicon-earphone"></span>
								</div>
								<div class="col-md-9">
									<h4>+91-44-</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<span class="glyphicon glyphicon-print"></span>
								</div>
								<div class="col-md-8">
									<h4>044-</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<span class="glyphicon glyphicon-envelope"></span>
								</div>
								<div class="col-md-offset-2">
									<h4><a href="#">info@mythra.in</a></h4>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!--Career modal-->
	<div class="modal fade career" tabindex="-1" role="dialog" aria-labelledby="forget" aria-hidden="true" id="career">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">PERSONAL INFORMATION</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="" method="post" class="registration-form" >
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="FirstName" data-toggle="tooltip" data-placement="bottom" name="first_name" id="first_name" class="form-control" placeholder="First Name" tabindex="1">
								</div>
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Father's Name" data-toggle="tooltip" data-placement="bottom" name="father_name" id="father_name" class="form-control" placeholder="Father's Name" tabindex="1">
								</div>
								<div class="form-group">
									<label class="sr-only" for="password">date</label>
										<div class="input-group date form_date" id="datepicker" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
											<input class="form-control" rel="txtTooltip" title="Date of Birth" data-toggle="tooltip" data-placement="bottom" size="1" type="text" value="" placeholder="Date of Birth" >
										</div>
									<input type="hidden" id="dtp_input2" value="" />
								</div>
								<div class="form-group">
									<div class="selectContainer">
										<select rel="txtTooltip" title="Gender" data-toggle="tooltip" data-placement="bottom" class="form-control" name="size" placeholder="select gender" class="leguage-select" id = "gender-select">
												<option value="0">Select Gender</option>
												<option value="f">Female</option>
												<option value="m">Male</option>
										</select>
									</div>	
								</div>
								<div class="form-group">
									<div class="selectContainer">
										<select rel="txtTooltip" title="Marital Status" data-toggle="tooltip" data-placement="bottom" class="form-control" name="size" placeholder="select gender" id = "status-select" class="leguage-select">
											<option value="0">Select Marital Status</option>
											<option value="m">Married</option>
											<option value="u">UnMarried</option>
										</select>
									</div>	
								</div>
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Current Location" data-toggle="tooltip" data-placement="bottom" name="current_location" id="current_location" class="form-control" placeholder="Current Location" tabindex="1">
								</div>
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Interested Location" data-toggle="tooltip" data-placement="bottom" name="interested_location" id="interested_location" class="form-control" placeholder="Interested Location" tabindex="1">
								</div>
								<div class="col-md-12">Language Proficiency Details:
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<div class="selectContainer">
												<select rel="txtTooltip" title="Language" data-toggle="tooltip" data-placement="bottom" class="form-control" name="size" placeholder="select gender" id = "language1-select">
													<option value="0">Select Language</option>
													<option value="m">Tamil</option>
													<option value="u">English</option>
													<option value="u">Telugu</option>
												</select>
											</div>
										</div>
										<div class="col-md-2" id="r">
											<input type="checkbox" name="options" value="option2">Read
										</div>
										<div class="col-md-2" id="r">
											<input type="checkbox" name="options" value="option2" id="w">Write
										</div>
										<div class="col-md-2" id="r">
											<input type="checkbox" name="options" value="option2" id="s">Speak
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<div class="selectContainer">
												<select rel="txtTooltip" title="Language" data-toggle="tooltip" data-placement="bottom" class="form-control" name="size" placeholder="select gender" class = "leguage-select" id = "language2-select">
													<option value="0">Select Language</option>
													<option value="m">Tamil</option>
													<option value="u">English</option>
													<option value="u">Telugu</option>
												</select>
											</div>
										</div>
										<div class="col-md-2" id="r">
											<input type="checkbox" name="options" value="option2">Read
										</div>
										<div class="col-md-2" id="r">
											<input type="checkbox" name="options" value="option2">Write
										</div>
										<div class="col-md-2" id="r">
											<input type="checkbox" name="options" value="option2">Speak
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<div class="selectContainer">
												<select rel="txtTooltip" title="Language" data-toggle="tooltip" data-placement="bottom" class="form-control" name="size" placeholder="select gender" class = "leguage-select" id = "language3-select">
													<option value="0">Select Language</option>
													<option value="m">Tamil</option>
													<option value="u">English</option>
													<option value="u">Telugu</option>
												</select>
											</div>
										</div>
										<div class="col-md-2" id="r">
											<input type="checkbox" name="options" value="option2">Read
										</div>
										<div class="col-md-2" id="r">
											<input type="checkbox" name="options" value="option2">Write
										</div>
										<div class="col-md-2" id="r">
											<input type="checkbox" name="options" value="option2"	>Speak
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-about-yourself">KeySkills</label>
									<textarea rel="txtTooltip" title="KeySkills" data-toggle="tooltip" data-placement="bottom" name="form-about-yourself" placeholder="KeySkills" class="form-about-yourself form-control" id="KeySkills"></textarea>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-about-yourself">Company details</label>
									<textarea rel="txtTooltip" title="Company Details" data-toggle="tooltip" data-placement="bottom" name="form-about-yourself" placeholder="Previous Company Details" class="form-about-yourself form-control" id="Company_details"></textarea>
								</div>	
							</div>
							<div class="col-md-6 col-sm-6 col-xs-6">
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Email" data-toggle="tooltip" data-placement="bottom" name="email" id="email" class="form-control" placeholder="Email" tabindex="1">
								</div>
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Mobile" data-toggle="tooltip" data-placement="bottom" name="mobile" id="mobile" class="form-control" placeholder="Mobile Number" tabindex="1">
								</div>
								<div class="form-group">
									<div class="selectContainer">
										<select rel="txtTooltip" title="Experience" data-toggle="tooltip" data-placement="bottom" class="form-control" name="size" placeholder="select gender" id = "experience-select" class = "leguage-select">
											<option value="0">Year Of Experience</option>
											<option value="10">Freshers</option>
											<option value="1">6 months</option>
											<option value="2">1 year</option>
											<option value="3">2 year</option>
											<option value="4">3 year</option>
											<option value="5">4 year</option>
											<option value="6">5 year</option>
											<option value="7">6 year</option>
											<option value="8">7 year</option>
											<option value="9">8 year</option>
										</select>
									</div>	
								</div>
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Applied Post to" data-toggle="tooltip" data-placement="bottom" name="post" id="post" class="form-control" placeholder="Applied Post to" tabindex="1">
								</div>
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Reference CARE Employee" data-toggle="tooltip" data-placement="bottom" name="ref" id="ref" class="form-control" placeholder="Reference CARE Employee" tabindex="1">
								</div>
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Reference CARE Employee Phone" data-toggle="tooltip" data-placement="bottom" name="ref_phone" id="ref_phone" class="form-control" placeholder="Reference CARE Employee Phone" tabindex="1">
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-6">
										Ex_Employee:
										</div>
										<div class=" col-md-6">
											<div class="btn-group btn-toggle" data-toggle="buttons">
												<label class="btn btn-success">
													<input type="radio" name="options" value="option1">Yes
												</label>
												<label class="btn btn-default active">
													<input type="radio" name="options" value="option2" checked="">No
												</label>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											Do you have a bike?
										</div>
										<div class="col-md-6">
											<div class="btn-group btn-toggle" data-toggle="buttons">
												<label class="btn btn-success">
													<input type="radio" name="options" value="option1">Yes
												</label>
												<label class="btn btn-default active">
													<input type="radio" name="options" value="option2" checked="">No
												</label>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Passport Number" data-toggle="tooltip" data-placement="bottom" name="pass_no" id="pass_no" class="form-control" placeholder="Passport Number" tabindex="1">
								</div>
								<div class="form-group">
									<label class="sr-only" for="password">date</label>
										<div class="input-group date form_date" id="datepicker" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
											<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
											<input class="form-control" rel="txtTooltip" title="Passport Expire Date" data-toggle="tooltip" data-placement="bottom" size="1" type="text" value="" placeholder="Passport Expire Date" >
										</div>
											<input type="hidden" id="dtp_input2" value="" />
								</div>
								<div class="form-group">
									<div class="selectContainer">
										<select rel="txtTooltip" title="Qualification" data-toggle="tooltip" data-placement="bottom"class="form-control" name="size" placeholder="select Qualification" class = "leguage-select">
											<option value="0">Select Qualification</option>
											<option value="5">B.E</option>
											<option value="1">B.Tech</option>
											<option value="2">MCA</option>
											<option value="3">M.E</option>
											<option value="4">M.Tech</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-about-yourself">Certificate (If any)</label>
									<textarea rel="txtTooltip" title="Certificate" data-toggle="tooltip" data-placement="bottom" name="form-about-yourself" placeholder="Certificate (If any)" class="form-about-yourself form-control" id="certificate"></textarea>
								</div>	
								<div class="form-group">
									<label class="sr-only" for="form-about-yourself">brief_description</label>
									<textarea rel="txtTooltip" title="Brief description" data-toggle="tooltip" data-placement="bottom" name="form-about-yourself" placeholder="Brief Description" class="form-about-yourself form-control" id="brief_description"></textarea>
								</div>	
								<div class="form-group">
									<input type="file" rel="txtTooltip" title="choose file to upload" data-toggle="tooltip" data-placement="bottom">
								</div>
							</div>
						</div>
							<div class="row">
							<div class="col-md-2 col-md-offset-4">
								<div class="form-group">
									<input type="submit" value="Submit" class="btn btn-primary btn-block" tabindex="7">
								</div>	
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<a href="#" class="btn btn-success btn-block" data-dismiss="modal" data-toggle="modal" id="close">Close</a>
									
								</div>
							</div>
						</div>
						
					</form>	
				</div>
			</div>				
		</div>
	</div>

	<!--Verification Modal-->
	<div class="modal fade verification" tabindex="-1" role="dialog" aria-labelledby="verification" aria-hidden="true" id="verification">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">EMPLOYEE VERIFICATION</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="" method="post" class="registration-form" >
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Name" data-toggle="tooltip" data-placement="bottom" name="name" id="name" class="form-control" placeholder="Name" tabindex="1">
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Company Name" data-toggle="tooltip" data-placement="bottom" name="company_name" id="company_name" class="form-control" placeholder="Company Name" tabindex="1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<label class="sr-only" for="form-about-yourself">Address</label>
									<textarea rel="txtTooltip" title="Address" data-toggle="tooltip" data-placement="bottom" name="form-about-yourself" placeholder="Address" class="form-about-yourself form-control" id="Address"></textarea>
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" name="email" rel="txtTooltip" title="Email" data-toggle="tooltip" data-placement="bottom" id="email-verify" class="form-control" placeholder="Email" tabindex="1">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Mobile" data-toggle="tooltip" data-placement="bottom" name="mobile" id="mobile" class="form-control" placeholder="Mobile" tabindex="1">
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input type="text" rel="txtTooltip" title="Phone" data-toggle="tooltip" data-placement="bottom" name="phone" id="phone" class="form-control" placeholder="Phone" tabindex="1">
								</div>
													
							</div>
						</div>
						<div class="form-group">
							(CISMAR2015007672) Enter 16 Digit Character For Resigned People
						</div>	
						<div class="form-group">
							<input type="text" rel="txtTooltip" title="Verification Employee ID" data-toggle="tooltip" data-placement="bottom" name="empid" id="empid" class="form-control" placeholder="Verification Employee ID" tabindex="1">
						</div>	
						<div class="row">
							<div class="col-md-2 col-md-offset-4">
								<div class="form-group">
									<input type="submit" value="Submit" class="btn btn-primary btn-block" tabindex="7">
									
									</div>	
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<a href="#" class="btn btn-success btn-block" data-dismiss="modal" data-toggle="modal" id="close">Close</a>	
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>	
	</div>
	
	
<footer>
 <br>
	 <sql:query var="rsfooter" dataSource="jdbc/spring">SELECT CHR_WEBSITE,CHR_NAME FROM m_company  WHERE INT_CPYID=1</sql:query>
	 <c:forEach items="${rsfooter.rows}" var="currentItem">
	 <p class="text-center">&copy; 2007 <a href='${currentItem.CHR_WEBSITE}' class = "active"> ${currentItem.CHR_NAME}</a> </p>
	 </c:forEach>	
	 
</footer>

	<!-- Javascript -->
        <script src="resources/js/jquery-1.11.3.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
		<script src="resources/js/jquery.backstretch.min.js"></script>
        <script src="resources/js/retina-1.1.0.min.js"></script>
        <script src="resources/js/scripts.js"></script>
		<script src="resources/js/bootstrap-datetimepicker.js"></script>
		<script src="resources/js/bootstrap-datetimepicker.fr.js"></script>
       

    </body>

</html>




 