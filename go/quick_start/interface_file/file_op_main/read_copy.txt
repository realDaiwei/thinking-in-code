package file_op

import (
	"bufio"
	"fmt"
	"io"
	"io/ioutil"
	"os"
)

// 使用最简单直接的方法读取文件
func OsRead() {
	f, err := os.Open("/Users/daiwei/go/src/go.daiwei.io/daiwei/day05/file_operation/read.go")
	if err != nil {
		fmt.Printf("open file failed and err is %#v", err)
		return
	}
	defer f.Close()

	var tmp [128]byte
	for {
		n, err := f.Read(tmp[:])
		if err != nil {
			fmt.Printf("read from file failed, err is %#v\n", err)
			return
		}
		fmt.Print(string(tmp[:n]))
		if n < len(tmp) {
			// fmt.Printf("read finished～ and n val %d\n", n)
			return
		}
	}
}

// 使用bufIo一行行读取数据
func ReadByBufio() (string, error) {
	f, err := os.Open("/Users/daiwei/go/src/go.daiwei.io/daiwei/day05/file_operation/read.go")
	if err != nil {
		fmt.Printf("open file failed, err %v\n", err)
		return "", err
	}
	defer f.Close()
	reader := bufio.NewReader(f)
	var str string
	for {
		line, err := reader.ReadString('\n')
		if line == "" && err == io.EOF {
			return str, nil
		}
		if err != nil {
			fmt.Printf("read failed and err is %#v\n", err)
			return "", err
		}
		str = str + string(line)
		// fmt.Print(string(line))
	}
}

func ReadByIOUitl() {
	arr, err := ioutil.ReadFile("/Users/daiwei/go/src/go.daiwei.io/daiwei/day05/file_operation/read.go")
	if err != nil {
		fmt.Printf("read file failed, err:%v\n", err)
		return
	}
	fmt.Println(string(arr))
}
