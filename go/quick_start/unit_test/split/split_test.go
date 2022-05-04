package split

import (
	"reflect"
	"testing"
)

// func TestSplit(t *testing.T) {
// 	got := split.Split("dai:wei", ":")
// 	want := []string{"dai", "wei"}
// 	if !reflect.DeepEqual(got, want) {
// 		t.Errorf("test failed, want %v but got %v\n", want, got)
// 	}
// }

// func TestSomeSpecialSituation(t *testing.T) {
// 	got := split.Split("daiwei", "")
// 	want := []string{"d", "a", "i", "w", "e", "i"}
// 	if !reflect.DeepEqual(got, want) {
// 		t.Errorf("test failed, want %v but got %v\n", want, got)
// 	}
// }

// func TestEmptySituation(t *testing.T) {
// 	got := split.Split("", "")
// 	want := []string{}
// 	if !reflect.DeepEqual(got, want) {
// 		t.Errorf("test failed, want %v but got %v\n", want, got)
// 	}
// }

func TestSplit(t *testing.T) {
	type testCase struct {
		str  string
		sep  string
		want []string
	}

	// 使用切片存储多个测试用例，最后循环执行
	// testGroup := []testCase{
	// 	{"dai:wei", ":", []string{"dai", "wei"}},
	// 	{"daiwei", "", []string{"d", "a", "i", "w", "e", "i"}},
	// 	{"", "", []string{}},
	// }

	// for _, tc := range testGroup {
	// 	got := split.Split(tc.str, tc.sep)
	// 	if !reflect.DeepEqual(got, tc.want) {
	// 		t.Errorf("test failed, want %v but got %v\n", tc.want, got)
	// 	}
	// }

	// 使用map存储多个测试用例
	//go test -run=TestSplit/case_2 单独跑case2
	testGroup := map[string]testCase{
		"case_1": {"dai:wei", ":", []string{"dai", "wei"}},
		"case_2": {"daiwei", "", []string{"d", "a", "i", "w", "e", "i"}},
		"case_3": {"", "", []string{}},
	}

	for name, tc := range testGroup {
		t.Run(name, func(t *testing.T) {
			got := Split(tc.str, tc.sep)
			if !reflect.DeepEqual(got, tc.want) {
				t.Errorf("test failed, want %v but got %v\n", tc.want, got)
			}
		})
	}

	// go test -cover 可以统计单元测试覆盖率，但是前提是要和被测试文件放在同一个文件夹中
	// go test -cover -coverprofile=cover.out 可以将覆盖输出到cover.out文件
	// go tool cover -html=cover.out 打开html页面代码具体的测试覆盖度
}

func BenchmarkSplit(b *testing.B) {
	for i := 0; i < b.N; i++ {
		Split("dai:wei:hello", ":")
	}
}
