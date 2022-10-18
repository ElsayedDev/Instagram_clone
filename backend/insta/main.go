package main

import (
	"database/sql"
	"fmt"

	_ "github.com/lib/pq"
)

const (
	host     = "localhost"
	port     = 5432
	user     = "postgres"
	password = "1234"
	dbname   = "test"
)

func main() {
	psqlInfo := fmt.Sprintf("host=%s port=%d user=%s "+
		"password=%s dbname=%s sslmode=disable",
		host, port, user, password, dbname)
	fmt.Println(psqlInfo)

	db, err := sql.Open("postgres", psqlInfo)

	if err != nil {
		fmt.Println(err)
		panic(err)
	}
	defer db.Close()

	// to create table
	createTableInDb(db, err)

}

func insertInDB(db *sql.DB, err error) {
	sqlStatement := `
	INSERT INTO users (age, email, first_name, last_name)
	VALUES ($1, $2, $3, $4)
	returning id
	`
	id := 0

	err = db.QueryRow(sqlStatement, 30, "jon@calhoun.io", "Jonathan", "Calhoun").Scan(&id)

	if err != nil {

		fmt.Println(err)

		panic(err)
	}

	fmt.Println("New record ID is:", id)
}

func createTableInDb(db *sql.DB, err error) {
	sqlStatement := `CREATE TABLE users (id SERIAL PRIMARY KEY, age INT, email TEXT, first_name TEXT, last_name TEXT)`
	_, err = db.Exec(sqlStatement)

	if err != nil {
		fmt.Println(err)
		panic(err)
	}
	fmt.Println("Table has created successfully")
}

func selectFromDb(db *sql.DB, err error) {
	sqlStatement := `SELECT * FROM users;`
	rows, err := db.Query(sqlStatement)
	if err != nil {
		fmt.Println(err)
		panic(err)
	}
	defer rows.Close()

	for rows.Next() {
		var id int
		var age int
		var email string
		var first_name string
		var last_name string

		err = rows.Scan(&id, &age, &email, &first_name, &last_name)
		if err != nil {
			fmt.Println(err)
			panic(err)
		}
		fmt.Println(id, age, email, first_name, last_name)
	}

	err = rows.Err()
	if err != nil {
		fmt.Println(err)
		panic(err)
	}
}
