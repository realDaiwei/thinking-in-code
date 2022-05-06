package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
)

func main() {
	// 创建一个默认的路由引擎
	r := gin.Default()
	// GET: 请求方式; /hello: 请求的路径
	// 当客户端以Get方法请求/hello路径时，会执行后面的匿名函数
	r.GET("/hello", func(c *gin.Context) {
		//c.JSON: 返回JSON格式的数据
		c.JSON(200, gin.H{
			"message": "Hello world!",
		})
	})
	// 启动HTTP服务，默认在0.0.0.0:8080启动
	err := r.Run(":8081")
	if err != nil {
		fmt.Printf("server start err, err:%#v\n", err)
	}
}
