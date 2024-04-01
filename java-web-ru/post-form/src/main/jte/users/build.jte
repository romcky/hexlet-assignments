@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <h1>Регистрация нового пользователя</h1>
        
        <form method="post" action="/users">
        	<div>
        		<label> 
        			FirstName:
        			<input type="text" name="firstName" />
        		</label>
        	</div>
        	<div>
        		<label>
        			LastName:
        			<input type="text" name="lastName" />
        		</label>
        	</div>
        	<div>
        		<label>
        			Email:
        			<input type="email" name="email" />
        		</label>
        	</div>
        	<div>
        		<label>
        			Password:
        			<input type="password" name="password" />
        		</label>
        	</div>
        	<input type="submit" value="Registry"> 		
        </form>   
    `
)
