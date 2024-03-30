@import exercise.dto.users.UsersPage

@param UsersPage usersPage

@template.layout.page(
	content = @`
   		<table>
    		@for (var user : usersPage.getUsers())
    		<tr>
    			<td>
    				<a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
    			</td>
    		</tr>
    		@endfor    		
    	</table>

	`)
		
