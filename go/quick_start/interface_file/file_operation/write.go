package file_op

import (
	"bufio"
	"fmt"
	"io/ioutil"
	"os"
)

// 使用OS实现数据的写文件
func WriteByOS(filename, content string) {
	f, err := os.OpenFile(filename, os.O_CREATE|os.O_WRONLY|os.O_TRUNC, 0644)
	if err != nil {
		fmt.Printf("open file err, err %#v\n", err)
	}
	defer f.Close()
	_, err = f.WriteString(content)
	if err != nil {
		fmt.Printf("write err, err %#v\n", err)
	}
}

// 使用bufIo的方式写入数据
func WriteByBufio(filename, content string) {
	f, err := os.OpenFile(filename, os.O_CREATE|os.O_WRONLY|os.O_TRUNC, 0644)
	if err != nil {
		fmt.Printf("open file err, err %#v\n", err)
	}
	defer f.Close()
	writer := bufio.NewWriter(f)
	_, err = writer.WriteString(content)
	flushErr := writer.Flush()
	if err != nil || flushErr != nil {
		fmt.Printf("write err, err %#v\n or flush err, err %#v\n", err, flushErr)
	}
	writer.Flush()
}

// 通过IOUtil的方式写入数据
func WriteByIOUtil(filename, content string) {
	// f, err := os.OpenFile(filename, os.O_CREATE|os.O_WRONLY|os.O_TRUNC, 0644)
	err := ioutil.WriteFile(filename, []byte(content), 0664)
	if err != nil {
		fmt.Printf("write err %#v\n", err)
	}
}

