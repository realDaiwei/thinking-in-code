package main

import (
	"fmt"
	"runtime"
	"sync"
)

var waitGroup sync.WaitGroup

// 将100个数字放入channel中
func f1(ch1 chan<- int) {
	defer waitGroup.Done()
	defer close(ch1)
	for i := 0; i < 100; i++ {
		ch1 <- i
	}
}

func f2(ch1 <-chan int, ch2 chan<- int) {
	defer waitGroup.Done()
	// 这里不能关闭ch2，因为在关闭的时候可能另外的线程正在往里面写数据 panic: send on closed channel
	// defer once.Do(func() { close(ch2) })
	for {
		v, ok := <-ch1
		if !ok {
			break
		}
		ch2 <- v * v
	}
}

func main() {
	runtime.GOMAXPROCS(2)
	ch1, ch2 := make(chan int, 100), make(chan int, 100)
	// defer close(ch2)
	waitGroup.Add(3)
	go f1(ch1)
	go f2(ch1, ch2)
	go f2(ch1, ch2)
	waitGroup.Wait()
	// for v := range ch2 {
	// 	fmt.Println(v)
	// }
	for {
		if len(ch2) <= 0 {
			break
		}
		fmt.Println(<-ch2)
	}
	fmt.Println("finished～")
}
