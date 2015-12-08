.PHONY: tools java-starter clean-downloads test-run start/MyBot.jar

tools:
	wget http://ants.aichallenge.org/tools.tar.bz2
	tar xvfj tools.tar.bz2

java-starter:
	wget https://s3-eu-west-1.amazonaws.com/ai.aint.starter/start.tar.gz
	tar xvfz start.tar.gz

start: java-starter tools
	@echo "Ready to start"

clean-downloads:
	rm java_starter_package.zip tools.tar.bz2

test-run:
	cd tools; ./play_one_game_live.sh

start/start.jar:
	cd start; make
