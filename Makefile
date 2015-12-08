java-starter:
	wget http://ants.aichallenge.org/starter_packages/java_starter_package.zip
	unzip java_starter_package.zip -d start

start: java-starter
	echo "Ready to start"
