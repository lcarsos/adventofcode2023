.PHONY: all

all: challenge.jar
	java -jar challenge.jar

challenge.jar: challenge.kt
	kotlinc $< -include-runtime -d $@
