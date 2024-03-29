@import exercise.dto.users.UsersPage

@param UsersPage usersPage

<html>
	<head>
		<meta charset="UTF-8">
	</head>
    <body>
   		<table>
    		@for (var user : usersPage.getUsers())
    		<tr>
    			<td>
    				<a href="/users/${user.getId()}">Пользователь №${user.getId()}</a>
    			</td>
    		</tr>
    		@endfor    		
    	</table>
    </body>
</html>
