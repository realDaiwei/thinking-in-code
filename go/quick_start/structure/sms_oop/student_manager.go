package main

import (
	"fmt"
	"os"
)

type student struct {
	id     string
	name   string
	age    int
	gender string
}

type stuMgr struct {
	allStudentMap map[string]*student
}

func (m stuMgr) printWelcome() {
	fmt.Println("===================================")
	fmt.Println("欢迎进入学生管理系统！！")
	fmt.Println(`目前系统有一下功能，输入指令:
			1. 展示所有学员
			2. 添加学员
			3. 删除学员
			4. 退出
		请输入指令～
		`)
}

func (m stuMgr) printEnd() {
	fmt.Println("===================================")
}

//输出展示所有的学生
func (m stuMgr) listAllStu() {
	if len(m.allStudentMap) == 0 {
		fmt.Println("系统中还未录入学生。")
	} else {
		fmt.Println("当前系统中所有的学生如下: ")
		for _, v := range m.allStudentMap {
			fmt.Printf("%#v\n", v)
		}
	}
}

//添加学员
func (m stuMgr) addStu() {
	var (
		id     string
		name   string
		age    int
		gender string
	)
	fmt.Println("请输入要录入的学生的ID:")
	fmt.Scanln(&id)
	v, ok := m.allStudentMap[id]
	if ok {
		fmt.Printf("当前ID学员存在系统中，学员信息: %#v\n", *v)
		return
	}
	fmt.Println("请输入要录入的学生的姓名:")
	fmt.Scanln(&name)
	fmt.Println("请输入要录入的学生的年纪:")
	fmt.Scanln(&age)
	fmt.Println("请输入要录入的学生的性别:")
	fmt.Scanln(&gender)
	s := &student{
		id:     id,
		name:   name,
		age:    age,
		gender: gender,
	}
	m.allStudentMap[id] = s
	fmt.Println("添加完成～")
}

//删除学员
func (m stuMgr) removeStu() {
	fmt.Println("请输入要移除的学生Id")
	var stuId string
	fmt.Scanln(&stuId)
	v, ok := m.allStudentMap[stuId]
	if ok {
		delete(m.allStudentMap, stuId)
		fmt.Printf("成功移除学员: %v\n", *v)
	} else {
		fmt.Println("当前系统中不存在id: ", stuId)
	}
}

// 退出系统
func (m stuMgr) logout() {
	fmt.Println("退出系统～")
	os.Exit(0)
}
