tools:
	wget http://ants.aichallenge.org/tools.tar.bz2
	tar xvfj tools.tar.bz2

java-starter:
	wget http://ants.aichallenge.org/starter_packages/java_starter_package.zip
	unzip java_starter_package.zip -d start

start: java-starter tools
	@echo "Ready to start"
