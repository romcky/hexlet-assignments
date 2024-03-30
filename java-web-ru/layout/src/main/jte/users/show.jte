@import exercise.dto.users.UserPage

@param UserPage userPage

@template.layout.page(
	content = @`
   		<table>
    		<tr>
    			<td>
    				Id: ${userPage.getUser().getId()}
    			</td>
    		</tr>
			<tr>
    			<td>
    				FirstName: ${userPage.getUser().getFirstName()}
    			</td>
    		</tr>
			<tr>
    			<td>
    				LastName: ${userPage.getUser().getLastName()}
    			</td>
    		</tr>     	
			<tr>
    			<td>
    				Email: ${userPage.getUser().getEmail()}
    			</td>
    		</tr>  	
    	</table>
	`)
		
