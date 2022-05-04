package main

import (
	"database/sql"
	"fmt"

	_ "github.com/go-sql-driver/mysql"
)

type user struct {
	id   int64
	name string
	age  int
}

var db *sql.DB

func initDB() (err error) {
	connStr := "root:daiwei@tcp(127.0.0.1:3306)/test"
	// 打开一个数据库连接
	db, err = sql.Open("mysql", connStr)
	if err != nil {
		fmt.Printf("connStr:%s invalid, err:%v\n", connStr, err)
		return
	}
	db.SetMaxOpenConns(10) // 数据库连接池最大连接数
	db.SetMaxIdleConns(3)  // 数据库最大空闲连接数
	return db.Ping()
}

/*
 查询方法
*/
func query(id int) {
	var u1 user
	sqlStr := "select id, name, age from tb_user where id = ?"
	// db.QueryRow(sqlStr, 1).Scan(&u1.id, &u1.name, &u1.age)
	db.QueryRow(sqlStr, 1).Scan(&u1.id, &u1.name, &u1.age)
	fmt.Printf("u1:%#v\n", u1)
}

/*
query user batch
*/
func queryRows(lessThanId int) {
	sqlStr := "select id, name, age from tb_user where id < ?"
	rows, err := db.Query(sqlStr, lessThanId)
	if err != nil {
		fmt.Printf("query error, error is %#v\n", err)
		return
	}
	defer rows.Close()
	for rows.Next() {
		var u user
		rows.Scan(&u.id, &u.name, &u.age)
		fmt.Printf("%#v\n", u)
	}
}

/*
insert data
*/
func insert() {
	sqlStr := "insert into tb_user(`name`, age) values('ztt', 25),('clh', 26),('pzy', 26),('adu', 24)"
	res, err := db.Exec(sqlStr)
	if err != nil {
		fmt.Printf("insert failed, err is %#v\n", err)
		return
	}
	id, err := res.LastInsertId()
	if err != nil {
		fmt.Printf("insert result err, err %#v\n", err)
		return
	}
	fmt.Printf("last insert id is %#v\n", id)
}

/*
update data
*/
func update() {
	sqlStr := "update tb_user set name = 'daiwei666' where id = 1"
	res, err := db.Exec(sqlStr)
	if err != nil {
		fmt.Printf("update err, err: %#v\n", err)
	}
	n, err := res.RowsAffected()
	if err != nil {
		fmt.Printf("affect row err: %v\n", err)
		return
	}
	fmt.Printf("update affect num of rows: %d\n", n)
}

/*
delete operation
*/
func deleted(deletedId int) {
	sqlStr := "delete from tb_user where id = ?"
	res, err := db.Exec(sqlStr, deletedId)
	if err != nil {
		fmt.Printf("exec err, err:%#v\n", err)
	}
	n, err := res.RowsAffected()
	if err != nil {
		fmt.Printf("affect row err, err:%#v\n", err)
	}
	fmt.Printf("deleted operation affect row %d\n", n)
}

/*
insert batch by prepare
*/
func prepareInsert() {
	sqlStr := "insert into tb_user(`name`, age) value(?, ?)"
	stmt, err := db.Prepare(sqlStr)
	if err != nil {
		fmt.Printf("prepare failed, err:%#v\n", err)
	}
	defer stmt.Close()
	var m = map[string]int{
		"张三":     25,
		"李四":     24,
		"王五":     23,
		"daiwei": 26,
	}
	for k, v := range m {
		stmt.Exec(k, v)
	}
}

/*
transaction
*/
func txUpdate() {
	sqlStr1 := "update tb_user set age = age + 1 where id = 2"
	sqlStr2 := "update tb_user set age = age - 1 where id = 3"
	tx, err := db.Begin() // 开启一个事务
	if err != nil {
		fmt.Printf("start transaction failed, err:%v\n", err)
	}
	_, err = tx.Exec(sqlStr1)
	if err != nil {
		fmt.Printf("sql1 exec err, err:%v\n", err)
		tx.Rollback()
	}
	_, err = tx.Exec(sqlStr2)
	if err != nil {
		fmt.Printf("sql1 exec err, err:%v\n", err)
		tx.Rollback() // 必须手动回滚，不然事务不生效
	}
	tx.Commit() // 统一提交
	fmt.Printf("tx update finished～\n")
}

func main() {
	err := initDB()
	if err != nil {
		fmt.Println("open conn failed")
	}
	// query(1)
	// fmt.Println("======================")
	// update()
	// queryRows(10)
	// deleted(1)
	// insert()
	// prepareInsert()
	queryRows(20)
	txUpdate()
	queryRows(20)
}
