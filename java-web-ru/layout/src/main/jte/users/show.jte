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
    				Name: ${userPage.getUser().getFirstName()} ${userPage.getUser().getLastName()}
    			</td>
    		</tr>    	
			<tr>
    			<td>
    				Email: ${userPage.getUser().getEmail()}
    			</td>
    		</tr>  	
    	</table>
	`)
		
