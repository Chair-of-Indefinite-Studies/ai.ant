.PHONY: tools java-starter clean-downloads test-run start/MyBot.jar

tools:
	wget http://ants.aichallenge.org/tools.tar.bz2
	tar xvfj tools.tar.bz2

java-starter:
	wget http://ants.aichallenge.org/starter_packages/java_starter_package.zip
	unzip java_starter_package.zip -d start

start: java-starter tools
	@echo "Ready to start"

clean-downloads:
	rm java_starter_package.zip tools.tar.bz2

test-run:
	cd tools; ./play_one_game_live.sh

start/MyBot.jar:
	cd start; make
