NAME = swingy

$(NAME):
	@echo "." > sources.txt
	@find . -name "*.java" >> sources.txt
	@javac -sourcepath @sources.txt
	@echo "\033[1;32;4mCOMPILING SUCCESSFUL"
all: $(NAME)

clean:
	@rm -R */*/*/*/*.class */*/*/*.class */*/*/*/*/*.class
	@rm sources.txt
	@echo "\033[1;34;4mCLEAN SUCCESSFUL\033[0m"

re: fclean all
