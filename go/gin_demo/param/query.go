package main

import (
	"encoding/json"
	"fmt"
	"github.com/gin-gonic/gin"
	"net/http"
)

type Login struct {
	User     string `form:"user" json:"user" binding:"required"`
	Password string `form:"password" json:"password" binding:"required"`
}

func main() {
	r := gin.Default()
	r.GET("/user", func(c *gin.Context) {
		name := c.Query("name") // query方法获取getParam
		c.JSON(http.StatusOK, gin.H{"name": name})
	})

	r.GET("/param/:user/:age", func(c *gin.Context) {
		username := c.Param("user") // Param方法获取path value
		age := c.Param("age")
		c.JSON(http.StatusOK, gin.H{"username": username, "age": age})
	})

	r.POST("/request-body", func(c *gin.Context) {
		data, _ := c.GetRawData() // 获取request-body
		var m map[string]interface{}
		err := json.Unmarshal(data, &m)
		if err != nil {
			fmt.Printf("unmarshal failed, err:%#v\n", err)
		}
		fmt.Printf("request body: %#v\n", m)
		c.JSON(http.StatusOK, m)
	})

	// 使用 shouldBind 绑定json/form数据
	r.POST("/login-bind", func(c *gin.Context) {
		var l Login
		if err := c.ShouldBind(&l); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"err": err.Error()})
		} else {
			c.JSON(http.StatusOK, gin.H{
				"user":     l.User,
				"password": l.Password,
			})
		}
	})

	// 使用 shouldBind 绑定 query 数据
	r.GET("/login-bind", func(c *gin.Context) {
		var l Login
		if err := c.ShouldBind(&l); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"err": err.Error()})
		} else {
			c.JSON(http.StatusOK, gin.H{
				"user":     l.User,
				"password": l.Password,
			})
		}
	})

	err := r.Run(":8090")
	if err != nil {
		return
	}
}
