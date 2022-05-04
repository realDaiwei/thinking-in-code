package main

import (
	"database/sql"
	"fmt"

	_ "github.com/go-sql-driver/mysql"
)

func main() {
	connStr := "root:daiwei@tcp(127.0.0.1:3306)/test"
	// 打开一个数据库连接
	db, err := sql.Open("mysql", connStr)
	if err != nil {
		fmt.Printf("connStr:%s invalid, err:%v\n", connStr, err)
		return
	}
	// ping一下，ping通了就是连接上了
	err = db.Ping()
	if err != nil {
		fmt.Printf("open %s failed, err:%v\n", connStr, err)
		return
	}
	fmt.Println("数据库连接成功～")
}
