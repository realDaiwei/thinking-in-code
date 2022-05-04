package main

import (
	"fmt"
	"time"
)

func main() {
	now := time.Now()
	fmt.Println(now)
	fmt.Println(now.Year())
	fmt.Println(now.Month())
	fmt.Println(now.Day())
	fmt.Println(now)
	fmt.Println(int(now.Weekday()))
	fmt.Println(now.Hour())
	fmt.Println(now.Minute())
	fmt.Println(now.Second())

	// 时间戳
	fmt.Println("关于时间戳==========")
	fmt.Println(now.Unix())
	fmt.Println(now.UnixNano())

	// 时间戳转时间
	nano := time.Unix(0, now.UnixNano())
	fmt.Println(nano)
	fmt.Println(nano.Year())
	fmt.Println(int(nano.Month()))

	// 时间累加
	fmt.Println("时间累加")
	fmt.Println(now.Add(-24 * time.Hour))

	// 定时器
	// fmt.Println("定时器")
	// ticker := time.Tick(time.Second)
	// for range ticker {
	// 	// fmt.Println(t)
	// 	fmt.Println("tik...tok...")
	// }

	// 格式化
	fmt.Println("格式化=======")
	fmt.Println(now.Format("2006-01-02"))
	fmt.Println(now.Format("2006-01-02 03:04:05 PM"))
	fmt.Println(now.Format("2006-01-02 15:04:05.000"))

	// 字符串解析到时间
	t, err := time.Parse("2006-01-02", "2022-04-23")
	if err != nil {
		fmt.Printf("parse time failed, err %v\n", err)
		return
	}
	fmt.Println(t)
	fmt.Println(t.Unix())

	// sleep
	// fmt.Println("sleep begin~")
	// time.Sleep(5 * time.Second)
	// fmt.Println("sleep end~")

	// timezone
	fmt.Println(now)
	time.Parse("2006-01-02 15:04:05", "2022-04-24 08:50:11")

	loc, err := time.LoadLocation("Asia/Shanghai")
	if err != nil {
		fmt.Printf("load loc failed, err:%v\n", err)
		return
	}
	t, err = time.ParseInLocation("2006-01-02 15:04:05", "2022-04-24 08:50:11", loc)
	if err != nil {
		fmt.Printf("parse time failed, err:%v\n", err)
		return
	}
	fmt.Println(t)
	now = now.Add(48 * time.Hour)
	dur := now.Sub(t)
	fmt.Println(dur)

}
