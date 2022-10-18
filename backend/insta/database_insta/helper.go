package database_insta

import (
	"database/sql"
	"fmt"
)

type DatabaseContent struct {
	TableName string
	TableData string
}

func CreateTable(db *sql.DB, databaseContent DatabaseContent) error {
	var err error

	sqlStatement := fmt.Sprintf("CREATE TABLE %s (%s)", databaseContent.TableName, databaseContent.TableData)
	// sqlStatement := `CREATE TABLE posts (id SERIAL PRIMARY KEY, post_text TEXT, post_image TEXT,post_video TEXT,post_likes INT ,post_comments INT,post_time TEXT)`

	_, err = db.Exec(sqlStatement)

	return err
}
