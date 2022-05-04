package main

import (
	"fmt"
	"os"
)

type student struct {
	id   int64
	name string
}

func newStudent(id int64, name string) *student {
	return &student{
		id:   id,
		name: name,
	}
}

var (
	studentMap map[int64]*student = make(map[int64]*student, 64)
)

// 输出答应所有的学员关系
func showAllStudent() {
	fmt.Println("所有学员信息如下：")
	for k, v := range studentMap {
		fmt.Printf("student id is %d, student name :%s\n", k, v.name)
	}
}

// 添加学员
func addStudent() {
	fmt.Println("添加学员！！")
	var (
		id   int64
		name string
	)
	fmt.Println("输入学员学号：")
	fmt.Scanln(&id)
	fmt.Println("输入学员名字：")
	fmt.Scanln(&name)

	fmt.Printf("学员Id: %d\n", id)
	fmt.Printf("学员name: %s\n", name)
	_, ok := studentMap[id]
	if ok {
		fmt.Println("学员已存在，无法添加！")
	} else {
		studentMap[id] = newStudent(id, name)
		fmt.Println("添加完成！")
	}
}

// 删除学生
func removeStudent() {
	fmt.Println("请输入要删除学员的Id")
	var sutId int64
	fmt.Scanln(&sutId)
	fmt.Println("要删除的学员Id是: ", sutId)
	delete(studentMap, sutId)
	fmt.Println("删除成功！！")
}

//退出系统
func exitSystem() {
	fmt.Println("好好好，退出退出～")
	os.Exit(0)
}

func main() {

	for {
		fmt.Println(`欢迎进入学生管理系统(函数版)`)
		fmt.Println(`
			1. 查看所有学生
			2. 新增学生
			3. 删除学生
			4. 退出系统
		`)
		fmt.Println("你要输入啥？？")
		var input int
		fmt.Scanln(&input)
		fmt.Println("输入的结果是：", input)
		switch input {
		case 1:
			showAllStudent()
		case 2:
			addStudent()
		case 3:
			removeStudent()
		case 4:
			exitSystem()
		default:
			fmt.Println("别搞事好伐～")
		}
	}
}
