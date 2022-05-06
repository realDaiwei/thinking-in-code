package main

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

type msg struct {
	Name    string `json:"name"`
	Message string `json:"message"`
	Age     int    `json:"age"`
}

func main() {
	r := gin.Default()

	r.GET("/json", func(c *gin.Context) {
		//data := map[string]interface{}{
		//	"name":    "小王子",
		//	"message": "hello world",
		//	"age":     18,
		//}
		// 其实gin.H就是以map[string]interface{}创建的类型，也就是一个map
		data := gin.H{"name": "小王子", "message": "hello world", "age": 18}
		c.JSON(http.StatusOK, data)
	})

	r.GET("/get-by-struct", func(c *gin.Context) {
		c.JSON(http.StatusOK, &msg{"daiwei", "hello gin", 18})
	})

	r.Run(":8090")
}
