package main

import "fmt"

var manager stuMgr

func main() {
	manager = stuMgr{
		allStudentMap: make(map[string]*student, 64),
	}
	for {
		manager.printWelcome()
		var cmd int
		fmt.Scanln(&cmd)
		fmt.Println("你输入的指令是：", cmd)
		switch cmd {
		case 1:
			manager.listAllStu()
		case 2:
			manager.addStu()
		case 3:
			manager.removeStu()
		case 4:
			manager.logout()
		default:
			fmt.Println("大哥别搞，球球啦～")
		}
		manager.printEnd()
	}

}
