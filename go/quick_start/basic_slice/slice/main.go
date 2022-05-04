package main

import "fmt"

func main() {
	var s1 []int
	var cities []string

	// 此时slice还没有初始化
	// slice只是一个指针
	fmt.Println(s1, cities)
	fmt.Println(s1 == nil)
	fmt.Println(cities == nil)

	// 初始化 slice下面的数组，slice指向实际的内存空间
	s1 = []int{1, 2, 3, 4, 5, 6}
	cities = []string{"beijing", "shanghai", "shenzhen", "nanchang", "jiujiang"}

	fmt.Printf("s1 type is %T, len is %d, cap is %d, %v\n", s1, len(s1), cap(s1), s1)
	fmt.Printf("cities type is %T, len is %d, cap is %d, %v\n", cities, len(cities), cap(cities), cities)

	// 基础切片 [2, len(s1)]
	s2 := s1[2:]
	fmt.Printf("s2 type is %T, len is %d, cap is %d, %v\n", s2, len(s2), cap(s2), s2)

	// 全部切过去，当前s3是一个指针，所以直接修改会修改这个引用指向对象的值
	s3 := s1[:]
	fmt.Printf("s3 type is %T, len is %d, cap is %d, %v\n", s3, len(s3), cap(s3), s3)
	s1[3] = 10086
	fmt.Printf("s3 type is %T, len is %d, cap is %d, %v\n", s3, len(s3), cap(s3), s3)

	// 从数组切出切片
	a1 := []int{1, 2, 3, 4, 5, 6, 7, 8}
	s4 := a1[:3]
	fmt.Printf("s4 type is %T, len is %d, cap is %d, %v\n", s4, len(s4), cap(s4), s4)

	s5 := make([]int, 5, 10)
	fmt.Printf("s5 type is %T, len is %d, cap is %d, %v\n", s5, len(s5), cap(s5), s5)

	s5[0] = 10086
	s5[1] = 10087
	// s5[8] = 20000 // 虽然没有超过 cap 但是超过len也不能赋值
	fmt.Printf("s5 type is %T, len is %d, cap is %d, %v\n", s5, len(s5), cap(s5), s5)

	// slice 的遍历
	for idx, v := range s5 {
		fmt.Println(idx, v)
	}

	for i := 0; i < len(s5); i++ {
		fmt.Println(i, s5[i])
	}

	

}
