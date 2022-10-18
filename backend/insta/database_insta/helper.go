package database_insta

import (
	"database/sql"
	"fmt"
)

func CreateTable(db *sql.DB, err error, databaseContent DatabaseContent) {
	sqlStatement := fmt.Sprintf("CREATE TABLE %s (%s)", databaseContent.TableName, databaseContent.TableData)
	// sqlStatement := `CREATE TABLE posts (id SERIAL PRIMARY KEY, post_text TEXT, post_image TEXT,post_video TEXT,post_likes INT ,post_comments INT,post_time TEXT)`

	_, err = db.Exec(sqlStatement)

	if err != nil {
		fmt.Println(err)
		panic(err)
	}
	fmt.Println("Table has created successfully")
}

type DatabaseContent struct {
	TableName string
	TableData string
}
