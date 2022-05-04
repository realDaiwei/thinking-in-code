package main

import "fmt"

var (
	coins = 25
	users = []string{
		"Matthew", "Sarah", "Augustus", "Heidi", "Emilie", "Peter", "Giana", "Adriano", "Elizabeth",
	}
	distribution = make(map[string]int, len(users))
)

func main() {
	left := distribute()
	fmt.Println("剩下：", left)
	fmt.Println(distribution)
}

func distribute() (left int) {
	coinsNum := coins
	for _, name := range users {
		if coinsNum <= 0 {
			break
		}
		personalCoin := 0
		for _, c := range name {
			nums := letterToCoin(c)
			if nums > coinsNum {
				personalCoin += coinsNum
				coinsNum -= nums
				break
			} else {
				personalCoin += nums
				coinsNum -= nums
			}
		}
		distribution[name] = personalCoin
	}
	if coinsNum < 0 {
		left = 0
	} else {
		left = coinsNum
	}
	return
}

func letterToCoin(c rune) (ret int) {
	switch c {
	case 'e', 'E':
		ret = 1
	case 'i', 'I':
		ret = 2
	case 'o', 'O':
		ret = 3
	case 'u', 'U':
		ret = 4
	default:
		ret = 0
	}
	return
}
