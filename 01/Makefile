.PHONY: all prod

all: challenge.jar
	java -jar challenge.jar < test-input.txt

prod: challenge.jar
	java -jar challenge.jar < input.txt

challenge.jar: challenge.kt
	kotlinc $< -include-runtime -d $@
