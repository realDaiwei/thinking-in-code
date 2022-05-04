package main

import (
	"fmt"

	file_op "go.daiwei.io/daiwei/interface_file/file_operation"
)

func main() {
	str, err := file_op.ReadByBufio()
	if err != nil {
		fmt.Printf("read err, err is %v\n", err)
	}
	file_op.WriteByIOUtil("read_copy.txt", str)
}
