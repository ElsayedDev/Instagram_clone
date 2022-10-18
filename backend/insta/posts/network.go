package posts

import (
	"encoding/json"
	"log"
	"net/http"

	"github.com/gorilla/mux"

	_ "github.com/lib/pq"
)

const (
	port             = ":8080"
	getPostsEndpoint = "/api/posts"
)

func getPosts(w http.ResponseWriter, _ *http.Request) {

	// get posts from db
	// incase of error it get empty array
	// posts_from_db := getPostsList()
	posts_from_db := []SocialMediaPost{}

	var message string
	var status bool

	if len(posts_from_db) == 0 {
		message = "something went wrong"
		status = false
	} else {
		message = "success"
		status = true
	}

	var response NetworkResponse = NetworkResponse{status, message, posts_from_db}

	w.Header().Set("Content-Type", "application/json")

	json.NewEncoder(w).Encode(response)

}

func MainPostsNetwork() {
	r := mux.NewRouter()
	r.HandleFunc(getPostsEndpoint, getPosts).Methods("GET")

	log.Fatal(http.ListenAndServe(port, r))

	log.Println("posts_network:: ", "started")
}
