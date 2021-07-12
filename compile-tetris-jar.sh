#!/bin/bash

#compile tetris.jar
echo 'creating tetris.jar'

#compile .java files
echo 'compiling .java files'
javac -d bin/ src/*.java

#move into bin dir
echo 'moving into bin/ directory'
cd bin

#compile tetris.jar with manifest.mf in dir one level up
echo 'compiling .class files with manifest.mf to tetris.jar'
jar -cfm ../tetris.jar ../manifest.mf ./*.class

#move back to parent dir
echo 'moving back to parent directory'
cd ..

#create start-tetris.sh
echo 'creating start-tetris.sh'
echo -e "#!/bin/bash\njava -jar tetris.jar" > start-tetris.sh

echo -e "start tetris.jar with\nbash start-tetris.sh\nor\njava -jar tetris.jar"

#start tetris.jar
echo 'running tetris.jar'
java -jar tetris.jar

