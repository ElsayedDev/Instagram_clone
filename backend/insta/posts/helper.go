package posts

import (
	"database/sql"
	"insta/database_insta"
)

// used just one time to create table
func CreatePostTable(db *sql.DB, err error) {
	// to create table just one
	database_insta.CreateTable(db, err, database_insta.DatabaseContent{
		TableName: "posts",
		TableData: "id SERIAL PRIMARY KEY, post_text TEXT, post_image TEXT,post_video TEXT,post_likes INT ,post_comments INT,post_time TEXT",
	})
}

func getPosts() {
	post := socialMediaPost{
		Id:            1,
		Post_text:     "Hello World",
		Post_image:    "image",
		Post_video:    "video",
		Post_likes:    10,
		Post_comments: 20,
		Post_time:     "2020-01-01",
	}

	// return post

	return post.Id, post.Post_text, post.Post_image, post.Post_video, post.Post_likes, post.Post_comments, post.Post_time

}
