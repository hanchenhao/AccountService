# AccountService
spring boot 练手项目

## 创建Maven项目

### 1. 添加`pom.xml`

#### pom.xml中常用的几个标签：
- `<modelVersion>`： POM model版本 (总是4.0.0).
- `<groupId>`： 项目所属的组或组织。通常表示为倒置的域名
- `<artifactId>` ：提供给项目的库项目的名称 (例如, 其 jar 或 war 文件的名称)
- `<version>`. 正在生成项目的版本
- `<packaging>` - 应如何打包项目。默认为 jar ，代表项目文件打包成jar包。使用 "war" 代表打包成war 包

### 2. 创建项目目录结构

```
└── src
    └── main
        └── java
            └── example
            	└── controllers
            	└── model
```
### 3. 添加依赖和插件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.xiedaimala.microservices</groupId>
    <artifactId>hello-spring-boot</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!-- Inherit defaults from Spring Boot -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.5.RELEASE</version>
    </parent>
    <dependencies>
        <!-- Add typical dependencies for a web application -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <!-- Package as an executable jar -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

