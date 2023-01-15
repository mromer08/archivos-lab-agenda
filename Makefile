MAVEN := mvn
SRC:= .
JARFILE := target/agenda-1.0-SNAPSHOT-jar-with-dependencies.jar

compile: Makefile
	${MAVEN} -f ${SRC}/pom.xml clean verify

agenda: compile
	@rm -f agenda
	echo '#!/bin/bash' >> agenda
	echo 'java -jar ${JARFILE} $$*' >> agenda
	chmod 755 agenda

test1: clean agenda
	./agenda -f tests/test1.txt < tests/input1.test

test2: clean agenda
	./agenda -f tests/test2.txt < tests/input1.test

test3: clean agenda
	./agenda < tests/input2.test

clean:
	-${MAVEN} -f ${SRC}/pom.xml clean
	-rm -f tests/*.save agenda
