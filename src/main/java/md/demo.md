1、标题

注： #后面保持空格
# h1
## h2
### h3
#### h4
##### h5
###### h6
######## h7  //错误代码
######### h8 //错误代码

2、分级标题

注： = - 最少可以只写一个，兼容性一般

一级标题
=========
二级标题
-

4、引用

> hello world!
>> hello world!
>>> hello world!

5、行内标记

注：用`标记代码块变成一行

标记之外`this is mark`标记之外

6、代码块

* 注：与上行距离空一行
* 注：用 ``` 生成块
* 注：用Tab缩进

```
    <div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </div>
    var num = 0;
    for (var i = 0; i < 5; i++) {
        num+=i;
    }
    console.log(num);
```

(自定义语法)

注：根据不同的语言配置不同的代码着色
```javascript
var num = 0;
for (var i = 0; i < 5; i++) {
    num+=i;
}
console.log(num);
```

7、内嵌链接

注：{:target="_blank"}跳转方式兼容性一般 ，多数第三方平台不支持跳转

[百度](http://www.baidu.com)

8、 序列

注：序列.后保持空格

1. one
2. two
3. three

无序：

* one
* two
* three

嵌套：

1. one
    1. one-1
    2. one-2
2. two
    * two-1
    * two-2
    
9、分隔符

注：最少三个 --- 或 ***或 * * *

---
***
* * *

10、时序图

注：
```
 -    实线
 >    实心箭头
 --   虚线
 >>   空心箭头
```

```sequence
A->>B: 你好
Note left of A: 我在左边     // 注释方向，只有左右，没有上下
Note right of B: 我在右边
B-->A: 很高兴认识你
```


