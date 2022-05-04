package main

import (
	"fmt"
	"time"
)

func worker(id int, job <-chan int, result chan<- int) {
	for i := range job {
		fmt.Printf("job[%d] process[%d] strated....\n", id, i)
		time.Sleep(time.Second)
		result <- i * 2
		fmt.Printf("job[%d] process[%d] finished....\n", id, i)
	}
}

func main() {
	job := make(chan int, 64)
	result := make(chan int, 64)

	// start 2 goroutine
	for i := 0; i < 3; i++ {
		go worker(i, job, result)
	}

	for j := 0; j < 10; j++ {
		job <- j
	}
	// 这里的close并不是要强制关闭，而是关闭channel加入新的item
	close(job)
	// 这里存在deadLock的风险，原因是在于主线程在等待result中新的结果，
	// 但是result已经不会有新的结果了，所以main goroutine也就sleeple
	// 解决方案1，等待上面执行完成了直接在遍历所有的结果。
	// 解决方案2，只遍历部分结果。
	// for r := range result {
	// 	fmt.Printf("result is %d\n", r)
	// }
	for i := 0; i < 10; i++ {
		fmt.Printf("result is %d\n", <-result)
	}
}
