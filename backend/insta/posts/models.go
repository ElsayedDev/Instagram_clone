package posts

type SocialMediaPost struct {
	Id            int    `json:"id"`
	Post_text     string `json:"text"`
	Post_image    string `json:"image"`
	Post_video    string `json:"video"`
	Post_likes    int    `json:"likes"`
	Post_comments int    `json:"comments"`
	Post_time     string `json:"time"`
}

type NetworkResponse struct {
	Status  bool        `json:"status"`
	Message string      `json:"message"`
	Data    interface{} `json:"data"`
}
