.PHONY: clean-downloads clean-extracts clean-target clean test-run all

tools.tar.bz2:
	wget http://ants.aichallenge.org/tools.tar.bz2

tools: tools.tar.bz2
	tar xvfj tools.tar.bz2

start.tar.gz:
	wget https://s3-eu-west-1.amazonaws.com/ai.aint.starter/start.tar.gz

start: start.tar.gz
	tar xvfz start.tar.gz

clean-downloads:
	rm start.tar.gz tools.tar.bz2

clean-extracts:
	rm -rf start tools

clean: clean-downloads clean-extracts

test-run: tools
	cd tools; ./play_one_game_live.sh

start/start.jar: start
	cd start; make

clean-target:
	rm -rf target

target:
	mkdir target

all: start/start.jar target
	javac -d target -cp start/start.jar src/main/java/nl/cois/**.java
