# maven git tag  plugin

> tag 对于项目的发版已经历史代码的追查有着非常重要的意义，但是每次我们每次都需要在发版是手动去git上执行相应的命令，
>git-tag-maven-plugin应运而生


# 使用方式

## 引用plugin插件

```
<plugin>
    <groupId>com.javayh.gittag</groupId>
    <artifactId>git-tag</artifactId>
    <version>1.0.0</version>
</plugin>
```


## 执行相关名利
- 创建tag

```
mvn git-tag:create
```

- 删除tag
```
mvn git-tag:delete
```

- 推动tag
```
mvn git-tag:push
```

- 创建并推动tag
```
mvn git-tag:cp
```