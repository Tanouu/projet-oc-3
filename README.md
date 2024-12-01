# Procedure to install the project

## Procedure to install the database

### Install MySQL

Download and install MySQL from [the official site](https://dev.mysql.com/downloads/).

### Create the database

Connect to MySQL and execute the following command to create the database:

```sql
CREATE DATABASE your_database_name;
```

### Import the SQL script

Import the SQL script to create the tables and insert initial data:

```ini
ressources/sql/script.sql
```

### Install dependencies

```bash
npm install
```

### Launch the front-end

```bash
ng serve
```

### Launch the back-end

Ensure that the back-end is configured to serve static files and that the `upload` folder is at the root of the back-end.

Create a `.env` file at the root of the `backend.projet2` folder with the following content:

```ini
DB_URL=jdbc:mysql://localhost:3306/your_database_name
DB_USERNAME=your_username

DB_PASSWORD=your_password
SERVER_BASE_URL=your_server_base_url (ex: http://localhost:3001)
```

Then, run the back-end:

```ini
run backend.projet2/src/main/java/com/tanou/projet/oc/backend/projet2/Application.java
```

### Swagger

http://your_server_base_url/swagger-ui/index.html
