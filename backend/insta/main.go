package main

import (
	"database/sql"
	"fmt"
	"insta/database_insta"

	_ "github.com/lib/pq"
)

func main() {
	database_insta.DatabasePSQLConnection()
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
