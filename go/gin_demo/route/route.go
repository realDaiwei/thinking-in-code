package main

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func main() {
	s := gin.Default()

	// route接受任何参数
	s.Any("/route", func(c *gin.Context) {
		c.JSON(http.StatusOK, gin.H{
			"method": c.Request.Method,
		})
	})

	// 一个简单的路由组
	group := s.Group("/user")
	{
		group.GET("/index", func(c *gin.Context) {
			c.JSON(http.StatusOK, gin.H{"uri": c.Request.RequestURI})
		})
		group.GET("/home", func(c *gin.Context) {
			c.JSON(http.StatusOK, gin.H{"uri": c.Request.RequestURI})
		})
		group.GET("/login", func(c *gin.Context) {
			c.JSON(http.StatusOK, gin.H{"uri": c.Request.RequestURI})
		})
	}
	// 路由组再路由组
	second := group.Group("/path")
	{
		second.GET("/hello", func(c *gin.Context) {
			c.JSON(http.StatusOK, gin.H{"uri": c.Request.RequestURI})
		})
		second.GET("/bye", func(c *gin.Context) {
			c.JSON(http.StatusOK, gin.H{"uri": c.Request.RequestURI})
		})
	}

	// 没有匹配上路由的请求
	s.NoRoute(func(c *gin.Context) {
		c.JSON(http.StatusNotFound, gin.H{"where is the page location": "i don't known!"})
	})

	err := s.Run(":8090")
	if err != nil {
		panic(err)
	}
}
