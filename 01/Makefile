.PHONY: all

all: challenge.jar
	java -jar challenge.jar

challenge.jar: challenge.kt
	kotlinc challenge.kt -include-runtime -d challenge.jar
