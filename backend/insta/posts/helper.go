package posts

import (
	"database/sql"
	"fmt"
	"insta/database_insta"
	"log"
)

// used just one time to create table
func createPostTable(db *sql.DB) error {
	// to create table just one
	err := database_insta.CreateTable(db, database_insta.DatabaseContent{
		TableName: "posts",
		TableData: "id SERIAL PRIMARY KEY, post_text TEXT, post_image TEXT,post_video TEXT,post_likes INT ,post_comments INT,post_time TEXT",
	})

	return err
}

// GetPosts return all posts from database
// should pass the database connection and [error]
func getPostsFromDatabase(db *sql.DB) ([]SocialMediaPost, error) {
	sql_statement := `SELECT * FROM posts`

	var err error

	rows, err := db.Query(sql_statement)

	var posts []SocialMediaPost

	for rows.Next() {
		var id int
		var post_text string
		var post_image string
		var post_video string
		var post_likes int
		var post_comments int
		var post_time string

		err = rows.Scan(&id, &post_text, &post_image, &post_video, &post_likes, &post_comments, &post_time)
		if err != nil {
			// handle this error
			panic(err)
		}
		posts = append(posts, SocialMediaPost{id, post_text, post_image, post_video, post_likes, post_comments, post_time})
		fmt.Println(id, post_text, post_image, post_video, post_likes, post_comments, post_time)
	}

	// get any error encountered during iteration
	err = rows.Err()

	return posts, err

}

// SocialMediaPost is the struct for posts
func getPostsList() []SocialMediaPost {

	// for whole project get the db & error
	db, err := database_insta.DatabasePSQLConnection()

	// check error for whole
	if err != nil {
		log.Println("error_open_db:: ", err)
		panic(err)
	}

	// close the db after end the program
	defer db.Close()

	// get posts from db
	posts_from_db, err := getPostsFromDatabase(db)

	if err != nil {
		log.Println("error_getPosts:: ", err)

		return []SocialMediaPost{}
	}

	return posts_from_db
}
