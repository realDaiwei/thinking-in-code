package main

import (
	"context"
	"fmt"
	"math/rand"
	"sync"
	"time"
)

var wg sync.WaitGroup

// withCancel
func tryWithCancel() {
	ctx, cancel := context.WithCancel(context.Background())
	wg.Add(1)
	go f0(ctx)
	time.Sleep(5 * time.Second)
	cancel()
	wg.Wait()
	fmt.Println("finished~")
}

func f0(c context.Context) {
	defer func() {
		fmt.Println("stop!")
		wg.Done()
	}()
	// LOOP:
	for {
		fmt.Println("hello")
		time.Sleep(1 * time.Second)
		select {
		case <-c.Done():
			// break LOOP
			return
		default:
			fmt.Printf("hello")
		}
	}
}

// withDeadLine 和 withTimeout非常类似，这里演示withTimeout
func tryWithTimeOut() {
	rand.Seed(time.Now().Unix())
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	go f1(ctx)
	n := rand.Int31n(20)
	fmt.Printf("sleep for %d\n", n)
	time.Sleep(time.Second * time.Duration(n))
	cancel()
	// (context deadline exceeded) or (context canceled)
	fmt.Println(ctx.Err())
}

func f1(ctx context.Context) {
	for {
		select {
		case <-ctx.Done():
			fmt.Println("stop")
			return
		default:
			time.Sleep(time.Second)
			fmt.Println("hello~")
		}
	}
}

type TraceCode string

// 上下文可以传递值
func tryWithVal() {
	ctx, cancel := context.WithCancel(context.Background())
	key := TraceCode("key")
	ctx = context.WithValue(ctx, TraceCode("key"), "helloleoleo")
	go f2(ctx, key)
	time.Sleep(5 * time.Second)
	cancel()
}

func f2(ctx context.Context, key TraceCode) {
	for {
		select {
		case <-ctx.Done():
			fmt.Println("stop!!")
			return
		default:
			fmt.Println("hello~")
			v, ok := ctx.Value(key).(string)
			if ok {
				fmt.Printf("traceValue is %s\n", v)
			}
			time.Sleep(time.Second)
		}
	}
}

func main() {
	// tryWithCancel()
	// tryWithTimeOut()
	tryWithVal()
}
