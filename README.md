# maven-purge-local-failed-plugin

### purpose
I hate deleting uncompleted jar files from remote maven repository again and again . so I write this plugin to escape :)  


### usage

The plugin has only one goal of clean and it will run under the clean phase by default.  

You can use it like this:  

``` XML  
<build>
...
...
	<plugins>
	...
	...
		<plugin>
			<groupId>halfmountainer</groupId>
			<artifactId>maven-purge-local-failed-plugin</artifactId>
			<executions>
				<execution>
					<phase>clean</phase>
					<goals>
						<goal>clean</goal>
					</goals>
				</execution>
			</executions>
		</plugin>
			...
			...
	</plugins>
...
...
</build>
```

### 目的
对于网络环境造成的下载中断，maven 始终不能优雅透明地处理，导致很多开发团队每每被坑掉很多时间（尤其是新手），即使是老鸟，也应当厌倦了一次又一次手工清除下载失败的本地库文件。  


这个插件的唯一目的，就是终结这个顽症，立竿见影。      

### 使用
本插件只有一个 goal : clean, 默认在 clean 阶段执行, 使用时按如下配置即可:

``` XML  
<build>
...
...
	<plugins>
	...
	...
		<plugin>
			<groupId>halfmountainer</groupId>
			<artifactId>maven-purge-local-failed-plugin</artifactId>
			<executions>
				<execution>
					<phase>clean</phase>
					<goals>
						<goal>clean</goal>
					</goals>
				</execution>
			</executions>
		</plugin>
			...
			...
	</plugins>
...
...
</build>
```

    
    
