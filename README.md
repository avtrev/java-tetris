# Java Tetris

This is a tetris game I made with java in the fall of 2020.
I was taking a JAVA II course at the time and wanted to practice some of what I had learned in my free time.
I did not follow as many best practices at the time, so I went through and cleaned it up a bit before pushing it to the repo.
The logic used to get the game working was fun to try and figure out.
Looking back at the code, I'm sure I would have done some things differently now, but at the time "IT WORKED!" and I was happy with that.

This game does not follow all of the tetris rules.
You can pause the game and still move the shapes around, it will just not descend on its own.
Your can turn the speed increase off so that it stays the same speed the whole time.

# Installation

If you know how to compile java files please disregard these instructions.
I added a compile-tetris-jar.sh shell script that compiles everything to a tetris.jar file.
Then bash script is for Linux and MacOs terminals. I will make a .bat script for Windows soon.

In a terminal:

mkdir java-tetris

cd java-tetris

git clone https://github.com/avtrev/java-tetris.git .

bash compile-tetris-jar.sh

the tetris.jar will start at the end of the script.

you can now start the tetris.jar with either

bash start-tetris.sh

or

java -jar tetris.jar

# Controls

h = show help menu

Enter or p = start/pause game

Movements = Up Down Left Right Arrows

shift (left or right) = drop shape to bottom

Spacebar = rotate shape

z = set shape in place

s = showscores

r = reset

f = toggle speed increase (default on)
