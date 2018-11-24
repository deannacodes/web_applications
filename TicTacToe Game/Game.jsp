<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tic-Tac-Toe</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">		
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<style>
	table {
		width: 300px;
		max-width: 300px;
	}
	.ttt {
		display: block;
		background-color: #efefef;
		font-size: 45px;
		height: 70px;
		width: 70px;
		text-align: center;
		transition: 0.3s all ease-in-out;
	}
	.ttt:hover {
		background-color: #dddddd;
	}
	.ng {
		float: right;
	}
	.alert {
		width: 100%;
	}
</style>

</head>
<body>
	<div class="container" style="padding-top:60px;">
		<div class="row justify-content-between">
			<h1 class="col-auto">Tic-Tac-Toe</h1>
			<div class="col-auto">
				<a class="btn btn-primary ng" href="NewGame">New Game</a>
			</div>
		</div>
		
		<hr>
		
		<c:if test="${game.gameOver}">
			<div class="row">
				<div class="alert alert-success" role="alert">
					<h4>Game Over!</h4>
					<p>Winner: ${ game.winningPlayerName }</p>
				</div>
			</div>
		</c:if>
		
		<c:if test="${!game.gameOver}">
			<h4 class="text-center">${ game.currentPlayerName }'s Turn</h4>
		</c:if>
		
		
		
		<div class="row justify-content-center text-center">
		
		

			<table class="table table-bordered">
			
			
				<tr>
					<td><a class="ttt" href="Play?row=0&col=0">${ game.gameBoard[0][0] }</a></td>
					<td><a class="ttt" href="Play?row=0&col=1">${ game.gameBoard[0][1] }</a></td>
					<td><a class="ttt" href="Play?row=0&col=2">${ game.gameBoard[0][2] }</a></td>
				</tr>
				<tr>
					<td><a class="ttt" href="Play?row=1&col=0">${ game.gameBoard[1][0] }</a></td>
					<td><a class="ttt" href="Play?row=1&col=1">${ game.gameBoard[1][1] }</a></td>
					<td><a class="ttt" href="Play?row=1&col=2">${ game.gameBoard[1][2] }</a></td>
				</tr>
				<tr>
					<td><a class="ttt" href="Play?row=2&col=0">${ game.gameBoard[2][0] }</a></td>
					<td><a class="ttt" href="Play?row=2&col=1">${ game.gameBoard[2][1] }</a></td>
					<td><a class="ttt" href="Play?row=2&col=2">${ game.gameBoard[2][2] }</a></td>
				</tr>
				
			
			</table>
		
		</div>
		
		
	
		<div class="row justify-content-center" style="padding-top: 40px;">
		
			<table class="table">
			
			
					<tr>
						<td colspan="2">
							<h4>Scorecard</h4>
						</td>
					</tr>
			
				<tr>
					<td> 
						Player 1 (X) Score:
					</td>
					<td>
						${ xScore }
					</td>
				</tr>
				
				<tr>
					<td> 
						Player 2 (O) Score:
					</td>
					<td>
						${ oScore }
					</td>
				</tr>
				
				<tr>
					<td> 
						Cat's Games:
					</td>
					<td>
						${ ties }
					</td>
				</tr>
			
			</table>
		</div>
	</div>
</body>
</html>