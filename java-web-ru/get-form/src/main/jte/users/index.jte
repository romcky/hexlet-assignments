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
    		
    		
    		<tr>
    			<td>
    				<form>
    					<label for="term_id">Search by firstName:</label>
    					<input type="search" id="term_id" name="term" value="${usersPage.getTerm()}"/>
    					<input type="submit" value="Search"/>
    				</form>
    			</td>
    		</tr>  
        </table>
    `
)
