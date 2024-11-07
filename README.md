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

```bash
mysql -u your_username -p your_database_name < ressources/sql/script.sql
```

Replace `your_username` with your MySQL username.

Replace `your_database_name` with the name of the database you created.



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
```

Then, run the back-end:

```ini
run backend.projet2/src/main/java/com/tanou/projet/oc/backend/projet2/Application.java
```
