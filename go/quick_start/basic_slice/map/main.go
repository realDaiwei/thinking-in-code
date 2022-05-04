package main

import (
	"fmt"
)

func main() {
	var m = make(map[string]int, 6)

	// fmt.Printf("%v, length is %d\n", m, len(m))
	m["daiwei"] = 26
	// fmt.Printf("%v, length is %d\n", m, len(m))
	m["xiaofeng"] = 25
	// fmt.Printf("%v, length is %d\n", m, len(m))
	m["lainghui"] = 26
	// fmt.Printf("%v, length is %d\n", m, len(m))
	m["zouting"] = 26
	// fmt.Printf("%v, length is %d\n", m, len(m))
	m["renchu"] = 24
	// fmt.Printf("%v, length is %d\n", m, len(m))
	m["limeiling"] = 25
	// fmt.Printf("%v, length is %d\n", m, len(m))
	m["laoji"] = 26
	// fmt.Println(m)

	// v, ok := m["laoji"]
	// if ok {
	// 	fmt.Println(v)
	// } else {
	// 	fmt.Println("没有这个key")
	// }

	// 查询某个key值
	// v, ok := m["daiwei"]
	// if ok {
	// 	fmt.Println("value is ", v)
	// } else {
	// 	fmt.Println("没有查到这个key")
	// }

	// 循环 k, v
	// for k, v := range m {
	// 	fmt.Printf("k : %s, v : %d\n", k, v)
	// }

	// // 只遍历 k
	// for k := range m {
	// 	fmt.Printf("k : %s\n", k)
	// }

	// //只遍历value，key使用匿名变量接
	// for _, v := range m {
	// 	fmt.Printf("v : %d\n", v)
	// }

	// // 删除元素
	// delete(m, "daiwei")
	// delete(m, "lainghui")
	// fmt.Println("删除元素")
	// // 循环 k, v
	// for k, v := range m {
	// 	fmt.Printf("k : %s, v : %d\n", k, v)
	// }

	//按照某个排序查询遍历map
	// 逻辑很简单，把所有的key拿出来，放入到一个切片中，然后排序切片，然后再按照切片遍历map
	// rand.Seed(time.Now().UnixNano())

	// scoreMap := make(map[string]int, 100)
	// for i := 0; i < 100; i++ {
	// 	no := rand.Intn(100)
	// 	key := fmt.Sprintf("stu%02d", no)
	// 	scoreMap[key] = no
	// }

	// keys := make([]string, 0, 100)
	// for k := range scoreMap {
	// 	keys = append(keys, k)
	// }
	// sort.Strings(keys)
	// for _, k := range keys {
	// 	fmt.Printf("key : %s, value : %d\n", k, scoreMap[k])

	// slice和map结构的套在一起
	// [map1, map2, map3]
	s1 := make([]map[string]int, 10)
	fmt.Println(s1)
	fmt.Println(s1[0] == nil)
	for i := 0; i < len(s1); i++ {
		s1[i] = make(map[string]int)
	}
	s1[0]["daiwei"] = 18
	fmt.Println(s1)

	// {key1:[], key2:[], key3:[]}
	s2 := make(map[string][]int)
	s2["daiwei"] = make([]int, 10)
	s2["daiwei"][0] = 100
	s2["laoji"] = make([]int, 10)
	s2["laoji"][0] = 10
	fmt.Println(s2)
}
