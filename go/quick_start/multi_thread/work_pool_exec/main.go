package main

import (
	"fmt"
	"math/rand"
	"time"
)

/*
使用gorout ine和channe1实现一个计草int64随机数各 位数和的程序。
1．开启一个gorout ine盾环生成int64类型的隨机数，发送到jobCham
2．开启24个gorout ineljobchan中取出随机数计算咨位数的和，将结异
3.主goroutine 从resultChan取出结果并打印到终端输出
*/

type job struct {
	v int64
}

type result struct {
	j   *job
	res int64
}

// create job for channel
func create(jobChan chan<- *job) {
	for i := 0; i < 100; i++ {
		x := rand.Int63()
		jobChan <- &job{x}
		time.Sleep(time.Second)
	}
}

func calc(jobChan <-chan *job, resChan chan<- *result) {
	for {
		job, ok := <-jobChan
		if !ok {
			break
		}
		resChan <- &result{
			j:   job,
			res: sumAllNums((*job).v),
		}
	}
}

// 计算一个int64中的所有数字的和
func sumAllNums(x int64) int64 {
	var res int64
	for {
		if x <= 0 {
			break
		}
		res += x % 10
		x /= 10
	}
	return res
}

func main() {
	jobChann := make(chan *job, 64)
	resChan := make(chan *result, 64)
	go create(jobChann)
	go calc(jobChann, resChan)
	for result := range resChan {
		fmt.Printf("job is %v and resule is %d\n", result.j.v, result.res)
	}
}
