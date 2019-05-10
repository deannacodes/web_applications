<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Expression Language Test</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">		
</head>
<body>
	<div class="container" style="padding-top:60px;">
		<table class="table table-striped table-bordered">
		
			<tr> 
				<th colspan=2>Arithmetic Operators</th>
				<th colspan=2>Relational Operators</th>
			</tr>
			
			<tr>
				<th>Expression</th>
				<th>Result</th>
				<th>Expression</th>
				<th>Result</th>
			</tr>
			
			<tr>
				<td>${ '${'} "abc" lt 'b'}</td>
				<td>${null eq 'null'}</td>
				<td>${ '${'} "a" + 3 div 2}</td>
				<td></td>
			</tr>
				
			<tr>
				<td>${ '${'} "1"+2 }</td>
				<td>${ "1"+2 }</td>
				<td>${ '${'} "a"<"b" }</td>		
				<td>${ "a"<"b" }</td>
			</tr>
			
			<tr>		
				<td>${ '${'} 1+2*3+3/4 }</td>
				<td>${ 1+2*3+3/4 }</td>
				<td>${ '${'} 2/3 >= 3/2 }</td>
				<td>${ 2/3 >= 3/2 }</td>
			</tr>
			
			<tr>		
				<td>${ '${'} 3%2 }</td>
				<td>${ 3%2 }</td>
				<td>${ '${'} 3/4 == 0.75 }</td>
				<td>${ 3/4 == 0.75 }</td>
			</tr>
			
			<tr>
				<td>${ '${'} (8 div 2) mod 3 }</td>
				<td>${ (8 div 2) mod 3 }</td>
				<td>${ '${'} null == "test" }</td>
				<td>${ null == "test" }</td>
			</tr>
			
			<tr> 
				<th colspan=2>Logical Operators</th>
				<th colspan=2><span style="background-color:#eee; font-family:Courier New; color: #c7254f">empty</span> Operator</th>
			</tr>
			
			<tr>
				<th>Expression</th>
				<th>Result</th>
				<th>Expression</th>
				<th>Result</th>
			</tr>
			
			<tr>
				<td>${ '${'} (1<2) && (4<3) }</td>
				<td>${ (1<2) && (4<3) }</td>
				<td>${ '${'} empty "" }</td>
				<td>${ empty "" }</td>
			</tr>
			
			<tr>
				<td>${ '${'} (1<2) || (4<3) }</td>
				<td>${ (1<2) || (4<3) }</td>
				<td>${ '${'} empty null }</td>
				<td>${ empty null }</td>
			</tr>
			
			<tr>
				<td>${ '${'} !(1<2) }</td>
				<td>${ !(1<2) }</td>
				<td>${ '${'} empty param.blah }</td>
				<td>${ empty param.blah }</td>
			</tr>
		
		</table>
	</div>
</body>
</html>