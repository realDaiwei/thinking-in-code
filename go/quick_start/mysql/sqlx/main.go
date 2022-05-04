package main

import (
	"fmt"

	_ "github.com/go-sql-driver/mysql"
	"github.com/jmoiron/sqlx"
)

var db *sqlx.DB

func initDB() (err error) {
	connStr := "root:daiwei@tcp(127.0.0.1:3306)/test"
	db, err = sqlx.Connect("mysql", connStr)
	if err != nil {
		fmt.Printf("open connection failed, err:%#v\n", err)
		return
	}
	err = db.Ping()
	if err != nil {
		fmt.Printf("conntect db failed, err:%#v\n", err)
		return
	}
	db.SetMaxOpenConns(10)
	db.SetMaxIdleConns(3)
	return
}

type user struct {
	Id   int
	Name string
	Age  int
}

/*
query one by sqlx
*/
func queryOne(id int) {
	sqlStr := "select id, name, age from tb_user where id = ?"
	var u user
	err := db.Get(&u, sqlStr, id)
	if err != nil {
		fmt.Printf("select data failed, err:%#v\n", err)
		return
	}
	fmt.Printf("data seleted: %#v\n", u)
}

/*
query batch by sqlx
*/
func queryList(greateThanId int) {
	sqlStr := "select id, name, age from tb_user where id >= ?"
	var users []user
	err := db.Select(&users, sqlStr, greateThanId)
	if err != nil {
		fmt.Printf("select data err, err:%#v\n", err)
		return
	}
	fmt.Printf("data was selected: %#v\n", users)
}

func main() {
	err := initDB()
	if err != nil {
		fmt.Printf("init db failed, err:%#v\n", err)
		return
	}
	queryOne(2)
	queryList(3)
}
