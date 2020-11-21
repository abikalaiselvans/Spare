<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<select id="selectBox" name="selectBox">
    <option value="option1"> option1 </option>
    <option value="option2"> option2 </option>
    <option value="option3"> option3 </option>
    <option value="option4"> option4 </option>
</select>

<script type="text/javascript">
$(document).ready(function(){
    $("#add").click(function(){
    	
    	var _targ=document.getElementsByName('selectBox')[0];       
		_targ.options.length=0;
		_targ.options[0]=new Option('Select selectBox','0');
		for (loop = 0; loop < 10; loop++) 
			_targ.options[loop+1]=new Option("I - Value :"+loop,loop);
		
    });
});


$(document).ready(function(){
    $("#remove").click(function(){
    	
    	var _targ=document.getElementsByName('selectBox')[0];       
		_targ.options.length=0;
		_targ.options[0]=new Option('Select selectBox','0');
		 
		
    });
}); 
</script>

 
<button type="button" id='add' name='add'  class="btn btn-default">Add</button>
<button type="button" id='remove' name='remove'  class="btn btn-default">Remove</button> 
</body>
</html>