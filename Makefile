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

clean-target:
	rm -rf target

clean: clean-downloads clean-target

test-run: tools
	cd tools; ./play_one_game_live.sh

start/start.jar: start
	cd start; make

target:
	mkdir target

all: start/start.jar target
	javac -d target -cp start/start.jar src/main/java/nl/cois/**.java

play: all
	python tools/playgame.py "java -cp start/start.jar:target nl.cois.DvbBot" "java -cp start/start.jar:target nl.cois.Caecus"  --map_file tools/maps/example/tutorial1.map --log_dir game_logs --turns 60 --scenario --food none --player_seed 7 --verbose -e

