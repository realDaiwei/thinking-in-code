package main

import (
	"fmt"
	"strconv"
	"sync"
)

// go里面的map不是一个并发安全的容器, 加锁就可以正常访问
// var m = make(map[string]int)
// var lock sync.Mutex

// go提供的并发安全的Map
var concMap sync.Map

// func get(key string) int {
// 	return m[key]
// }

// func set(key string, value int) {
// 	m[key] = value
// }

// func testMap() {
// 	wg := sync.WaitGroup{}
// 	for i := 0; i < 10; i++ {
// 		wg.Add(1)
// 		go func(n int) {
// 			key := strconv.Itoa(n)
// 			lock.Lock()
// 			set(key, n)
// 			lock.Unlock()
// 			fmt.Printf("k=%s, v=%d\n", key, get(key))
// 			wg.Done()
// 		}(i)
// 	}
// 	wg.Wait()
// 	fmt.Println("finished~")
// }

func testConcMap() {
	wg := sync.WaitGroup{}
	for i := 0; i < 100; i++ {
		wg.Add(1)
		go func(n int) {
			defer wg.Done()
			key := strconv.Itoa(n)
			concMap.Store(key, n)
			v, ok := concMap.Load(key)
			if ok {
				fmt.Printf("key = %v, value = %v\n", key, v)
			}
		}(i)
	}
	wg.Wait()
	fmt.Println("finished~")
}

func main() {
	// testMap()
	testConcMap()
}
